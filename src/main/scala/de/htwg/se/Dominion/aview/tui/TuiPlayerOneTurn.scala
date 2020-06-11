package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.aview.tui.TuiTwoPlayers

case class TuiPlayerOneTurn(controller : Controller, tui:Tui) extends State {
  override def processInputLine(input: String): Unit =
    input match {
      case "1" => {
        val (handList,playerDrawPile) = (
          Dominion.playerList(0).updateHand(
            Dominion.playerList(0).handList,
            Dominion.playerList(0).handList.head.processEffect(0, Dominion.playerList(0).handList,
                                                                Dominion.playerList(0).playerDrawPile)._1,
            Dominion.playerList(0).handList.head.processEffect(0, Dominion.playerList(0).handList,
            Dominion.playerList(0).playerDrawPile)._2))
        Dominion.playerList(0).hand= handList
        Dominion.playerList(0).playerDrawPile = playerDrawPile
        println(handList.toString())
        println(playerDrawPile.toString())
      }
      case "2" => Dominion.playerList(0).handList(1).processEffect(1,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "3" => Dominion.playerList(0).handList(2).processEffect(2,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "4" => Dominion.playerList(0).handList(3).processEffect(3,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "5" => Dominion.playerList(0).handList(4).processEffect(4,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "6" => Dominion.playerList(0).handList(5).processEffect(5,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "7" => Dominion.playerList(0).handList(6).processEffect(6,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "8" => Dominion.playerList(0).handList(7).processEffect(7,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
      case "9" => Dominion.playerList(0).handList(8).processEffect(8,Dominion.playerList(0).handList,Dominion.playerList(0).playerDrawPile)
    }

  override def printTui(): Unit = ???

  override def handle(): Unit = ???
}
