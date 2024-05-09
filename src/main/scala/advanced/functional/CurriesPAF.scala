package advanced.functional

object CurriesPAF extends App{
  val supperAdder : Int => Int => Int =
    x => y => x + y


  val add3 = supperAdder(3)

  println(add3(5))

  println(supperAdder(3)(5))

  def curriedAdder(x : Int)(y : Int) : Int = x + y

  val add4 : Int => Int  = curriedAdder(4)

  def inc(x : Int) = x +1
  List(1,2,3).map(x => inc(x))

  val add5 = curriedAdder(5) _

  val simpleAddFunction = (x : Int,y : Int) => x + y
  def simpleAddMethod(x: Int,y: Int) = x + y
  def curriedAddMethod(x : Int)(y: Int) = x + y

  val add7 = (x : Int) => simpleAddFunction(7, x)
  val add7_2 = simpleAddFunction.curried(7)

  val add7_3 = curriedAddMethod(7) _

  val add7_4 = curriedAddMethod(7)(_)


  val add7_5 = simpleAddMethod(7,_ : Int)


  def concatenator(a : String, b : String,c : String) = a +b +c
  val insertName = concatenator("Hello, I'm ", _ : String, " how are you ?")
  println(insertName("Ana"))

  val fillInTheBlanks = concatenator("Hello, ", _ : String, _ : String)
  println(fillInTheBlanks("Dan ","Ann"))


  def curriedFormatter(s : String)(number : Double) : String = s.format(number)

  val numbers = List(Math.PI,Math.E ,1,9.8,1.3e-12)
  val simpleFormat = curriedFormatter("%4.2f") _
  val seriousFormat = curriedFormatter("%8.6f") _
  val preciseFormat = curriedFormatter("%14.12f") _
  println(numbers.map(curriedFormatter("%14.12f")))


  def byName(n: => Int) : Int = n+1
  def byFunction(f : () => Int) = f()  +1


  def method : Int = 42
  def parentMethod() : Int = 42

  byName(23)
  byName(method)
  byName(parentMethod())
  // byName(() => 42)
  byName((() => 32)())

  // byFunction(method)
  byFunction(parentMethod)
  byFunction(() => 46)
  byFunction(parentMethod _ )
}
