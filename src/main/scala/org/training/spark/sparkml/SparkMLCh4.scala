package org.training.spark.sparkml

import java.io.File

import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.jblas.DoubleMatrix

object SparkMLCh4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SparkMLCh4").setMaster("local[4]")
    val sc = new SparkContext(conf)
    val path = "E:\\Download\\ml-100k"
    val rawData: RDD[String] = sc.textFile(path + File.separator + "u.data")
    //  println(rawData.first())
    val rawRatings = rawData.map(_.split("\\t").take(3))
    rawRatings.first().foreach(println)

    val ratings = rawRatings.map{case Array(user, movie, rating) => Rating(user.toInt, movie.toInt, rating.toDouble)}
    // train model
    val model = ALS.train(ratings, 50, 10, 0.01)
    println(model.userFeatures.count())
    // predict the rating of user 789 and product 123
    var predictedRating: Double = model.predict(789, 123)
    // 预测用户789对123商品的评分
    println(predictedRating)

    // recommend 10 products for user 789
    val topKRecs: Array[Rating] = model.recommendProducts(789, 10)
    println(topKRecs.mkString("\n"))

    val movies = sc.textFile(path + File.separator + "u.item")
    // get the dict of id and movie title
    val titles: collection.Map[Int, String] = movies.map(line => line.split("\\|").take(2)).map(array=>(array(0).toInt, array(1))).collectAsMap()
    println(titles(123))

    // user 789's total movie
    val moviesForUser: Seq[Rating] = ratings.keyBy(_.user).lookup(789)
    println(moviesForUser)

    // 789's top 10 movies and their ratings
    moviesForUser.sortBy(-_.rating).take(10).map(rating => (titles(rating.product), rating.rating)).foreach(println)

    // 789's top recommendation, with titles and ratings
    topKRecs.map(rating=>(titles(rating.product), rating.rating)).foreach(println)

    // product 567's vector
    val itemFactor = model.productFeatures.lookup(567).head
    val itemVector: DoubleMatrix = new DoubleMatrix(itemFactor)
    println(cosineSimilarity(itemVector, itemVector))

    // calculator product 567 with other's similarity
    val sims: RDD[(Int, Double)] = model.productFeatures.map { case (id, factor) =>
      val factorVector = new DoubleMatrix(factor)
      val sim = cosineSimilarity(factorVector, itemVector)
      (id, sim)
    }
    sims

    // get top 10 similarity
    val sortedSims = sims.top(10)(Ordering.by[(Int, Double), Double]{
      case (id, similarity) => similarity
    })
    println(sortedSims.take(10).mkString("\\n"))

    // get top 2-11 similarity, exclude product 567 itself
    val sortedSims2 = sims.top(11)(Ordering.by[(Int, Double), Double]{
      case (id, similarity) => similarity
    })
    println(sortedSims.slice(1, 11).map{case (id, sim) => (titles(id), sim)}.take(10).mkString("\n"))

    // user 789's first movie
    val actualRating: Rating = moviesForUser.take(1).head
    // user 789's first movie's prediction
    predictedRating = model.predict(789, actualRating.product)
    // squaredError
    val squaredError = math.pow(predictedRating - actualRating.rating, 2.0)
    println(squaredError)

    // all users with their products
    val usersProducts: RDD[(Int, Int)] = ratings.map{
      case Rating(user, product, rating) => (user, product)
    }
    // predict all users's rating with their relevant products
    val predictions = model.predict(usersProducts).map{
      case Rating(user, product, rating) => ((user, product), rating)
    }

    // join to generate (user, product), (rating, predictions) pair
    val ratingsAndPredictions: RDD[((Int, Int), (Double, Double))] = ratings.map {
      case Rating(user, product, rating) => ((user, product), rating)
    }.join(predictions)
    // get MSE between ratings with predictions
    val MSE = ratingsAndPredictions.map{
      case ((user, product), (actual, predicted)) => math.pow(actual - predicted, 2)
    }.reduce(_ + _) / ratingsAndPredictions.count
    println("Mean Squared Error = " + MSE)
    // get sqrt
    val RMSE = math.sqrt(MSE)
    println("Root Mean Squared Error = " + RMSE)

    // calculate the apk10 for user 789
    val actualMovies = moviesForUser.map(_.product)
    val predictedMovies = topKRecs.map(_.product)
    val apk10 = avgPrecisionK(actualMovies, predictedMovies, 10)

    val itemFactors = model.productFeatures.map { case (id, factor) => factor }.collect()
    val itemMatrix = new DoubleMatrix(itemFactors)
    println(itemMatrix.rows, itemMatrix.columns)
    val imBroadcast = sc.broadcast(itemMatrix)
    // get all users and their recommended products
    val allRecs = model.userFeatures.map{ case (userId, array) =>
      val userVector = new DoubleMatrix(array)
      val scores = imBroadcast.value.mmul(userVector)
      val sortedWithId = scores.data.zipWithIndex.sortBy(-_._1)
      val recommendedIds = sortedWithId.map(_._2 + 1).toSeq
      (userId, recommendedIds)
    }
    // [user, (movieId1, movieId2, ...)]
    val userMovies = ratings.map{ case Rating(user, product, rating) => (user, product)}.groupBy(_._1)
    val K = 10
    val MAPK = allRecs.join(userMovies).map{ case (userId, (predicted, actualWithIds)) =>
      val actual = actualWithIds.map(_._2).toSeq
      avgPrecisionK(actual, predicted, K)
    }.reduce(_ + _) / allRecs.count
    println("Mean Average Precision at K = " + MAPK)

    // 使用 RegressionMetrics 来求解MSE和RMSE得分
    import org.apache.spark.mllib.evaluation.RegressionMetrics
    val predictedAndTrue = ratingsAndPredictions.map { case ((user,
    product), (predicted, actual)) => (predicted, actual) }
    val regressionMetrics = new RegressionMetrics(predictedAndTrue)
    println("Mean Squared Error = " + regressionMetrics.meanSquaredError)
    println("Root Mean Squared Error = " + regressionMetrics.rootMeanSquaredError)

    import org.apache.spark.mllib.evaluation.RankingMetrics
    val predictedAndTrueForRanking = allRecs.join(userMovies).map{ case
      (userId, (predicted, actualWithIds)) =>
      val actual = actualWithIds.map(_._2)
      (predicted.toArray, actual.toArray)
    }
    val rankingMetrics = new RankingMetrics(predictedAndTrueForRanking)
    println("Mean Average Precision = " + rankingMetrics.meanAveragePrecision)

    val MAPK2000 = allRecs.join(userMovies).map{ case (userId, (predicted,
    actualWithIds)) =>
      val actual = actualWithIds.map(_._2).toSeq
      avgPrecisionK(actual, predicted, 2000)
    }.reduce(_ + _) / allRecs.count
    println("Mean Average Precision = " + MAPK2000)
    sc.stop()
  }

  def cosineSimilarity(vec1: DoubleMatrix, vec2: DoubleMatrix): Double = {
    vec1.dot(vec2) / (vec1.norm2() * vec2.norm2())
  }

  def avgPrecisionK(actual: Seq[Int], predicted: Seq[Int], k: Int): Double = {
    val predK = predicted.take(k)
    var score = 0.0
    var numHits = 0.0
    for ((p, i) <- predK.zipWithIndex) {
      if (actual.contains(p)) {
        numHits += 1.0
        score += numHits / (i.toDouble + 1.0)
      }
    }
    if (actual.isEmpty) {
      1.0
    } else {
      score / scala.math.min(actual.size, k).toDouble
    }
  }
}
