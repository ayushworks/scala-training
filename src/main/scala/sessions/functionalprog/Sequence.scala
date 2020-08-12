package sessions.functionalprog

import scala.util.Random

object Sequence extends App{

  //Seq
  // Sequences are a very general interface for two properties
  //1. well defined order
  //2. can be indexed

  //they have various operations
  // apply, length, revers, group, search etc..

  val seq = Seq(1,2,3,4)
  println(seq.getClass)
  println(seq)// sequence apply created a type of List internally

  // so the actual created type is List

  //utility methods
  println(seq.reverse)

  println(seq(2)) //call the apply method on a sequence actauuly fetches the value at the index

  println(seq ++ Seq(7,6,5)) // ++ is concatenation
  println(Seq(3,2,1,4).sorted) //works only when the type is Ordered

  // ranges
  val aRange: Seq[Int]  = 1 to 10 // 1 to 10 is a collection,which is a range
  aRange.foreach(println)

  val aRange1: Seq[Int]  = 1 until 10 // 10 exclusive
  aRange1.foreach(println)

  //Lists
  /**
   * sealed abstract class List[+A]
   *
   * case object Nil extends List[Nothing]
   *
   * case class ::[A](val head: A, val tail: List[A]) extends List[A]
   */

  //lists extend LinearSeq class
  // head, tail, isEmpty methods are fast O(1)
  // length, reverse O(n)

  val aList = List(1,2,3)
  //prepend
  val prepended = 0 :: aList // or 0 +: aList
  println(prepended)

  //append
  val appended  = aList :+ 4
  println(appended)

  // the way to remembers this is that : is always on the side of the list


  //utility methods

  val apples5 = List.fill(5)("apple") // fill is a curried function
  println(apples5)

  // mkstring
  println(apples5.mkString("-"))

  // Array
  // equivalent of simple java arrays
  // can be constructod with predefined length
  // can be mutated
  // indexing is fast

  val numbers: Array[Int] = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  // this allocates space for three elements without providing a value for them
  threeElements.foreach(println) // arrays are initialized with default values
  // for primtive like int, double we have defaults like zero
  // for reference type we have null as default

  // mutation of arrays
  numbers(2) = 0 // in place mutation of arrays
  println(numbers.mkString(" "))

  // the above mutation works because
  // numbers(2) = 0 is a syntax sugar for
  numbers.update(2,4) // update method is also special in Scala like apply
  println(numbers.mkString(" "))

  // arrays and sequences
  val numberSeq : Seq[Int] = numbers // implicit conversions (advanced topic)
  println(numberSeq)


  //vectors
  // fast and good for large size collections

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //same utils as other sequences

  //a performance test between vectors and lists

  val maxRuns = 1000
  val maxIndex = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val t = new Random
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.currentTimeMillis()
      collection.updated(t.nextInt(maxIndex), t.nextInt)
      System.currentTimeMillis() - currentTime
    }
    times.sum * 1.0 / maxRuns //average time for this collection to be updated at a random index
  }

  val numberlist : List[Int] = (1 to maxIndex).toList

  val numberVector: Vector[Int] = (1 to maxIndex).toVector


  println(getWriteTime(numberlist))

  println(getWriteTime(numberVector))


}
