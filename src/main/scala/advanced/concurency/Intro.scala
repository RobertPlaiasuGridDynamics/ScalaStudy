package advanced.concurency

import java.util.concurrent.Executors

object Intro extends App {
  /*
  val aThread = new Thread(new Runnable {
    override def run() : Unit = println("running")
  })

  aThread.start()

   */

  val threadHello = new Thread(() => (1 to 5).foreach(x => println("hello")))
  val threadBye = new Thread(() => (1 to 5).foreach(x => println("bye")))
  threadHello.start()
  threadBye.start()


  val pool = Executors.newFixedThreadPool(10)
  pool.execute(() => println("something"))
  pool.execute(() => {
    Thread.sleep(1000)
    println("1 sec")
  })
  pool.execute(() => {
    Thread.sleep(1000)
    println("almost")
    Thread.sleep(1000)
    println("2 sec")
  })

  pool.shutdown()
  println(pool.isShutdown)
  
  
  
  
}
