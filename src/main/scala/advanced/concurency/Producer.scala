package advanced.concurency

import scala.collection.mutable
import scala.util.Random

class Producer (id : Int,buffer : mutable.Queue[Int],capacity : Int) extends Thread {
  override def run()  :Unit = {
    val random = new Random()
    var i = 0

    while(true) {
      buffer.synchronized {
        while(buffer.size == capacity) {
          println(s"producer $id: buffer is full, waiting...")
          buffer.wait()
        }


        println(s"producer $id: producing " + i)
        buffer.enqueue(i)

        // for consumer
        buffer.notifyAll()

        i += 1
      }
    }
  }
}
