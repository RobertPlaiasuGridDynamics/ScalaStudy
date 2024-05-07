package oop

import scala.language.postfixOps

object OOBasics extends App{
  /*
  val person: Person = new Person("Mary", 35, "Inception")
  println(person learnsScala)
  println(person + "the rockstar" name)
  println(+person age)
  println(person(2))
   */
  val listOfIntegers : MyList[Int] = new Cons[Int](2,new Cons(3,Empty))
  println(listOfIntegers.toString)

  println(listOfIntegers.filter(x => x % 2 == 0)
  .toString)

  println(listOfIntegers.map(_*2))

  println(listOfIntegers.flatMap(x => new Cons(x,new Cons(x+1,Empty))).toString)

  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x,y) => y -x))

  val anotherList = new Cons[Int](6,new Cons[Int](4,Empty))



  println(listOfIntegers.zipWith(anotherList,  _ * _ ))

  println(listOfIntegers.fold(0,_+_))

  val forCombinations = for {
    n <- listOfIntegers
  } yield n * 2
  println(forCombinations)
}

class Writer(firstName : String,surname : String,val year : Int)
{
  def fullName() : String = firstName + " " + surname
}

class Novel(name : String,yearOfRelease : Int, author : Writer)
{
  def authorAge(): Int = yearOfRelease - author.year

  def isWrittenBy: String = author.fullName()
  
  def copy(newYearOfRelease : Int) = new Novel(name, newYearOfRelease, author)
}

class Counter(number : Int)
{
  def current : Int = number
  
  def increment() : Counter = new Counter(number+1)
  def increment(value : Int) : Counter = new Counter(number+value)

  def decrement(): Counter = new Counter(number - 1)
  def decrement(value: Int): Counter = new Counter(number - value)


}
