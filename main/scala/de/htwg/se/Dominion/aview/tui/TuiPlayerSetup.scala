package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.{Board, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiPlayerSetup(controller: Controller, tui: Tui, amount:Int) extends Observer with State {
  var playerArray: Array[String] = Array("")
  override def processInputLine(input: String): Unit = {
    playerArray = input.split(" ")
    println(playerArray.mkString("\n"))
    println(Board.toString())
    for (i <- 0 until amount - 1) {
      Dominion.playerList += Player(playerArray(i))
    }
    Dominion.playerList.toList
    controller.firstTurn(tui)
  }

  override def update: Unit = {
  }

}