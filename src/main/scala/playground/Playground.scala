package playground

import oop.Person

import scala.annotation.tailrec
import scala.language.postfixOps

object Playground extends App {
  def greet(name : String,age: Int) : Unit = {
    println("Hi my name is " + name + " and my age is " + age);
  }

  def factorial(n : Int) : Int = {
    if( n<= 1)
      1
    else
    n * factorial(n-1)
  }

  def fibonacci(n :Int) : Int = {
    if(n <= 2) 1 else fibonacci(n-1) + fibonacci(n-2)
  }


  @tailrec
  def stringConcat(text : String,times : Int,finalString : String) : String = {
    if(times == 1) finalString
    else stringConcat(text,times-1,finalString + text)
  }

  @tailrec
  def isPrime(number: Int,start : Int,prime : Boolean =true): Boolean = {
    if (start <= 2) true
    else if(number % start == 0)
      false
    else
      isPrime(number,start -1, true && prime)
  }
  
}
