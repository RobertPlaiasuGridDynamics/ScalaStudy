package functional

import scala.util.Random

object OptionsFunctional extends App{
  val config :Map[String,String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  def tryConnection(host : Option[String], port : Option[String]) : Unit  ={


    val connection = host.flatMap(h => port.flatMap(p => Connection(h,p)))

    val connectionState = connection.map(c => c.connection)

    println(connectionState)
    connectionState.foreach(println)
  }

  {
    val host = config.get("host")
    val port = config.get("port")
    tryConnection(host, port)
  }

  config.get("host")
    .flatMap(host => config.get("port").flatMap(port => Connection(host,port))
      .map(connection => connection.connection))
    .foreach(println)


  val connectionState = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connection

  connectionState.foreach(println)
}

class Connection {
  def connection = "connected"
}

object Connection{
  val random = new Random(System.nanoTime())
  def apply(host : String,port : String): Option[Connection] ={
    if(random.nextBoolean()) Some(new Connection)
    else None
  }
}
