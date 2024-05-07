package pattern

import scala.util.Random

object PatternMatching extends App {
  val random =new Random()
  val x = random.nextInt(10)


  val description = x match {
    case 1 => "sadasads"
    case 2 => "hhhhhhhh"
    case 3 => "ppppppp"
    case _ => "tttttt"
  }
  println(x)
  println(description)
}
