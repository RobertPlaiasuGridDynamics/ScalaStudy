package oop

class Person(val name : String,val age: Int = 0,val movie : String) {

  def +(subName : String) = new Person(s"$name ($subName)",age,movie)

  def unary_+ = new Person(name,age+1,movie)

  def learns(language : String) = s"$name learns $language"

  def learnsScala = learns("Scala")

  def apply(watched : Int) = s"$name watched $movie $watched times"
}
