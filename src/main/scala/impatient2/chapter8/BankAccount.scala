package impatient2.chapter8

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance

  def currentBalance: Double = balance

  def deposit(amount: Double): Double = {
    balance += amount
    balance
  }

  def withdraw(amount: Double): Double = {
    balance -= amount
    balance
  }
}

class CheckingAccount(val initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double): Double = {
    super.deposit(amount - 1)
  }

  override def withdraw(amount: Double): Double = super.withdraw(amount + 1)
}

class SavingAccount(val initialBalance: Double) extends BankAccount(initialBalance) {
  def earnMonthlyInterest(): Unit = {
    SavingAccount.freeLeft = 0
    super.deposit(currentBalance * 0.03 / 12.0)
  }

  override def deposit(amount: Double): Double = {
    SavingAccount.freeLeft += 1
    if (SavingAccount.freeLeft <= 3)
    super.deposit(amount)
    else
    super.deposit(amount - 1)
  }

  override def withdraw(amount: Double): Double = {
    SavingAccount.freeLeft += 1
    if (SavingAccount.freeLeft <= 3)
      super.withdraw(amount)
    else
      super.withdraw(amount + 1)
  }
}

object SavingAccount {
  private var freeLeft: Int = 0
}