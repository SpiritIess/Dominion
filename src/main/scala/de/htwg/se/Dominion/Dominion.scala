package de.htwg.se.Dominion

import de.htwg.se.Dominion.model.{Board, Card, CardSet, Player, Hand, Pile, PlayerDrawPile}

object Dominion {
  def main(args: Array[String]): Unit = {
    println("Dominion\n")
    val student1 = Player("Jakob Strakhof")
    val student2 = Player("Karsten Huber")
    println("Hello, " + student1.name + " and " + student2)

    val board = Board().toString
    print(board)
    println(student1.startingPile.toString)
    println(student1.startingHand.toString)
    println(student1.playerDrawPile.toString)

    println(student2.startingPile.toString)
    println(student2.startingHand.toString)
    println(student1.playerDrawPile.toString)
    ProcessEffect(student1, CardSet.moatCard)
  }
}