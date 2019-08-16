package impatient2.chapter5

import scala.beans.BeanProperty

class Person {
  private var privateAge = 0
//  def age = privateAge
//  def age_=(newValue: Int) = {
//    if (newValue > privateAge) privateAge = newValue // Can’t get younger
//  }

  @BeanProperty var name: String = _
  private var age = 0

  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, age: Int) {
    this(name)
    this.age = age
  }
}

class Person1(val name: String, val age: Int) {
  println("Just constructed another person")
  def description = s"$name is $age years old"
}

class Person2(name: String, age: Int) {
  def description = s"$name is $age years old"
}

object Person {
  def main(args: Array[String]): Unit = {
    val fred = new Person
    fred.age = 5
    println(fred.age)
    fred.age = 2
    println(fred.age)
    val person = new Person1("aa", 1)()
    person
  }
}
