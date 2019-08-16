package impatient2.chapter5

class Person {
  private var privateAge = 0
  def age = privateAge
  def age_=(newValue: Int) = {
    if (newValue > privateAge) privateAge = newValue // Can’t get younger
  }
}

object Person {
  def main(args: Array[String]): Unit = {
    val fred = new Person
    fred.age = 5
    println(fred.age)
    fred.age = 2
    println(fred.age)
  }
}
