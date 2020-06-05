package de.htwg.se.Dominion.model

object GameState extends Enumeration {
  type gameState = Value
  val init, initPlayerCount, initPlayers, play = Value
}
