package impatient2.chapter5

class Counter {
  private[chapter5] var value = 0

  def increment() {
    value += 1
  }

  def current: Int = value

  def isLess(other: Counter):Boolean = value < other.value
}

object Counter {
  def main(args: Array[String]): Unit = {
    val myCounter = new Counter
//    myCounter.value = 10
    myCounter.increment()
    println(myCounter.current)
  }
}
