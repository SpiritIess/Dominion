package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.gui.SwingGui
import de.htwg.se.Dominion.aview.tui.{Tui, TuiBuyPhase}
import de.htwg.se.Dominion.model.{Board, Card, CardSet, Pile, Player}
import de.htwg.se.Dominion.util.Observable

import scala.collection.mutable.ListBuffer

case class RoundManager(controller: Controller){

  def processCardEffect(tui:Tui, player: Player, index: Int):Unit = {
    println("In processCardEffect!\n")
    val (handList, playerDrawPile) = player.hand.handCards(index - 1).processEffect(index - 1, player)
    player.hand = handList
    player.playerDrawPile = playerDrawPile
    controller.notifyInController
    if (player.mayPlayAction == 0) {
      //print(s"No more Actions available for ${player.name}, moving to Buying-Phase, please press 'Enter' to confirm\n")
      actionToBuyPhase(tui, player)
    }
  }

  def processBuy(tui:Tui, player: Player, card: Card):Unit = {
    println("IN processBuy!\n")
    if (player.handValue >= card.cost) {
      Pile.piles = Pile.piles + (card -> (Pile.piles(card) - 1))
      controller.putOnDiscardPile(player, card)
      player.handValue -= card.cost
      player.mayBuy -= 1
      controller.notifyInController
    } else {
      println("Not enough money in hand, please choose a different card!\n")
    }
    if (player.mayBuy <= 0) {
      println("no buys left, next players turn!\n")
      player.playerDiscardPile = player.playerDiscardPile.discardCards(player.hand.handCards)
      controller.callNextPlayer(tui, player)
    } else {
//      print(Board.toString())
      //println(s"Player ${player.name}, has ${player.handValue} money, which card/s do you want to buy (one by one)?\n")
      controller.notifyInController
    }
  }

  def actionToBuyPhase(tui:Tui, player: Player): Unit = {
    println("Ending Action-Phase. Beginning Buy-Phase.\n")
    player.handValue += player.hand.countGold()
    controller.turnState = TurnState.buyingPhase
    tui.state = TuiBuyPhase(controller, tui, player)
    //println(Board())
    //println(s"Player ${player.name}, has ${player.handValue} money, which card/s do you want to buy (one by one)?\n")
    controller.notifyInController
  }


  def processCommand(turnState: TurnState.Value, tui: Tui, player: Player,
                     index:Int, card: Card = CardSet.copperCard): (ListBuffer[Player],TurnState.Value) = {
    if (turnState == TurnState.actionPhase) {
      processCardEffect(tui, player, index)
    } else if (turnState == TurnState.buyingPhase) {
//      actionToBuyPhase(tui:Tui, player: Player)
      processBuy(tui, player, card)
    }
    (Dominion.playerList, turnState)
  }





}
