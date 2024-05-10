package advanced.concurency

case class Friend(name: String) {
  def bow(other : Friend) : Unit = {
    this.synchronized {
      println(s"$this: I am bowing to my friend $other")
      other.rise(this)
      println(s"$this : my friend $other has risen")
    }
  }

  def rise(other : Friend) = {
    this.synchronized {
      println(s"$this : I am rising to my friend $other")
    }
  }

  var side = "right"
  def switchSide() : Unit = {
    if(side == "right") side = "left"
    else side = "right"
  }

  def pass(other : Friend) : Unit = {
    while(this.side == other.side){
      println(s"this : Oh, please $other friend pass")

      switchSide()
      Thread.sleep(1000)
    }
  }
}
