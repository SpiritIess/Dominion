package de.htwg.se.Dominion

import de.htwg.se.Dominion.model.{Board, Card, CardSet, Player, Tui}

object Dominion {
  def main(args: Array[String]): Unit = {
    val tui = Tui()
    tui.printTui()
  }
}