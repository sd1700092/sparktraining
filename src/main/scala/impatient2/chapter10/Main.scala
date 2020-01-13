package impatient2.chapter10

object Main {
  def main(args: Array[String]): Unit = {
    val account1 = new SavingAccount2 with ConcreteLogger

    val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
    egg.translate(10, -10)
    egg.grow(10, 20)
  }
}
