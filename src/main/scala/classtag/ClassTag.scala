package classtag

import scala.reflect.ClassTag

object ClassTag {
  def main(args: Array[String]): Unit = {
    /*
       * ClassTag:在运行时指定，在编译时无法确定的
       */
    def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

    /*
     *  执行结果：
     *  42
     *  13
     */
    mkArray(42, 13).foreach(println)

    /*
     * 执行结果：
     * Japan
     * Brazil
     * Germany
     */
    mkArray("Japan", "Brazil", "Germany").foreach(println)
  }
}
