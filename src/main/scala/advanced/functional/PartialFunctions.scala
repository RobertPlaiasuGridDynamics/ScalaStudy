package advanced.functional

object PartialFunctions extends App {
  val aFucntion = (x : Int) => x +1

  val aFussyFunction = (x :Int) => {
    if(x == 1) 42
    else if(x == 2) 56
    else if(x == 5) 999
    else throw RuntimeException("Not applicable")
  }

  val aPartialFunction : PartialFunction[Int,Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function

  println(aPartialFunction(2))

  // println(aPartialFunction(999999))

  // PF utilities
  println(aPartialFunction.isDefinedAt(67))

  // lift to total function
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(87))

  val chain = aPartialFunction.orElse[Int,Int]{
    case 45 => 67
  }

  println(chain(2))
  println(chain(45))

  // Pf extends normal function

  val aTotalFunction : Int => Int = {
    case 1 => 99
  }

  val aMapList = List(1,2,3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }

  println(aMapList)


  // exercise

  val anotherFussyFunction = new PartialFunction[Int,Int] {
    override def apply(v1: Int): Int = v1 match {
      case 1 => 42
      case 2 => 56
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =  x==1 || x==2 || x == 5
  }

  val chatBot : PartialFunction[String,String] = {
    case "hello" => "Hello my name is Chat"
    case "orc" => "ORC!!!!!!!"
  }

  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)
}
