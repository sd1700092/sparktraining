package impatient2.chapter10

class SavingsAccount extends Account with ConsoleLogger {
  override def withdraw(amount: Double): Unit = {
    if (amount > balance) {
      log("Insufficient fund")
    } else {
      balance = balance - amount
    }
  }
}

abstract class absSavingsAccount extends Account with Logger {
  def withdraw(amount: Double): Unit = {
    if (amount > balance)
      severe("Insufficient funds")

  }
}
