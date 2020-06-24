package de.htwg.se.Dominion.controller

object GameState extends Enumeration {
  type gameState = Value

  val startScreen, setUpPlayers, playerOneTurn, playerTwoTurn, playerThreeTurn, playerFourTurn, endScreen = Value
}