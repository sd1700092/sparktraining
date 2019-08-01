package impatient2.chapter10

/*class ConsoleLogger extends Logger with Cloneable with Serializable {
  override def log(msg: String): Unit = {println(msg)}
}*/

trait ConsoleLogger extends Logger {
  def log(msg: String): Unit = {
    println(msg)
  }
}