package pattern

object PatternsEverywhere extends App {
  try{

  } catch {
    case e : RuntimeException => "runtime"
    case npe : NullPointerException => "npe"
    case _ => "other exception"
  }

  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1,2),(3,4))
  val filterTuples = for {
    (first,second) <- tuples
  }yield first* second

  // this works in the tutorial but doesn't work for me
  /*
  val tuple : (Int,Int,Int) = (1,2,3)
  val (a,b,c) = tuples
  println(b)
  */
  val x = List(1, 2, 3)

  val t = x match {
    case List(a, b, c) => (a, b, c);println(b)
  }

  val head :: tail = x
  println(head)
  println(tail)

  val mappedList = x.map {
    case v if v%2 == 0 => v+ " is even"
    case 1 => "one"
    case _ => "something else"
  }

  println(mappedList)
}
