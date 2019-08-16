package impatient2.chapter3

import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val random = new Random
    val a = for (i <- 0 until 5) yield random.nextInt(5)
    println(a)

    val arr = Array(1, 2, 3, 4, 5)
    for (i <- 0 to arr.length) {
      if (i % 2 == 0 && i < arr.length - 1) {
        val tmp = arr(i + 1)
        arr(i + 1) = arr(i)
        arr(0) = tmp
      }
    }
    println(arr.mkString(" ").toString)
  }
}
