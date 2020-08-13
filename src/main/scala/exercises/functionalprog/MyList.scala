package exercises.functionalprog


/**
 * Suppose we define our own list type
 * It has two sub types MyNil and MyCons
 * @tparam A
 */
sealed trait MyList[+A]  {

  def map[B](transformer: A => B): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]) : MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]
}

case object MyNil extends MyList[Nothing] {
  override def toString: String = "Nil"

  override def map[B](transformer: Nothing => B): MyList[B] = ???

  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = ???

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = ???

  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = ???
}

case class MyCons[+A](head: A, tail: MyList[A]) extends MyList[A] {

  //igonore how this works, we will learn pattern matching later
  override def toString: String = {
    val tailString = tail match {
      case MyNil => "Nil"
      case MyCons(h,t) => s"$h -> ${t.toString}"
    }
    s"$head -> $tailString"
  }

  override def map[B](transformer: A => B): MyList[B] = ???

  override def filter(predicate: A => Boolean): MyList[A] = ???

  override def ++[B >: A](list: MyList[B]): MyList[B] = ???

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = ???
}

object MyList {
  def apply[A](a: A*) : MyList[A] = if(a.length==0) MyNil
  else MyCons[A](a.head, apply(a.tail:_*))
}


object test  extends App {

  val myList = MyList(1,2,3)
  println(myList) //prints 1 -> 2 -> 3 -> Nil

  /**
   * 1. Implement the map method for MyList
    */
  println(myList.map(_ +1)) //should print 2 -> 3 -> 4 -> Nil

  /**
   * 2. Implement the filter method for MyList
   */
  println(myList.filter(_ %2 ==0)) // should print 2 -> Nil

  /**
   * 3. Implement the concatenation method
   */
  println(myList ++ MyList(4,5,6)) // should print 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> Nil

  /**
   * 4. Implement the flatMap method
   */
  println(myList.flatMap(x => MyList(x, x+1))) // should print 1 -> 2 -> 2 -> 3 -> 3 -> 4 -> Nil
}
