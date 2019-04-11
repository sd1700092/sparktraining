package org.training.spark

import java.io.File

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object SparkMLCh3 extends App {
  val conf = new SparkConf().setAppName("SparkMLCh3").setMaster("local[4]")
  val sc = new SparkContext(conf)
  val path = "E:\\Download\\ml-100k"
//  println(File.separator)
  val userData: RDD[String] = sc.textFile(path + File.separator + "u.user")
  println(userData.first())
  val userFields = userData.map(line => line.split("\\|"))
  val numUsers = userFields.map(fields => fields(0)).count()
  val numGenders = userFields.map(fields => fields(2)).distinct().count()
  val numOccupations = userFields.map(fields => fields(3)).distinct().count()
  val numZipcodes = userFields.map(fields => fields(4)).distinct().count()
  println(s"Users: $numUsers, genders: $numGenders, occupations: $numOccupations, ZIP codes: $numZipcodes")
//  userFields.map(fields => (fields(3), 1)).take(50).foreach(println)
  userFields.map(fields => fields(3)).countByValue().foreach(println) // 和reduceByKey一样的
  userFields.map(fields => (fields(3), 1)).reduceByKey(_ + _).collect().sortBy(- _._2).foreach(println)

  val ratingData: RDD[String] = sc.textFile(path + File.separator + "u.data")
  println(ratingData.first())
  val ratings: RDD[Int] = ratingData.map(fields=>fields(2).toInt)
  println(ratings.stats())

  val allOccupations: Array[String] = userFields.map(fileds=>fileds(3)).distinct().collect().sorted
  allOccupations.foreach(println)
  var idx = 0
  var allOccupationsMap = mutable.Map[String, Int]()
  for( o <- allOccupations) {
    allOccupationsMap(o) = idx
    idx+=1
  }
  println(allOccupationsMap)
  sc.stop()
}
