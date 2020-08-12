package sessions.functionalprog

object MapFlatMapFilterFor extends App {

  // introduce map, flatMap , filter for scala collections

  val list = List(1,2,3) //standard library list implementation
  // list is created by calling the apply method on the companion object
  println(list)

  println("head - " + list.head)
  println("tail - " + list.tail)

  // 1.map method
  /**
  def map[B](f: (A) => B): List[B]
  Builds a new list by applying a function to all elements of this list.

   B : the element type of the returned list.

   f : the function to apply to each element.
   returns a new list resulting from applying the given function f to each element of this list and collecting the results.
   */
  val incrementOne = (x: Int) => x+1
  println(list.map(incrementOne))
  println(list.map(x => x+1)) // same as above
  println(list.map(_ +1))

  // 2. filter method
  /**
   * def filter(p: (A) => Boolean): List[A]
   * Selects all elements of this list which satisfy a predicate.
   *
   * p : the predicate used to test elements.
   *
   * returns : a new iterator consisting of all elements of this list that satisfy the given predicate p. The order of the elements is preserved.
   */
  val isEven = (x: Int) => x%2==0
  println(list.filter(isEven))
  println(list.filter(x => x%2==0)) //same as above
  println(list.filter(_ % 2 ==0))

  //3. flatMap
  /**
   * def flatMap[B](f: (A) => IterableOnce[B]): List[B]
   * Builds a new list by applying a function to all elements of this list
   * and using the elements of the resulting collections.
   *
   * B : the element type of the returned collection.
   *
   * f : the function to apply to each element.
   *
   * returns a new list resulting from applying the given collection-valued function f to each element of this list
   * and concatenating the results.
   */

  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair)) // concatenation of List(1,2) List(2,3) List(3,4)

  //exercise - print all combinations b/w two lists
  val number = List(1,2,3,4)
  val characters = List('a','b','c','d')
  //generate a list List("a1","a2"..."b1","b2"....."d4")
  //imperative programming - use two loops

  val combined = number.flatMap(n => characters.map(c => ""+c+n)) //transform loops into map / flatMap
  println(combined)

  // the above is how you do 'iterations'

  //4. foreach
  /**
   * def foreach[U](f: (A) => U): Unit
   * Apply f to each element for its side effects
   */
  list.foreach(println)

  // scala shorthand for chains, because chains are harder to read and understand
  val forCombinations = for {
    num <- number
    c <- characters
  } yield (""+c+num)

  // the above expression is converted by the scala compiler to a chain of map and flatmaps
  println(forCombinations)

  //suppose we want to generate result only for even numbers
  //val combined = number.filter(x => x%2==0).flatMap(n => characters.map(c => ""+c+n))

  // same can be acheived in for comprhension using guards
  //guards in for comprehension
  val forCombinations1 = for {
    num <- number if num%2==0
    c <- characters
  } yield (""+c+num)

  println(forCombinations1) //basically a filter before the flatMap call

  //syntax overload / using body with curly braces
  list.map{ x =>
    x *2
  }


}
