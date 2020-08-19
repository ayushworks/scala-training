package sessions.patternmatching

import exercises.functionalprog.{MyCons, MyList, MyNil}

object AllPatterns extends App {

  // 1. constants

  val x: Any = "Scala"
  val constants = x match {
    case 1 => "number 1"
    case "Scala" => "Its scala"
    case true => "TRUTH"
    case AllPatterns => "siNGLETON object"
  }

  println(constants)

  //2. match anything

  //2.1 wildcard

  val matchAnything = x match {
    case _ => "whatever"
  }

  println(matchAnything)

  //2.2 variable
   val matchVariable = x match {
     case something => s"its $something"
   }

  println(matchVariable)

  //3. on tuples
  val aTuple = (1,2)
  val tupleMatch = aTuple match {
    case (1,1)  => "matched 1,1"
    case (_, 1)  => "matched something and 1"
    case (something, anything)  => s"matched to $something and $anything"
  }

  println(tupleMatch)

  //4. case classes = constructor pattern

  val myList: MyList[Int] = MyList(1,2,3)

  val listMatch = myList match {
    case MyNil => "empty"
    case MyCons(head, tail) => s"list with heed - $head and tail -  $tail"
  }


  println(listMatch)

  //case classses can also be nested

  val listMatch1 = myList match {
    case MyNil => "empty"
    case MyCons(head, MyCons(head1, tail)) => s"list with heed - $head and tail -  $head1 -> $tail"
  }

  println(listMatch1)

  // 5. List patterns
  val list = List(1,2,3,42)
  val standardMatch = list match {
    case List(1,_,_,_) => s"matched to a list of 4 things where 1 is the first elements"//extractor for list
    case List(1,_*) => //list of arbitraty length startting with 1
    case 1 :: List(_) => //infix pattern
    case List(1,2,3) :+ 42 => //infix patten
  }

  /**
   * This is how Option, Try and other types are also matched
   */


  //6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "found a list"
    case x: Int => "found an int"
  }

  println(unknownMatch)

  //7. name binding
  val nameBindingMatch = myList match {
    case nonEmptyList @ MyCons(_,_) =>  nonEmptyList.head //named binding, name can be used here
    case MyCons(1, rest @MyCons(head, tail)) => //name binding inside nested case classes
  }

  //8. multi patterns
  val multiPattern = myList match {
    case MyCons(2, _) |  MyCons(1, _) => println("matched multi-pattern")
    case _ => println("matched default")
  }

  // 9. if guards
  val multiPattern1 = myList match {
    case MyCons(x, _) if x==1 => println("matched multi-pattern")
    case _ => println("matched default")
  }


}
