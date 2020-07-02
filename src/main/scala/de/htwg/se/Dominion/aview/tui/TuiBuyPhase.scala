package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.{Controller, TurnState}
import de.htwg.se.Dominion.model.{Board, Card, CardSet, DiscardPile, Pile, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiBuyPhase(controller : Controller, tui:Tui, player: Player) extends Observer with State {
  //controller.add(this)
  override def update: Boolean = {
    printBoard
    true
  }

  def printBoard:Unit = {
    val boardString = Board.toString
    println(boardString)
  }

  override def processInputLine(input: String): Unit = {
    if (controller.turnState == TurnState.buyingPhase) {
      player.mayBuy -= 1
      var card: Card = CardSet.copperCard
      input match {
        case "1" => card = CardSet.copperCard
        case "2" => card = CardSet.silverCard
        case "3" => card = CardSet.goldCard
        case "4" => card = CardSet.propertyCard
        case "5" => card = CardSet.dukedomCard
        case "6" => card = CardSet.provinceCard
        case "7" => card = CardSet.gardenCard
        case "8" => card = CardSet.moatCard
        case "9" => card = CardSet.villageCard
        case "10" => card = CardSet.lumberjackCard
        case "11" => card = CardSet.smithyCard
        case "12" => card = CardSet.moneyLenderCard
        case "13" => card = CardSet.marketCard
        case "14" => card = CardSet.adventurerCard
        case "15" => card = CardSet.labCard
        case "16" => card = CardSet.funFairCard
      }
      if (player.handValue >= card.cost) {
        Pile.piles = Pile.piles + (card -> (Pile.piles(card) - 1))
        controller.putOnDiscardPile(player, card)
        player.handValue -= card.cost
      } else {
        println("Not enough money in hand, please choose a different card!\n")
      }
      if (player.mayBuy == 0) {
        println("no buys left, next players turn!\n")
        player.playerDiscardPile = player.playerDiscardPile.discardCards(player.hand.handCards)
        controller.callNextPlayer(tui, player)
      } else if (player.mayBuy > 0) {
        print(Board.toString())
        println(s"Player ${player.name}, has ${player.handValue} money, which card/s do you want to buy (one by one)?\n")
      }
    } else {
      println("trying to buy in a phase different from buying-phase!\n")
    }
  }
}
