package de.htwg.se.Dominion

import de.htwg.se.Dominion.model.{Board, Card, CardSet, Pile, Player, Tui}


object Dominion {
  val tui = new Tui

  def main(args: Array[String]): Unit = {
    var input: String = ""
    var test = 0
    tui.processInputLine("")
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")





  }
}