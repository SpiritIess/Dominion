package de.htwg.se.Dominion.controller

object TurnState extends Enumeration {
  type turnState = Value

  val actionPhase, buyingPhase, cleanUp = Value
}