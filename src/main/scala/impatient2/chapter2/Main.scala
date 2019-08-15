package impatient2.chapter2

import java.time.LocalDate

object Main {
  def SigNum(n: Int): Int = if (n > 0) 1 else if (n < 0) -1 else 0

  def EmptyBlock(): Unit = {}

  def countDown(n: Int) = {
    for (i <- n to 0 by -1) {
      println(i)
    }
  }

  def product(n: Int): Int = {
    if (n == 1) 1 else n * product(n - 1)
  }

  def pow(x: Double, n: Int): Double = {
    if (n < 0) 1 / pow(x, -n)
    else if (n == 0) 1.toDouble
    else if (n % 2 == 1) x * pow(x, n - 1)
    else pow(pow(x, n / 2), 2)
  }

  def main(args: Array[String]): Unit = {
    println(SigNum(-6))
    println(EmptyBlock())
    println(EmptyBlock().getClass.getSimpleName)
    var x: Unit = {}
    var y = 1
    x = y = 1
    for (i <- 10 to 1 by -1) {
      println(i)
    }
    countDown(20)
    var prod = 1L
    for (c <- "Hello") {
      prod = prod * c.toLong
    }
    println(prod)
    println("Hello".foldLeft(1L)((product, c) => product * c))
    println(product(5))

    import java.time.LocalDate



    val year = 2017
    val month = 11
    val day = 30

    println(date"$year-$month-$day")
  }

  implicit class DateInterpolator(val sc: StringContext) extends AnyVal {

    def date(args: Any*): LocalDate = {
      try {
        if (args.length != 3) throw new IllegalArgumentException("there aren’t three arguments")
        val (year, month, day) = (args(0).toString.toInt, args(1).toString.toInt, args(2).toString.toInt)
        for (x <- sc.parts) if (x.length > 0 && !x.equals("-")) throw new IllegalArgumentException("Date parts aren’t separated by dashes")
        LocalDate.of(year, month, day)
      }
      catch {
        case ex: NumberFormatException =>
          println("Date parts aren’t integer")
          throw ex
        case ex: IllegalArgumentException =>
          println("See exception message for detail")
          throw ex
      }
    }
  }
}


