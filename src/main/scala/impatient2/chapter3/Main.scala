package impatient2.chapter3

import java.util.TimeZone

import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val random = new Random
    val a = for (i <- 0 until 5) yield random.nextInt(5)
    println(a)

    val arr = Array(1, 2, 3, 4, 5)
    println(arr.grouped(2))
    for (i <- 0 to arr.length) {
      if (i % 2 == 0 && i < arr.length - 1) {
        val tmp = arr(i + 1)
        arr(i + 1) = arr(i)
        arr(i) = tmp
      }
    }
    println(arr.mkString(" ").toString)

    val arr1 = Array(5, 4, 3, 0, 2, 1, -1, -3, 0, -7, -11)
    println((arr1.filter(_ > 0) ++ arr1.filter(_ < 0) ++ arr1.filter(_ == 0)).mkString(" "))

    println(arr1.sortWith(_ > _).mkString(" "))

    println(TimeZone.getAvailableIDs.filter(_.startsWith("America")).map(_.stripPrefix("America/")).sorted.mkString(" "))
  }
}
