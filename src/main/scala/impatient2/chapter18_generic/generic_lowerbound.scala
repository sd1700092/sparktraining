package impatient2.chapter18_generic

object generic_lowerbound extends App {

  class Pair[T](val first: T, val second: T) {
    def replaceFirst[R >: T](newFirst: R) = new Pair[R](newFirst, second)
  }

  val p = new Pair("Fred", "Brooks")
  private val res: Pair[Object] = p.replaceFirst(new Object)
  println(res)
}
