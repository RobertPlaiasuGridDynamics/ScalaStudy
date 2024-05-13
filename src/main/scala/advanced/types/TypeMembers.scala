package advanced.types

object TypeMembers extends App {
  trait MList{
    type A
    def head : A
    def tail : MList
  }
  /*
  class CustomList(hd : String,tl : CustomList) extends MList with ApplicableToNumbers{
    type A = String
    def head = hd
    def tail = tl
  }
 */
  class IntList(hd: Int, tl: IntList) extends MList {
    type A = Int
    def head = hd

    def tail = tl
  }

  trait ApplicableToNumbers {
    type A <: Number
  }
}
