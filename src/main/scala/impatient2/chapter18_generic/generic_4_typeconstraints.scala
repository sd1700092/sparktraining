package impatient2.chapter18_generic

object generic_4_typeconstraints extends App {
  // 表示T可以被隐式转换为带有Comparable接口的类型，比如Int隐式转换为RichInt
  class Pair[T](val first: T, val second: T)(implicit ev: T=> Comparable[T]) {
    def smaller: T = if (first.compareTo(second) < 0) first else second
  }

  val p = new Pair("Fred", "Brooks")
  println(p.smaller)
}
