package impatient2.chapter10

trait RectangleLike {
  this: java.awt.geom.Ellipse2D.Double =>
  def translate(dx: Int, dy: Int):Unit = {
    setFrame(getX + dx, getY + dy, getWidth, getHeight)
  }
  def grow(h: Int, v: Int) {
    setFrame(getX, getY, getWidth + v, getHeight + h)
  }
}
