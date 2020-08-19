package sessions.patternmatching

import exercises.functionalprog.{MyCons, MyList, MyNil}

import scala.util.Random

object PatternMatch extends App {

  // highly enhanced version of switch statements

  val random = new Random
  val x = random.nextInt(10) // any number from 0 to 10
  val description = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "third"
    case _ => "more than 3" //default, matches anything
  }

  println(x)
  println(description)
  // the match / case block is called pattern matching
  // the idea is to match a value against multiple patterns
  // each pattern is written in a case statement

  // this looks like a swtich from java

  // properties of pattern match apart from ones similar to java

  // 1. decompose values
  case class Person(name: String, age: Int)
  val bob = Person("bob",26)
  val greeting = bob match {
    case Person(n, a) => s"Hi , my name is $n and i am $a years old"
    case _ => "Unknown"
  }

  // so if Bob is a person , the pattern match is able to deconstruct
  // to its constituent parts
  // once deconstructed or extracted we can use the values in the result expression

  println(greeting)

  // 2. providing guards in patterns
  val greeting1 = bob match {
    case Person(n, a) if a>25 => s"Sir , my name is $n and i am $a years old"
    case Person(n, a) => s"Hi , my name is $n and i am $a years old"
    case _ => "Unknown"
  }

  println(greeting1)

  // things to remember
  // 1. cases are matched in order
  // 2. if no case is matched

  val description1 = 42 match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "third"
    case _ => "more than 3" //default, matches anything
  }
  println(description1)

  // if no match happens then - scala.MatchError

  // 3. type of the pattern match expression
  // unification of all the expressions returned by all the pattern expressions

  // PR on sealed heirarchies
  sealed trait Animal
  case object Dog extends Animal
  case object Cat extends Animal

  val animal: Animal = Dog
  animal match {
    case Dog => "its a dog"
  }

  //if i build , then i get a compiler warning

  //4. pattern match works really well with case classes
  // because of the nice apply and unapply method


}
