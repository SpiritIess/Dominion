package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.{Controller, GameState, TurnState}
import de.htwg.se.Dominion.model.{Board, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiPlayerSetup(controller: Controller, tui: Tui, amount:Int) extends Observer with State {
  //controller.add(this)
  //println("type in the names of the players, using a space as seperator\n")
  override def processInputLine(input: String): Unit = {
    controller.updatePlayerList(input)
    controller.callNextPlayer(tui, Dominion.playerList(amount - 1))
  }

  override def update: Boolean = {
    println(Dominion.playerList.toString())
    true
  }

}