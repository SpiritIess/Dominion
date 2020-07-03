package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.gui.SwingGui
import de.htwg.se.Dominion.aview.tui.{Tui, TuiBuyPhase}
import de.htwg.se.Dominion.model.{Board, Card, Player}

import scala.collection.mutable.ListBuffer

case class RoundManager(controller: Controller) {

  def processCardEffect(tui:Tui, player: Player, index: Int):Unit = {
    println("In processCardEffect!\n")
    val (handList, playerDrawPile) = player.hand.handCards(index - 1).processEffect(index - 1, player)
    player.hand = handList
    player.playerDrawPile = playerDrawPile
    if (player.mayPlayAction == 0) {
      print(s"No more Actions available for ${player.name}, moving to Buying-Phase, please press 'Enter' to confirm\n")
      actionToBuyPhase(tui, player)
    }
  }

  def actionToBuyPhase(tui:Tui, player: Player): Unit = {
    println("Ending Action-Phase. Beginning Buy-Phase.\n")
    player.handValue += player.hand.countGold()
    controller.turnState = TurnState.buyingPhase
    tui.state = TuiBuyPhase(controller, tui, player)
    println(Board())
    println(s"Player ${player.name}, has ${player.handValue} money, which card/s do you want to buy (one by one)?\n")
  }

  def cleanUp(): Unit = {
  }

  def processCommand(turnState: TurnState.Value, tui: Tui, player: Player, index:Int): (ListBuffer[Player],TurnState.Value)= {
    if (turnState == TurnState.actionPhase) {
      processCardEffect(tui, player, index)
    } else if (turnState == TurnState.buyingPhase) {
      actionToBuyPhase(tui:Tui, player: Player)
//      controller.turnState = TurnState.cleanUp
    }
    (Dominion.playerList, turnState)
  }



}
