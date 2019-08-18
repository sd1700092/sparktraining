package impatient2.chapter8

abstract class Shape {
  def centerPoint(): Double
}

class Rectangle(val width:Double, val height: Double) extends Shape {
  override def centerPoint(): Double = ???
}

class Circle(val radius: Double) extends Shape {
  override def centerPoint(): Double = ???
}
