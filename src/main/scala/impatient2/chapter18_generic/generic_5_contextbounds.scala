package impatient2.chapter18_generic

object generic_5_contextbounds extends App {
  class Pair[T : Ordering](val first: T, val second: T) {
    def smaller(implicit ord: Ordering[T]): T =
      if (ord.compare(first, second) < 0) first else second
  }
}
