package sessions.functionalprog

object Options extends App {

  //Options are special type of "collections" in scala

  // they are meant to solve the problem of null references
  // and to solve the problem of NPE
  val string : String = null
  //println(string.length) throw's NPE

  // kind of guards/checks we put in the code
  if(string!=null){
    println(string.length)
  }

  // Option is a datatype that encapsulates the absence of a value
  // An option is a wrapper for a value that might be present or not present

  /**
   * sealed abstract class Option[+A]
   *
   * case class Some[+A](a: A) extends Option[A]
   *
   * case object None extends Option[Nothing]
   */

  // Some wraps a concrete value
  // None is a singleton for absent value

  // Options are present in scala standard library

  // Map uses options in get
  val map = Map("key" -> "value")
  //map("key")
  //map("unknown key") apply throws exception for keys not prresent
  println(map.get("key"))
  println(map.get("unknown key"))

  // List has many methods that return option
  val list: List[String] = List()
  println(list.headOption) // list.head throws exception

  // Creating your own options

  val myOption : Option[Int] = Some(1)
  val noValueOption: Option[Int] = None

  println(myOption)
  println(noValueOption)

  // Options were invented to deal with unsafe api's
  def unsafe: String = {
    //some code path  in the code return null
    null
  }

  val result = Some(unsafe) // Wrong
  println(result)

  val correctResult = Option(unsafe) // the apply in
  // the companion object takes care of producing the correct type- Some or None
  // by checking for null

  println(correctResult)
  // The whole point of Option is to make sure we dont do null checks ourselves
  // the Option datatype is supposed to take care of that

  // Designing SAFE api's
  // make your method return Option instead of returning null

  case class User(id: Int, firstName: String, lastName: String)

  object UserRepository {
    private val users = Map(1 -> User(1, "John", "Doe"),
      2 -> User(2, "Bill", "Sam"))
    def findById(id: Int): Option[User] = users.get(id)
  }
  // if we design our api;s this way then
  // the caller need not wrap the result in option

  // Functions

  //1. isEmpty and get
  val optionalUser : Option[User] = UserRepository.findById(1)
  println(optionalUser.isEmpty) // a test whether option contains a value or not

  println(optionalUser.get) //tries to retrive a value, but its unsafe,
  // if the option is null it throws
  // don't use this in general

  // 2. performing side effects on options

  // If you need to perform some side-effect only if a specific optional value is present,
  // the foreach method can be used

  UserRepository.findById(1).foreach(println)
  //The function passed to foreach will be called exactly once, if the Option is a Some, or never, if it is None.

  //3. map
  // Just as you can map a List[A] to a List[B],
  // you can map an Option[A] to an Option[B].
  // if your instance of Option[A] is defined, i.e. it is Some[A], the result is Some[B],
  // otherwise it is None.

  val userFirstName = UserRepository.findById(1).map(_.firstName)
  println(userFirstName)

  //4. flatMap
  // used to help with nested options
  case class Employee(id: Int, firstName: String, age:Int, lastName: String, middleName: Option[String])

  def getEmployeeById(id: Int): Option[Employee] = {
    if(id ==1) Some(Employee(1, "John",23, "Doe", Some("D")))
    else None
  }

  val middleName: Option[Option[String]] = getEmployeeById(1).map(e => e.middleName)
  middleName // its an option[option[String]]
  val middleNameCorrect: Option[String] = getEmployeeById(1).flatMap(e => e.middleName)
  // if either employee is none or middle name is none we get none in overall result

  //5. filter
  // You can filter an option just like you can filter a list.
  // If the instance of Option[A] is defined, i.e. it is a Some[A],
  // and the predicate passed to filter returns true for the wrapped value of type A, the Some instance is returned.
  // If the Option instance is already None
  // or the predicate returns false for the value inside the Some, the result is None:

  println(getEmployeeById(1).filter(_.age>20))
  println(getEmployeeById(1).filter(_.age<20))
  println(getEmployeeById(2).filter(_.age>20))

  // 6. chaining options
  // you call orElse on an Option instance,
  // and pass in another Option instance as a by-name parameter.
  // If the former is None, orElse returns the option passed to it,
  // otherwise it returns the one on which it was called.
  println(Some(1) orElse Some(2))
  println(None orElse Some(3))

  // 7. orElse
  // if you simply want to provide a default value in case a given option is absent,
  // the getOrElse method is used

  val firstName: String = UserRepository.findById(1).map(_.firstName).getOrElse("no first Name")
  println(firstName)
}
