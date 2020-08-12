package sessions.functionalprog

object ScalaCollections extends App {

  //scala offers both mutable and immutable collections
  // mutable collections can be updated in place
  // immutable collections never change

  // scala library has type aliases for collections
  val list : List[Int] = ???
  val map: Map[String, Int] = ??? // we are using immutable collections by default

  //immutable collections heirarchy - https://docs.scala-lang.org/overviews/collections/overview.html

  // Traversable is the mother of all scala collections
  // found in scala.colllection.immutable

  //Traversable is extended by Iterable which in turn is extended
  // by major collection types

  // Set - types of collection that do not contain duplicates
  // like hashset and sortedsets

  // Map make association between keys and values

  // Seq are types of collection that can be traverrsed in a set order

  // the mojor kinds of seq are indexed and liner seq

  // Indexed sequence
  // these can be quickly accessed using  like vector, string and range
  // here the elements can be accessed in constant time

  //linear sequence
  // only guanratee ordering of elements, no quick access
  //like list , stack, queue

  //mutable collections heirarchy - https://docs.scala-lang.org/overviews/collections/overview.html
  // heirarchy is very similar to immutable

  // in this course we will focus on immutable collections

}
