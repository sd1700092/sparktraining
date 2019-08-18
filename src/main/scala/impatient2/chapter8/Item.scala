package impatient2.chapter8

abstract class Item {
  def price(): Double
  def description(): String
}

class SimpleItem extends Item {
  val price = 0.0934
  val description = "Description"
}

class Bundle(var contents: List[Item]) extends Item {
  override def price(): Double = contents.map(_.price()).sum

  override def description(): String = contents.map(_.description()).mkString("[", " ", "]")

  def add(item: Item): Unit = {
    contents :+= item
  }
}