package oop

import sun.net.NetworkClient

import scala.annotation.tailrec

object TupleAndMaps extends App {
    val phoneBook =  Map(("Jim",544),"Daniel" -> 789,("JIM",555)).withDefaultValue(-1)
    println(phoneBook)

    def add(network : Map[String,Set[String]],person: String) : Map[String,Set[String]] = {
      network + (person -> Set())
    }

  def friend(network : Map[String,Set[String]],a : String, b : String) : Map[String,Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)

    network + (a -> (friendA + b)) + (b -> (friendB + a))
  }

  def unfriend(network : Map[String, Set[String]], a : String, b : String) : Map[String,Set[String]] = {
    val friendA = network(a)
    val friendB = network(b)

    network + (a -> (friendA - b)) + (b -> (friendB -  a))
  }



  def remove(network : Map[String,Set[String]], person : String ) : Map[String,Set[String]] = {
    def removeAux(friends : Set[String],networkAcc : Map[String, Set[String]]) :  Map[String,Set[String]] =
      if(friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    val unfriended = removeAux(network(person),network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network : Map[String,Set[String]]= add(add(empty,"Bob"), "Mary")

  println(network)
  println(friend(network,"Bob","Mary"))
  println(unfriend(friend(network,"Bob","Mary"),"Bob","Mary"))
  println(remove(friend(network,"Bob","Mary"),"Bob"))

  val people = add(add(add(empty,"Bob"),"Mary"),"Jim")
  val jimBob = friend(people,"Bob","Jim")
  val testNet = friend(jimBob,"Bob","Mary")

  println(testNet)

  def nrFriends(network : Map[String,Set[String]], person : String) : Int = {
    if(!network.contains(person)) 0
    else network(person).size
  }

  println(nrFriends(testNet,"Bob"))
  println(nrFriends(testNet,"Mary"))


  def mostFriends(network : Map[String,Set[String]]) : String = network.maxBy(pair => pair._2.size)._1


  println(mostFriends(network))



  def nPeopleWithNoFriends(network:  Map[String,Set[String]]): Int = network.count(pair => pair._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network : Map[String,Set[String]],a: String,b : String) ={
    @tailrec
    def bfs(target : String, consideredPeople : Set[String], discordedPeople : Set[String]) : Boolean = {
      if(discordedPeople.isEmpty) false
      else {
        val person = discordedPeople.head
        if(person == target) true
        else if(consideredPeople.contains(person)) bfs(target,consideredPeople,discordedPeople.tail)
        else bfs(target,consideredPeople + person,discordedPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(),network(a) + a)
  }
  println(socialConnection(testNet,"Mary","Jim"))
  println(socialConnection(network, "Mary", "Bob"))
  
}
