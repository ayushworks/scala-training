package sessions.functionalprog

import scala.util.Try

object TryTry extends App {

  // Where Option[A] is a container for a value of type A that may be present or not,
  // Try[A] represents a computation that may result in a value of type A,
  // if it is successful, or in some Throwable if something has gone wrong

  /**
   * sealed abstract class Try[+A]
   *
   * case class Success[+A](a: A) extends Try[A]
   *
   * case class Failure[A](throwable: Throwable) extends Try[A]
   */

  def someForeignApi: String = "42"

  def callSomeApiAndCatchException: String = {
    try {
      someForeignApi
    }
    catch {
      case e: Exception => "handle errors"
    }
  }

  // Now, having this kind of exception handling code all over your code base
  // can become ugly very quickly and doesn't really go well with functional programming.
  // It's also a rather bad solution for applications with a lot of concurrency.
  // For instance, if you need to deal with an exception thrown by an Actor
  // that is executed on some other thread,
  // you obviously cannot do that by catching that exception –
  // you will want a possibility to receive a message denoting the error condition.
  //
  // Hence, in Scala, it's usually preferred to signify that an error has occurred
  // by returning an appropriate value from your function.

  def callSomeApiAndCatchException2: Try[String] = {
    Try(someForeignApi)
    //we are using the apply factory method on the Try companion object
    // This method expects a by-name parameter
    //  Inside that method, non-fatal exceptions are caught,
    //  returning a Failure containing the respective exception.
  }

  // Same functions as options
  // map, flatMap , filter, foreach

  // recovering from Failure

  // If you want to establish some kind of default behaviour in the case of a Failure,
  // you don't have to use getOrElse.
  // An alternative is recover, which expects a partial function and returns another Try.
  // If recover is called on a Success instance, that instance is returned as is.
  // Otherwise, if the partial function is defined for the given Failure instance, its result is returned as a Success.

  val result = Try(throw new NullPointerException).recover{
    case e: NullPointerException => 42
    case f: IllegalArgumentException => 43
  }

  println(result)
}
