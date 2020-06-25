package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.{Controller, TurnState}
import de.htwg.se.Dominion.model.{Board, CardSet, DiscardPile, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiBuyPhase(controller : Controller, tui:Tui, player: Player) extends Observer with State {
  controller.add(this)
  override def update: Unit = {
  }

  def printBoard:Unit = {
    val boardString = Board.toString
    println(boardString)
  }

  override def processInputLine(input: String): Unit = {
    player.hand.mayBuy -= 1
    input match {
      case "1" => controller.putOnDiscardPile(player, CardSet.copperCard)
      case "2" => controller.putOnDiscardPile(player, CardSet.silverCard)
      case "3" => controller.putOnDiscardPile(player, CardSet.goldCard)
      case "4" => controller.putOnDiscardPile(player, CardSet.propertyCard)
      case "5" => controller.putOnDiscardPile(player,CardSet.dukedomCard)
      case "6" => controller.putOnDiscardPile(player,CardSet.provinceCard)
      case "7" => controller.putOnDiscardPile(player,CardSet.moatCard)
      case "8" => controller.putOnDiscardPile(player,CardSet.villageCard)
      case "9" => controller.putOnDiscardPile(player,CardSet.lumberjackCard)
      case "10" => controller.putOnDiscardPile(player,CardSet.cellarCard)
      case "11" => controller.putOnDiscardPile(player,CardSet.marketCard)
      case "12" => controller.putOnDiscardPile(player,CardSet.militiaCard)
      case "13" => controller.putOnDiscardPile(player,CardSet.mineCard)
      case "14" => controller.putOnDiscardPile(player,CardSet.conversionCard)
      case "15" => controller.putOnDiscardPile(player,CardSet.workshopCard)
    }
    if(player.hand.mayBuy == 0) {
      println("no buys left, next players turn!\n")
      player.playerDiscardPile.discardCards(player.hand.handCards)
      controller.callNextPlayer(tui, player)
    } else if(player.hand.mayBuy > 0) {
      print(Board.toString())
      println(s"Player ${player.name}, has ${player.hand.value} money, which card/s do you want to buy (one by one)?\n")
    }
  }
}
