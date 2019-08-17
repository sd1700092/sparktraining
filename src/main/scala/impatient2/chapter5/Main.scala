package impatient2.chapter5

import scala.beans.BeanProperty

object Main {

  class ImprovedCounter {
    private var value = 0

    def increment(): Unit = {
      if (value < Int.MaxValue)
        value += 1
    }

    def current: Int = value
  }

  class BankAccount {
    private var privateBalance = 0

    def deposit(num: Int) {
      privateBalance = privateBalance + num
    }

    def withdraw(num: Int): Unit = {
      if (privateBalance - num > 0) {
        privateBalance = privateBalance - num
      }
    }

    def current: Int = privateBalance
  }

  class Time(val hours: Int, val minutes: Int) {
    def before(other: Time): Boolean = {
      if (hours < other.hours) {
        return true
      } else if (hours == other.hours) {
        return minutes==other.minutes
      } else {
        return false
      }
    }
  }

  class Student (@BeanProperty val name: String, @BeanProperty var age: Int) {
    if (age < 0) {
      age = 0
    }
  }

  class FullNamePerson(origStr: String) {
    val firstName: String = origStr.split(" ")(0)
    val lastName: String = origStr.split(" ")(1)
  }

  class Employee(val name: String, var salary: Double) {
    def this() { this("John Q. Public", 0.0) }
  }

  class ReWriteEmployee {
    var name: String = "John Q. Public"
    var salary: Double = 0.0


  }

  def main(args: Array[String]): Unit = {
    val time1 = new Time(23, 56)
    println(time1.hours)
    println(time1.minutes)
    val mingStudent = new Student("Ming", -1)
    println(mingStudent.age)
    val employee = new Employee()
    println(employee.name)
    println(employee.salary)
    val reWriteEmployee = new ReWriteEmployee
    println(reWriteEmployee.name)
    println(reWriteEmployee.salary)
  }
}
