package de.htwg.se.Dominion.util


trait Observer {
  def update:Boolean
}
class Obersvable {
  var subscribers: Vector[Observer] = Vector()

  def add(s:Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => 0 == s)

  def notifyObservers: Unit = subscribers.foreach(o => o.update)

}
