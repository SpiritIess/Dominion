package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.TurnState
import de.htwg.se.Dominion.controller.controllerComponent.Controller
import de.htwg.se.Dominion.model.cardComponent.{Card, CardSet}
import de.htwg.se.Dominion.model.playerComponent.Player
import de.htwg.se.Dominion.model.Board
import de.htwg.se.Dominion.util.Observer

case class TuiBuyPhase(controller : Controller, tui:Tui, player: Player) extends State {
  //controller.add(this)
//  override def update: Boolean = {
//    printBoard
//    true
//  }
//
//  def printBoard:Unit = {
//    val boardString = Board.toString
//    println(boardString)
//  }

  override def processInputLine(input: String): Unit = {
    if (controller.turnState == TurnState.buyingPhase) {
//      player.mayBuy -= 1
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
      controller.buyCard(tui, player, card)
//      controller.roundManager.processBuy(tui, player, card)
    } else {
      println("trying to buy in a phase different from buying-phase!\n")
    }
  }
}
