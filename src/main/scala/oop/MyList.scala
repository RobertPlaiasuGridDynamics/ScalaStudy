package oop

import jdk.javadoc.internal.tool.Start

import scala.annotation.tailrec

abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty : Boolean
    def add[B >: A](element : B) : MyList[B]
    def printElements : String

    override def toString : String = "[" + printElements +"]"

    def map[B](transformer : A => B) : MyList[B]
    def flatMap[B](transformer : A => MyList[B]) : MyList[B]
    def filter(predicate : A => Boolean) : MyList[A]

    def foreach(method : A => Unit) : Unit
    def sort(method : (A,A) => Int) : MyList[A]

    def ++[B >: A] (list : MyList[B]) : MyList[B]

    def zipWith[B,C](list : MyList[B], method : (A,B) => C) : MyList[C]
    
    def fold[B >: A](value : B,method : (B,B) => B) : B 
}
