package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.{Board, Player}

import scala.collection.mutable.ListBuffer

case class TuiTwoPlayers(controller: Controller, tui: Tui) extends State{
  var playerArray: Array[String] = Array("")
  override def processInputLine(input: String): Unit = {
    playerArray = input.split(" ")
    //formerly printTui():
    println(playerArray.mkString("\n"))
    val board = Board().toString()
    print(board)
    //formerly handle():
    Dominion.playerList += Player(playerArray(0))
    Dominion.playerList += Player(playerArray(1))
    Dominion.playerList.toList

    controller.gameState = GameState.playerOneTurn
    tui.state = TuiPlayerTurn(controller,tui)
    println(s"${playerArray(0)}, choose an action-card from your hand, " +
      s"or press '0' to skip to the Buying-Phase and confirm your decision by pressing 'Enter'!\n")
  }
}
