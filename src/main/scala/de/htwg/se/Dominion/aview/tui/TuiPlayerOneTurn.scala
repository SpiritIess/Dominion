package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.aview.tui.TuiTwoPlayers
import de.htwg.se.Dominion.model.Player

case class TuiPlayerOneTurn(controller : Controller, tui:Tui) extends State {
  val player: Player = Dominion.playerList.head
  override def processInputLine(input: String): Unit =
    input match {
      case "1" =>
        val (handList,playerDrawPile) = player.hand.handCards.head.processEffect(0, player.hand, player.playerDrawPile)
        player.hand = handList
        player.playerDrawPile = playerDrawPile

      case _ => println("bad input choose a card (only most-left one can be played)")
      /*case "2" => Dominion.playerList(0).handList(1).processEffect(1,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "3" => Dominion.playerList(0).handList(2).processEffect(2,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "4" => Dominion.playerList(0).handList(3).processEffect(3,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "5" => Dominion.playerList(0).handList(4).processEffect(4,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "6" => Dominion.playerList(0).handList(5).processEffect(5,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "7" => Dominion.playerList(0).handList(6).processEffect(6,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "8" => Dominion.playerList(0).handList(7).processEffect(7,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "9" => Dominion.playerList(0).handList(8).processEffect(8,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)

       */
    }

  override def printTui(): Unit = ???

  override def handle(): Unit = ???
}
