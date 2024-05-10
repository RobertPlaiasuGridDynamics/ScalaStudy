package advanced.concurency

import scala.collection.mutable

object ConsumerProducer {
  def main(args : Array[String]) ={
    // multiProdCons(3,3)
    
    testNotifyAll()
  }

  def multiProdCons(nConsumers : Int,nProducers : Int) : Unit = {
    val buffer : mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3
    (1 to nConsumers).foreach(i => new Consumer(i,buffer).start())
    (1 to nProducers).foreach(i => new Producer(i,buffer,capacity).start())
  }


  def testNotifyAll() : Unit = {
    val bell = new Object

    (1 to 10).foreach(i => new Thread(() => {
      bell.synchronized {
        println(s"thread $i: waiting")
        bell.wait()
        println(s"thread $i : hooray!")
      }
    }).start())


    new Thread(() => {
      Thread.sleep(2000)
      println("announcer : Rock'n roll!")
      bell.synchronized {
        bell.notifyAll()
      }
    }).start()

    testNotifyAll()
  }
}
