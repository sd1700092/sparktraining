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
