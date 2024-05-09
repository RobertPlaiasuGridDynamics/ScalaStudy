package advanced.exercises


class Lazy[+A](value : => A) {
  private lazy val internalValue = value
  def flatMap[B](f : (=> A) => Lazy[B]) : Lazy[B] = f(internalValue)
  def use : A = value
}

object Lazy {
  def apply[A](value : => A) : Lazy[A] = new Lazy[A](value)

}

object Monad extends App{

  val lazyInstance = Lazy {
    println("Today I don't feel")
    42
  }

  // println(lazyInstance.use)

  val flatMappedInstace = lazyInstance.flatMap(x => Lazy {
    10 * x
  })
  val flatMappedInstace2 = lazyInstance.flatMap(x => Lazy {
    10 * x
  })

  flatMappedInstace.use
  flatMappedInstace2.use
}
