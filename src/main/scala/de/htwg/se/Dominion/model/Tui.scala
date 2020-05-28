package de.htwg.se.Dominion.model

case class Tui() {
  def printTui(): Unit = {

    println("Dominion\n")
    val student1 = Player("Jakob Strakhof")
    val student2 = Player("Karsten Huber")
    println("Hello, " + student1.name + " and " + student2)

    val board = Board().toString()
    print(board)
  }
}
