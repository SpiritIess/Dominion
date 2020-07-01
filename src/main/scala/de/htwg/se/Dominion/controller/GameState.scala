package de.htwg.se.Dominion.controller

object GameState extends Enumeration {
  type gameState = Value

  val startScreen, setUpPlayers, playerTurn, endScreen = Value
}