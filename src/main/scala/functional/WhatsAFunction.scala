package functional

import scala.language.postfixOps
import scala.runtime.Nothing$

object WhatsAFunction extends App{
   val doubler = new MyFunction[Int ,Int]{
     override def apply(element: Int) : Int = element*2
   }

   println(doubler(2))

   val stringToIntCoverter = new Function1[String,Int] {
     override def apply(string: String): Int = string.toInt
   }

   println(stringToIntCoverter("3") + 4)

   val adder: ((Int,Int) => Int) = new Function2[Int,Int,Int ]{
     override def apply(a: Int, b: Int) = a + b
   }

   // All scala functions are objects


   val concat = new Function2[String,String,String] {
     override def apply(v1: String, v2: String): String = v1+v2
   }

   println(concat("Ana ","are mere"))

  /*
   val functionNumber = new Function[Int,Int] {
     override def apply(number: Int): Int => Int number + 1
   }
   
   */
  val supperAdder : (Int => Int => Int) = (x) => (y) => x+y


  val adder3 = supperAdder(3)
  println(adder3(4))
  println(supperAdder(3)(4))
  
  def toCurry (method : (Int,Int) => Int) : Int => Int => Int = {
    (x : Int) => (y : Int) => method(x,y)
  }

  // sum of 2 numbers
  val sum1 = (x: Int,y: Int) => x+y

  // another curried version of 2 numbers
  val sum2 = (x : Int) => (y : Int) => x + y

  def fromCurry(method : Int => Int => Int) : (Int,Int) => Int = {
    (x,y) => method(x)(y)
  }
  println(toCurry(sum1)(2)(3))
  println(fromCurry(sum2)(2,2))

  val squareNumber = (x :Int) => x*x

  def compose[A,B,C](f : A => B, g :  C => A) :  C => B =(c : C) => f(g(c))

  def andThen[A,B,C](f : A => B, g : B => C) : A => C = (c : A) => g(f(c))

  println(compose(sum2(4),squareNumber)(3))

  println(andThen(sum2(2),squareNumber)(7))


}

trait MyFunction[A,B]{
  def apply(element: A): B
}
