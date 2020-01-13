package impatient2.chapter10

import java.awt.Point

class OrderedPoint(x: Int, y: Int) extends java.awt.Point(x, y) with scala.math.Ordered[Point] {
  override def compare(that: Point): Int = {
    if (x < that.x) -10
    else if (x == that.x && y < that.y) -10
    else if (x == that.x && y == that.y) 0
    else 10
  }
}
