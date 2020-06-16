package de.htwg.se.Dominion.controller

object GameState extends Enumeration {
  type gameState = Value

  val startScreen, twoPlayers, threePlayers, fourPlayers, playerOneTurn, playerTwoTurn, playerThreeTurn, playerFourTurn, endScreen = Value
}
