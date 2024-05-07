package oop

import scala.annotation.tailrec
import scala.runtime.Nothing$

case object Empty extends  MyList[Nothing] {
  def head : Nothing = throw new NoSuchElementException()
  def tail : MyList[Nothing] = throw new NoSuchElementException()
  def isEmpty = true
  def add[B >: Nothing](element : B) : MyList[B] = new Cons(element,Empty)
  def printElements : String = ""

  def map[B](transformer : Nothing => B) : MyList[B] = Empty
  def flatMap[B](transformer : Nothing => MyList[B]) : MyList[B] = Empty
  def filter(predicate : Nothing => Boolean ) : MyList[Nothing] = Empty

  def ++[B >: Nothing](list : MyList[B]) = list

  def foreach(method: Nothing => Unit): Unit = ()

  def sort(method: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], method: (Nothing, B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  
  def fold[B >: Nothing](value: B, method: (B,B) => B): B = value
}


