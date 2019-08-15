package impatient2.chapter3

import scala.util.Random

object Main {
  def main(args: Array[String]): Unit = {
    val random = new Random
    val a = for (i <- 0 until 5) yield random.nextInt(5)
    println(a)
  }
}
