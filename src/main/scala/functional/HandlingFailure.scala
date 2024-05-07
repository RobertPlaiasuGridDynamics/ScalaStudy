package functional

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App{
  val aSucces = Success(3)
  val aFailure = Failure(new RuntimeException("Fail"))

  println(aSucces)
  println(aFailure)

  def unsafeMethod : String = throw new RuntimeException("UNSAFE")


  val potentialFailure = Try(unsafeMethod)
  println(potentialFailure)


  val anotherPotentialFailure = Try {

  }

  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod() : String = "A valid result"
  val fallBackTry = Try(unsafeMethod).orElse(Try(backupMethod()))
  println(fallBackTry)

  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page : String) = println(page)



  class  Connection {
    def get(url : String) : String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html> ... </html>"
      else throw new RuntimeException("Connection Interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host : String, port : String) : Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

  val safeConnection = Try(HttpService.getConnection(hostname,port))
  val safePage = safeConnection.flatMap(c => Try(c.get(hostname)))
  safePage.foreach(renderHTML)
}
