package sessions.functionalprog

import scala.Function1

object IntroFunctions extends App {

  //use functions as first class elements - use functions just like plain values

  // in object oriented world everything is an instance of an object


  val doubler = new Action[Int,Int] {
    override def apply(a: Int): Int = a*2
  }


  //because of the special treatment to apply methods
  //doubler instance acts as if it was a function
  println(doubler(2))

  // So doubler which is an instance of a function like class , can be called like a function


  // Scala supports these function types out of the box
  // Function1, Function2...Function22

  val doubler1 = new Function1[Int,Int] {
    override def apply(v1: Int): Int = v1*2
  }

  println(doubler1(3))

  //scala has upto function 22
  val adder: ((Int,Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }

  println(adder(3,4))
  // if we hover over the type of adder it is (Int, Int) => Int which is a syntax sugar for Function2

  //these are basically Function types. So (A,B) => R is a fucntion that takes two parameters and returns a result of type R

  // ALL SCALA FUNCTIONS ARE INSTANCES DERIVING FROM FUCTION[1..22] TYPE

  /**
   * Lets do some exercises
   *
   * 1. A function that takes two strings and concatenates them
   * 2. Define a function that takes an Int and returns another function that takes an int and returns an int
   */


  def concatenator: (String, String) => String = new Function2[String,String, String] {
    override def apply(v1: String, v2: String): String = s"$v1$v2"
  }

  println(concatenator("hello", "world"))

  //2 question
  // since it takes one parameter the type is function1
  // return type is also a fucntion1

  val superAdder: (Int => Function1[Int, Int]) = new Function[Int, (Int => Int)] {
    override def apply(x: Int): Int => Int = new Function[Int, Int] {
      override def apply(y: Int): Int = x+y
    }
  }

  val supperAdder3 = superAdder(3)

  println(supperAdder3(4))

  //this special function is called a curried function
  println(superAdder(3)(4))

  // curreid functions have the property that they can be called using multiple parameters lists
  // we will talk about HOF's and curried functions in later sections

}

//so we basically write classes and methods
trait Action[A,B] {
  def apply(a: A): B
}
