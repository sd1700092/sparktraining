package impatient2.chapter6

class Point private (val x: Int, val y: Int) {

}

object Point {
  def apply(x: Int, y: Int): Unit = {
    new Point(x, y)
  }


}