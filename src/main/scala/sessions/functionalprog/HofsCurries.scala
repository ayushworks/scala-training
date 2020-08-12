package sessions.functionalprog

object HofsCurries extends App {

  //lets write a complex function
  def complexFunction: (Int, (String,(Int => Boolean) => Int)) => (Int => Int) = ???

  // HOF: any function that takes a function as input argument
  // or return a function as result

  // function that applies another function n times over a value
  // nTimes(f,n,x)
  // nTimes(f,3,x) => f(f(f(x)))
  // nTimes(f,3,x) => nTimes(f, 2, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n<=0) x
    else nTimes(f, n-1,f(x))
  }

  val plusOne = (x: Int) => x+1
  println(nTimes(plusOne, 10, 1))

  // this incrementor function might look like an overkill
  // but this style of thinking will come over time with practise
  // and will be the basis of thinking like a FP

  // curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x+y
  //receives an Int and returns another function

  val add3 = superAdder(3) // y => 3+y // another lambda
  println(add3(10))
  println(superAdder(3)(10))

  // another scala syntax for Functions with multiple paramlist
  def curriedFormatter(f: String)(x: Double): String = f.format(x)

  // so we defined a function with multiple param list
  // now i can create mutliple formats

  val standardFormats: Double => String = curriedFormatter("%4.2f")
  // standard format is a valid function now.
  // we can call it with any value

  // we can have other formats also
  val preciseFormat: Double => String = curriedFormatter("%16.8f")

  println(standardFormats(Math.PI))
  println(preciseFormat(Math.PI))

  // BE SURE TO SPECIFY THE TYPES FOR THE SMALLER FUNCTIONS DEFINED USING CURRIED FUNCTION
  // OTHERWISE COMPILE WILL GIVE AN ERROR
}
