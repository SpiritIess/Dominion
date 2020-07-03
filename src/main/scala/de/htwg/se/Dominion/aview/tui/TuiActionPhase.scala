package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.controller.TurnState
import de.htwg.se.Dominion.model.{CardSet, Hand, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiActionPhase(controller : Controller, tui: Tui, player:Player) extends Observer with State {
//  controller.add(this)
  println(player.hand.toString, "\n")


  override def processInputLine(input: String): Unit = {
    val inputNumber = input.toInt
    if (player.mayPlayAction > 0) {
      if (inputNumber >= 0 && inputNumber <= player.hand.handCards.length + 1) {
        controller.play(tui, player, inputNumber)
      } else {
        println("bad input, please type in the number of the wanted card on your keyboard and confirm with 'Enter'.\n")
      }
    } else {
      controller.turnState = TurnState.buyingPhase
    }

  }

  override def update: Boolean = {
    true
  }

}