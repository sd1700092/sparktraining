package impatient2.chapter5

import impatient2.chapter5.Network.Member

import scala.collection.mutable.ArrayBuffer

class Network {
  private val members = new ArrayBuffer[Member]

  def join(name: String) = {
    val m = new Member(name)
    members += m
    m
  }
}

object Network {
  class Member(val name: String) {
    val contacts = new ArrayBuffer[Member]
  }
}
