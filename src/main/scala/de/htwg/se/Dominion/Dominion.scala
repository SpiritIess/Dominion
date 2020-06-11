package de.htwg.se.Dominion

import de.htwg.se.Dominion.aview.Tui
import de.htwg.se.Dominion.model.{Board, Card, CardSet, Hand, Pile, Player, PlayerDrawPile}

object Dominion {
  val tui = new Tui
  def main(args: Array[String]): Unit = {
    println("Dominion\n")
    val student1 = Player("Jakob Strakhof")
    val student2 = Player("Karsten Huber")
    println("Hello, " + student1.name + " and " + student2)

    val board = Board().toString
    print(board)
    /*
    println(student1.startingPile.toString)
    println(student1.startingHand.toString)
    println(student1.playerDrawPile.toString)

    println(student2.startingPile.toString)
    println(student2.startingHand.toString)
    println(student1.playerDrawPile.toString)
     */
    var input : String = ""
    do {
      input = readLine()
      tui.processInputLine(input,student1)
    } while (input != "q")

  }
}