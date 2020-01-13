package impatient2.chapter18_generic

object generic_6_classtag extends App {
  import scala.reflect._
  def makePair[T: ClassTag](first: T, second: T) = {
    val r = new Array[T](2); r(0) = first; r(1) = second; r
  }
}
