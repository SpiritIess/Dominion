package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.{Controller, GameState}
import de.htwg.se.Dominion.model.Player

import scala.collection.mutable.ListBuffer

case class TuiTwoPlayers(controller: Controller, tui: Tui) extends State{
  var playerArray = Array("")
  override def processInputLine(input: String) = {
    playerArray = input.split(" ")
    printTui()
    controller.gameState = GameState.playerOneTurn
    println(s"${playerArray(0)}, choose an action-card from your hand!\n")
  }
  override def handle(): Unit = {
    val player1 = Player(playerArray(0))
    val player2 = Player(playerArray(1))
  }
  override def printTui(): Unit = println(playerArray.mkString("\n"))
}
