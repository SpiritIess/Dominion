package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.{Board, Card, CardSet, DiscardPile, Player}
import de.htwg.se.Dominion.util.Observer

case class TuiBuyPhase(controller : Controller, tui:Tui, player: Player) extends Observer with State {
  //controller.add(this)
  override def update: Unit = {
  }

  def printBoard:Unit = {
    val boardString = Board.toString
    println(boardString)
  }

  override def processInputLine(input: String): Unit = {
    player.mayBuy -= 1
    var card : Card = CardSet.copperCard
    input match {
      case "1" => card = CardSet.copperCard
      case "2" => card = CardSet.silverCard
      case "3" => card = CardSet.goldCard
      case "4" => card = CardSet.propertyCard
      case "5" => card = CardSet.dukedomCard
      case "6" => card = CardSet.provinceCard
      case "7" => card = CardSet.moatCard
      case "8" => card = CardSet.villageCard
      case "9" => card = CardSet.lumberjackCard
      case "10" => card = CardSet.cellarCard
      case "11" => card = CardSet.marketCard
      case "12" => card = CardSet.militiaCard
      case "13" => card = CardSet.mineCard
      case "14" => card = CardSet.conversionCard
      case "15" => card = CardSet.workshopCard
    }
    controller.putOnDiscardPile(player, card)
    player.handValue -= card.cost
    if(player.mayBuy == 0) {
      println("no buys left, next players turn!\n")
      player.playerDiscardPile = player.playerDiscardPile.discardCards(player.hand.handCards)
      controller.callNextPlayer(tui, player)
    } else if(player.mayBuy > 0) {
      print(Board.toString())
      println(s"Player ${player.name}, has ${player.handValue} money, which card/s do you want to buy (one by one)?\n")
    }
  }
}
