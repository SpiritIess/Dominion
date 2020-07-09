package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card

abstract class PlayerPile(pile : List[Card]) {

  override def toString: String = pile.toString()
  def isEmpty: Boolean = pile.isEmpty
}


