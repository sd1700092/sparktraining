package impatient2.chapter18_generic

object generic_2 extends App {

  class Pair[T <: Comparable[T]](val first: T, val second: T) {
//  class Pair[Ordering[T]](val first: T, val second: T) {
    def smaller: T = if (first.compareTo(second) < 0) first else second
  }

  val p = new Pair("Fred", "Brooks")
  println(p.smaller)
}
