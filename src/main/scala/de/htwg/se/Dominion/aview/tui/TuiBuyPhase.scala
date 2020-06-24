package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.{Controller, TurnState}
import de.htwg.se.Dominion.model.{Board, CardSet, DiscardPile, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiBuyPhase(controller : Controller, tui:Tui, player: Player) extends Observer with State {
  override def update: Unit = {
  }

  override def processInputLine(input: String): Unit = {
    player.hand.mayBuy -= 1
    input match {
      case "1" => controller.discardCard(player, CardSet.copperCard)
      case "2" => controller.discardCard(player, CardSet.silverCard)
      case "3" => controller.discardCard(player, CardSet.goldCard)
      case "4" => controller.discardCard(player, CardSet.propertyCard)
      case "5" => controller.discardCard(player,CardSet.dukedomCard)
      case "6" => controller.discardCard(player,CardSet.provinceCard)
      case "7" => controller.discardCard(player,CardSet.moatCard)
      case "8" => controller.discardCard(player,CardSet.villageCard)
      case "9" => controller.discardCard(player,CardSet.lumberjackCard)
      case "10" => controller.discardCard(player,CardSet.cellarCard)
      case "11" => controller.discardCard(player,CardSet.marketCard)
      case "12" => controller.discardCard(player,CardSet.militiaCard)
      case "13" => controller.discardCard(player,CardSet.mineCard)
      case "14" => controller.discardCard(player,CardSet.conversionCard)
      case "15" => controller.discardCard(player,CardSet.workshopCard)
    }
    if(player.hand.mayBuy == 0) {
      println("no buys left, next players turn!\n")
      player.playerDiscardPile.discardCards(player.hand.handCards)
      controller.nextPlayer(tui, player)
    } else if(player.hand.mayBuy > 0) {
      controller.printBoard
      println(s"Player ${player.name}, has ${player.hand.value} money, which card/s do you want to buy (one by one)?\n")
    }
  }
}
