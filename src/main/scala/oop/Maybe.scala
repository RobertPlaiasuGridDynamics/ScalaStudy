package oop

abstract class Maybe[+T] {
  def map[B](transformer: T => B): Maybe[B]

  def flatMap[B](transformer: T=> Maybe[B]): Maybe[B]

  def filter(predicate: T => Boolean): Maybe[T]
}

case object MaybeNot extends Maybe[Nothing] {
  def map[B](transformer: Nothing => B): Maybe[B] = MaybeNot

  def flatMap[B](transformer: Nothing => Maybe[B]): Maybe[B] = MaybeNot

  def filter(predicate: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}
case class Just[+T](value: T) extends Maybe[T]
{
  def map[B](transformer: T => B): Maybe[B] = Just(transformer(value))

  def flatMap[B](transformer: T => Maybe[B]): Maybe[B] = transformer(value)

  def filter(predicate: T => Boolean): Maybe[T] =
    if(predicate(value)) this
    else MaybeNot
}

object MaybeTest extends App {
  val just3 = Just(3)
  println(just3)



  println(just3.filter(x => x % 2 == 0))

  println(just3.map(x => x * 3))

  println(just3.flatMap(x => Just(x % 2 == 0)))
}
