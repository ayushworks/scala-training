package sessions.functionalprog

object TuplesMaps extends App {

  //tuples are finite ordered "lists"
  val aTuple: (Int, String) = new Tuple2(2,"hello world")

  //paranthesis are syntax sugars for Tuple2

  //other ways to create is
  val aTuple2: (Int, String) = (2,"hello world") //using the apply method

  // tuples can be used to group atmost 22 values
  // why 22 - because they are used in conjection with Function  types which are also till 22

  // operations on tuples
  println(aTuple._1) //access element using _
  println(aTuple.copy(_2 = "bye")) //copy methods just like case class
  println(aTuple.swap) //swaps the elements

  //Maps
  //KEY-VALUES collections
  val aMap : Map[String, Int] = Map() //calling the apply method

  val phoneBook = Map(("Jim", 555),("Dainel", 666)) // they way to populate is by sepcifying tuples

  // a clearrerr sytax
  val phoneBook2 = Map("Jim" -> 555,"Daniel" -> 666)
  // a -> b is a syntax sugar for (a,b)

  println(phoneBook2)

  //operations on map
  println(phoneBook2.contains("Jim"))
  println(phoneBook2("Jim")) //apply method retrieves the value
  //println(phoneBook2("Mary")) //Map.apply with no key that exists will throw an exception

  //we can use .withDefaultValue on the map to overcome this issue

  // add a pair
  val newEntry = "Mary" -> 999
  val newPhoneBook = phoneBook2 + newEntry
  println(newPhoneBook)

  // functions on map
  //map, flatMap and filter
  println(newPhoneBook.map(pair => pair._1.toLowerCase -> pair._2)) //basically lowercase the keys

  //group by
  val name = List("Bob","John","Jim","Mary")
  println(name.groupBy(name => name.charAt(0))) //group all elements by charAt(0)

}
