package impatient2.chapter8

abstract class Person(val name: String) {
  def id: Int
}

class Employee(name: String) extends Person(name) {
  def id = name.hashCode
}