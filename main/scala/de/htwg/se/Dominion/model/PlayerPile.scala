package de.htwg.se.Dominion.model

abstract class PlayerPile(pile : List[Card]) {

  override def toString: String = pile.toString()
  def isEmpty: Boolean = pile.isEmpty
}


