package advanced.functional

object Concurrency {
  def inceptionThreads(maxThreads : Int, i : Int = 1) : Thread = {
    new Thread(() => {
      if (i < maxThreads) {
        val newThread = inceptionThreads(maxThreads, i + 1)
        newThread.start()
        newThread.join()
      }
      println(s"Hello from thread $i")
    })
  }

    def demoSleepFallacy() : Unit = {
      var message = ""
      val awesome= new Thread(() => {
        Thread.sleep(1000)
        message = "scala is awesome"
      })
      
      message = "Scala sucks"
      awesome.start()
      Thread.sleep(1000)
      println(message)
    }
    
    def main(args : Array[String]) : Unit = {
      demoSleepFallacy()
    }
}
