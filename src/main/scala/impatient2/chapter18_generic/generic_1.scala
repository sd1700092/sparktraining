package impatient2.chapter18_generic

object generic_1 extends App {
  class Pair[T, S](val first: T, val second: S)
  val p = new Pair(42, "String")
  val p2 = new Pair[Any, Any](42, "String")
  val p3 = new Pair[Int, String](42, "String")
  println(p)
  println(p2)
  println(p3)

  def getMiddle[T](a: Array[T]) = a(a.length / 2)

  println(getMiddle(Array("Mary", "had", "a", "little", "lamb")))
}
