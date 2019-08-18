package impatient2.chapter10

abstract class SavingAccount2 extends Account with Logger{
  def withdraw(amount: Double): Unit = {
    if (amount > balance)
      log("Insufficient funds")
  }
}

object SavingAccount2 {
  val acct = new SavingAccount2 with ConsoleLogger
}
