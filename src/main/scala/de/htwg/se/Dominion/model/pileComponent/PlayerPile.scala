package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card

abstract class PlayerPile(pile : List[Card]) extends PileInterface {

  override def toString: String = pile.toString()
  override def isEmpty: Boolean = pile.isEmpty
  override def nonEmpty: Boolean = pile.nonEmpty

}


