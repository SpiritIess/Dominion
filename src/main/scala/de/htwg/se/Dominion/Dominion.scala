package de.htwg.se.Dominion

import de.htwg.se.Dominion.model.{Board, Card, CardSet, Player, Tui}

import scala.collection.mutable.ListBuffer

object Dominion {
  def main(args: Array[String]): Unit = {
    val tui = Tui()

    val players = tui.getPlayerList()



    tui.printTui()
  }
}