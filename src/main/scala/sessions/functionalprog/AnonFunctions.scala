package sessions.functionalprog

object AnonFunctions extends App {

  //so you already know about Function types

  val doubler = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1*2
  }

  println(doubler(4))

  val doublerEasy  = (x: Int) => x *2 //this is same as above
  // it instanstaties a new Function1
  // this is called an anonymous function or also known as lambda


  println(doublerEasy(4))

  // syntax rules

  //the type of doubler is an Int => Int
  // if the type of val is already provided then the paramter type is not mandatory
  val doublerMoreEasy : Int => Int = x => x*2
  // the compiler helps us writee shorter code

  println(doublerMoreEasy(4))

  //multiple parmeeters
  val adder : (Int,Int) => Int = (a:Int,b: Int) => a+b

  //when we have mutiple parameteres then parantheses are required for function types

  println(adder(4,5))

  // no parameters
  val justDoIt = () => 42 //no param lambda

  println(justDoIt) //this is the function itself
  println(justDoIt()) // this is the call

  //curly braces with lambda
  val stringToInt = { str: String =>
    str.toInt
  }

  // more syntax sugar

  val niceIncrementor : Int => Int = (x: Int) => x+1
  val niceIncrementor1 : Int => Int = _ + 1

  val niceAdder: (Int,Int) => Int = (a: Int, b: Int) => a+b
  val niceAdder1: (Int, Int) => Int = _ + _ //each _ is a different parameter
  // undersocre notation is hughly contextual, be careful



}
