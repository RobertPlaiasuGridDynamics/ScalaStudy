package advanced.exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean){

  def apply(e : A) : Boolean = contains(e)
  def contains(a : A) : Boolean
  def +(e : A) : MySet[A]
  def ++ (anotherSet : MySet[A]) : MySet[A]

  def map[B](f : A => B): MySet[B]
  def flatMap[B](f : A => MySet[B]) : MySet[B]
  def filter(predicate : A => Boolean) : MySet[A]
  def foreach(f : A => Unit) : Unit

  def -(elem :A):  MySet[A]
  def &(anotherSet : MySet[A]) : MySet[A]

  def --(anotherSet : MySet[A]) : MySet[A]

  def unary_! : MySet[A]
}

class EmptySet[A] extends MySet[A]{
  def contains(a: A): Boolean = false

  def +(e: A): MySet[A] = NonEmptySet[A](e,this)

  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  def map[B](f: A => B): MySet[B] = new EmptySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  def filter(predicate: A => Boolean): MySet[A] = this

  def foreach(f: A => Unit): Unit =()

  def -(elem: A): MySet[A] = this

  def &(anotherSet: MySet[A]): MySet[A] = this

  def --(anotherSet: MySet[A]): MySet[A] = this

  def unary_! : MySet[A] = new PropertyBasedSet[A](x => true)
}
class NonEmptySet[A](head : A,tail : MySet[A]) extends MySet[A] {
  def contains(a: A): Boolean = a == head || tail.contains(a)

  def +(e: A): MySet[A] =
    if( this.contains(e)) this
    NonEmptySet[A](e, this)

  def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

  def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f ) ++ f(head)

  def filter(predicate: A => Boolean): MySet[A] = {
    val filteredTail = tail filter predicate
    if predicate(head) then filteredTail + head
    else filteredTail
  }

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  def -(elem: A): MySet[A] = {
    if(head == elem) tail
    else tail - elem + head
  }

  def &(anotherSet: MySet[A]): MySet[A] = filter(x => anotherSet.contains(x))

  def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)

  def unary_! : MySet[A] = new PropertyBasedSet[A](x => !this.contains(x))

}

object MySet {
  def apply[A](values : A*) : MySet[A] = {
    @tailrec
    def build(valSeq : Seq[A], acc : MySet[A]) : MySet[A] ={
      if (valSeq.isEmpty) acc
      else build(valSeq.tail,acc + valSeq.head)
    }

    build(values.toSeq,new EmptySet[A])
  }
}

object TestMySet extends App {
  val s = MySet(1,2,3,4)
  s.foreach(println)
  println("other set")
  s + 5 foreach println

  println("other set")
  s + 5 ++ MySet(-1,-2) + 3 foreach println

  println("other set")
  s + 5 ++ MySet(-1,-2) + 3 flatMap(x => MySet(x , 10 * x)) filter(x => x % 2 == 0 )  foreach println

  val negative = !s
  println(negative(2))
  println(negative(5))

  val negativeEven = negative.filter(_ % 2 == 0)
  println(negativeEven(5))

  val negativeEven5 = negativeEven + 5
  println(negativeEven5(5))
}


class PropertyBasedSet[A](property : A => Boolean) extends MySet[A] {
  def contains(a : A) : Boolean = property(a)
  def +(e : A) : MySet[A] = new PropertyBasedSet[A](x => property(x) || x == e)
  def ++ (anotherSet : MySet[A]) : MySet[A] = {
    new PropertyBasedSet[A](x => property(x) || anotherSet(x))
  }

  def map[B](f : A => B): MySet[B] = politelyFail
  def flatMap[B](f : A => MySet[B]) : MySet[B] = politelyFail
  def filter(predicate : A => Boolean) : MySet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))
  def foreach(f : A => Unit) : Unit = politelyFail

  def -(elem :A):  MySet[A] = filter(x =>x != elem)
  def &(anotherSet : MySet[A]) : MySet[A] = filter(anotherSet)

  def --(anotherSet : MySet[A]) : MySet[A] = filter(!anotherSet)

  def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

  def politelyFail = throw new IllegalArgumentException("Really deep rabbit hole")
}
