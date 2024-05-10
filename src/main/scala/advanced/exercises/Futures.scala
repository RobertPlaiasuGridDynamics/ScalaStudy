package advanced.exercises

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Random, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
object Futures extends App{
  def fulfillImmediately[T](value : T): Future[T] = {
    Future(value)
  }

  def inSequence[A,B](first : Future[A],second : Future[B]) : Future[B] = {
    first.flatMap(_ => second)
  }

  def first[A](f : Future[A],g : Future[A]) : Future[A] = {
    val promise = Promise[A]

    def tryComplete(promise: Promise[A],result : Try[A]) = result match {
      case Success(r) =>try {
        promise.success(r)
      } catch {
        case _ =>
      }

      case Failure(t) => try {
        promise.failure(t)
      } catch{
        case _ =>
      }
    }


    f.onComplete(promise.tryComplete)
    g.onComplete(promise.tryComplete)

    promise.future
  }


  def last[A](f : Future[A],g : Future[A]) : Future[A] = {
    val bothPromise = Promise[A]
    val lastPromise = Promise[A]

    val checkAndComplete =  (result : Try[A]) =>
      if(!bothPromise.tryComplete(result))
        lastPromise.complete(result)

    f.onComplete(checkAndComplete)
    g.onComplete(checkAndComplete)

    lastPromise.future
  }

  val fast = Future {
    Thread.sleep(100)
    42
  }

  val slow = Future {
    Thread.sleep(200)
    45
  }

  first(fast,slow).foreach(println)
  last(fast,slow).foreach(println)

  Thread.sleep(1000)


  def retryUntil[A](action : () => Future[A],condition : A => Boolean) : Future[A] ={
    action().filter(condition).recoverWith {
      case _ => retryUntil(action,condition)
    }
  }


  val random = new Random()
  val action = () => Future {
    Thread.sleep(100)
    val nextValue = random.nextInt(100)
    println("generated " + nextValue)
    nextValue
  }

  retryUntil(action,(x: Int) => x < 10).foreach(result => println("value " + result))
  Thread.sleep(10000)
}
