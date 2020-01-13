package impatient2.chapter10

import impatient2.chapter5.Main.BankAccount

trait Logger {
  def log(msg: String)
}

class ConsoleLogger extends Logger with Cloneable with Serializable {
  def log(msg: String): Unit = {
    println(msg)
  }
}

trait ConcreteLogger extends Logger{
  def log(msg: String): Unit = {
    println(msg)
  }
}

class SavingAccount extends BankAccount with ConcreteLogger {
  var balance = 10000

  override def withdraw(num: Int): Unit = {
    if (num > balance) log("Insufficient funds")
    else balance -= num
  }
}

abstract class SavingAccount2 extends BankAccount with Logger {
  var balance = 10000

  override def withdraw(num: Int): Unit = {
    if (num > balance) log("Insufficient funds")
    else balance -= num
  }
}

trait TimestampLogger extends Logger {
  abstract override def log(msg: String) { // Overrides an abstract method
    super.log(s"${java.time.Instant.now()} $msg") // Is super.log defined?
  }
}

trait ShortLogger extends Logger {
  val maxLength: Int // An abstract field
  abstract override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg
      else s"${msg.substring(0, maxLength - 3)}...")
  }
}

class SavingAccount3 extends BankAccount with ConcreteLogger with ShortLogger {
  var balance = 10000
  override val maxLength: Int = 20
  override def withdraw(num: Int): Unit = {
    if (num > balance) log("Insufficient funds")
    else balance -= num
  }
}