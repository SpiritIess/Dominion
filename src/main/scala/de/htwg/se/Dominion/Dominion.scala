package de.htwg.se.Dominion

import de.htwg.se.Dominion.aview.Tui
import de.htwg.se.Dominion.model.{Board, Card, CardSet, Hand, Pile, Player, PlayerDrawPile}

object Dominion {
  val tui = new Tui

  def main(args: Array[String]): Unit = {
    var input: String = ""
    var test = 0

    do {
      tui.printTui()
      input = scala.io.StdIn.readLine()
      test = tui.processInputLine(input)
      //      println("ProcessInput Return: " + test)
    } while (input != "q")





  }
}