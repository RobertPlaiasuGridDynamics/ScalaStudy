package advanced.concurency

object Deadlock extends App{
  val alex = new Friend("Alex")
  val mircea = new Friend("Mircea")

  // new Thread(() => alex.bow(mircea)).start()
  // new Thread(() => mircea.bow(alex)).start()

  new Thread(() => alex.pass(mircea)).start()
  new Thread(() => mircea.pass(alex)).start()
}
