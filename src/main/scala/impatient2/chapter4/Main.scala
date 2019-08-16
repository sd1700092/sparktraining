package impatient2.chapter4

import java.util

import scala.collection.JavaConverters._
import scala.collection.mutable

object Main {
  def main(args: Array[String]): Unit = {
    val cosmos = Map("AutoChess" -> 100, "Dota" -> 60, "Storm" -> 80)
    val cosmos2 = cosmos.map { case (k, v) => (k, v * 0.9) }
    var wordMap = collection.immutable.Map[String, Int]()
    wordMap += ("Hello" -> 1)
    wordMap += ("Java" -> 2, "Python" -> 3)
    var scalaMap = new util.TreeMap[String, Int]().asScala
    scalaMap += ("Hadoop" -> 4)

    val dayMap = mutable.LinkedHashMap("Monday" -> java.util.Calendar.MONDAY, "Tuesday" -> java.util.Calendar.TUESDAY,
      "Wednesday" -> java.util.Calendar.WEDNESDAY, "Thursday" -> java.util.Calendar.THURSDAY)
    //    dayMap.foreach(println)

    val propMap = System.getProperties.asScala
    var longestKey = 0
    propMap.foreach { case (k, _) =>
      if (k.length > longestKey) {
        longestKey = k.length
      }
    }
    propMap.foreach { case (k, v) => {
        println(f"$k%18s|$v")
      }
    }

    println("Hello".zip("World").mkString(" "))
  }

  def minmax(values: Array[Int]): (Int, Int) = {
    (values.min, values.max)
  }

  def lteqgt(values: Array[Int], v: Int): (Array[Int], Array[Int], Array[Int]) = {
    (values.filter(_ < v), values.filter(_ == v), values.filter(_ > v))
  }
}
