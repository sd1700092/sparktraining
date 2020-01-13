package classtag

import scala.reflect.ClassTag

object Extractor extends App {
  //  def extract[T](list: List[Any])(implicit tag: ClassTag[T]) =
//  def extract[T](list: List[Any]) =
//  Basically what happens is that if we provide the compiler with an implicit ClassTag, it will rewrite the condition(s) in pattern matching to use the given tag as an extractor.
  def extract[T: ClassTag](list: List[Any]) =
    list.flatMap {
      case element: T => Some(element)
      case _ => None
    }

  val list: List[Any] = List(1, "string1", List(), "string2")
  val result = Extractor.extract[String](list)
  println(result) // List(string1, string2)
}
