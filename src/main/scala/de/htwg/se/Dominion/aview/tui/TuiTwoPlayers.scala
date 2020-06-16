package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.{Board, Player}

import scala.collection.mutable.ListBuffer

case class TuiTwoPlayers(controller: Controller, tui: Tui) extends State{
  var playerArray = Array("")
  override def processInputLine(input: String) = {
    playerArray = input.split(" ")
    printTui()
    handle()
    controller.gameState = GameState.playerOneTurn
    tui.state = TuiPlayerOneTurn(controller,tui)
    println(s"${playerArray(0)}, choose an action-card from your hand!\n")
  }
  def handle(): Unit = {
    Dominion.playerList += Player(playerArray(0))
    Dominion.playerList += Player(playerArray(1))
    Dominion.playerList.toList
  }

  override def printTui(): Unit =  {
    println(playerArray.mkString("\n"))
    val board = Board().toString()
    print(board)
  }
}
