package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.controller.TurnState
import de.htwg.se.Dominion.model.{CardSet, Hand, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiActionPhase(controller : Controller, tui: Tui, player:Player) extends Observer with State {
  controller.add(this)
  println(player.hand.toString, "\n")
  controller.startTurn

  override def processInputLine(input: String): Unit = {
    if (controller.turnState == TurnState.actionPhase) {
      val inputNumber = input.toInt
      if (inputNumber > 0 && inputNumber <= player.hand.handCards.length) {
        controller.play(tui, player, inputNumber)
      } else if (inputNumber == 0) {
        controller.play(tui, player, inputNumber)
      } else {
        println("bad input, please type in the number of the wanted card on your keyboard and confirm with 'Enter'.\n")
      }
    }
  }

  override def update: Boolean = {
    true
  }

}