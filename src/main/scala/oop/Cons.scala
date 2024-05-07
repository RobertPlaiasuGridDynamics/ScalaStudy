package oop

import scala.annotation.tailrec

case class Cons [+A](h: A,t : MyList[A]) extends MyList[A] {
  def head : A = h
  def tail : MyList[A] = t
  def isEmpty : Boolean = false
  def add[B >: A] (element : B ) : MyList[B] = new Cons(element,this)
  def printElements : String =
    if(t.isEmpty) "" + h
    else h.toString + " " + t.printElements

  def filter(predicate : A => Boolean) : MyList[A] =
    if (predicate(h)) new Cons(h , t.filter(predicate))
    else t.filter(predicate)

  def map[B] (transformer : A => B) : MyList[B] = Cons(transformer(h),t.map(transformer))


  def ++[B >: A](list : MyList[B]): MyList[B] = Cons(h,t ++ list)

  def flatMap[B](transformer : A => MyList[B]) : MyList[B] =
    transformer(h) ++ t.flatMap(transformer)
  
  def foreach(method : A => Unit) : Unit = {
    method(h)
    t.foreach(method)
  }

  def sort(method: (A, A) => Int): MyList[A] = {

    def insert(x : A,sortedList : MyList[A]) : MyList[A] =
      if(sortedList.isEmpty) new Cons(x,Empty)
      else if(method(x,sortedList.head) <= 0) new Cons(x,sortedList)
      else new Cons(sortedList.head,insert(x, sortedList.tail))

    val sortedTail = t.sort(method)
    insert(h , sortedTail)
  }


  def zipWith[B, C](list: MyList[B], method: (A, B) => C): MyList[C] =
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same length!")
    else new Cons(method(h,list.head),t.zipWith(list.tail,method))


  def fold[B >: A](value: B, method: (B,B) => B): B = {
    if(!this.isEmpty)
      t.fold(method(value,h),method)
    else value
  }

}
