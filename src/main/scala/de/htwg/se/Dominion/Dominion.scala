package de.htwg.se.Dominion

import de.htwg.se.Dominion.model.{Board, Card, CardSet, Player}

object Dominion {
  def main(args: Array[String]): Unit = {
    println("Dominion\n")
    val student1 = Player("Jakob Strakhof")
    val student2 = Player("Karsten Huber")
    println("Hello, " + student1.name + " and " + student2)

    val board = Board().printBoard()
    print(board)
  }
}