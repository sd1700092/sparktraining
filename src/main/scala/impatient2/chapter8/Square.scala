package impatient2.chapter8

class Square(x: Int, y: Int, width: Int) extends java.awt.Rectangle {
  def this(width: Int) {
    this(0, 0, width)
  }

  def this() {
    this(0, 0, 0)
  }
}
