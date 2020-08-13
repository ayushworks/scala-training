package exercises.functionalprog

object CurryExercise extends App {

  /**
   * 1. Implement a toCurry function
   * def toCurry(f :(Int,Int) => Int) : (Int => Int => Int)
   */
  def toCurry(f: (Int, Int) => Int) : (Int => Int => Int) =
    ???

  val superAdder = toCurry((x: Int,y: Int) => x+y)
  println(superAdder(3)(4)) //should print 7

  /**
   * 2. Implement a fromCurry function
   * def fromCurry(f :(Int => Int => Int)) : (Int, Int) => Int
   */

  def fromCurry(f:Int => Int => Int) : (Int, Int) => Int =
    ???

  val adder = fromCurry(x => y => x+y)
  println(adder(3,4)) //should print 7
}
