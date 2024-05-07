package functional

object FlatMapFlatMapFor extends App{
  val list = List(1,2,3)
  println(list.head)
  println(list.tail)

  // Exercise

  val charList = List('a','b','c')
  println(list.flatMap(x => charList.map(y => y.toString + x.toString)))



  val forCombinations = for {
    n <- list if n % 2 == 0
    c <- charList
  } yield " " + c + n

  println(forCombinations)
}
