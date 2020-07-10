package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card
import de.htwg.se.Dominion.model.playerComponent.Player

trait PileInterface {

  val pile : List[Card]
  def toString: String
  def isEmpty: Boolean
  def nonEmpty: Boolean

}

trait DrawPileInterface {

  def drawOne(player: Player) : (Card, DrawPile)
  def drawAdditional(amount: Int) : (List[Card], DrawPile)
  def ensureDrawCapacity(amount: Int, player: Player) : (List[Card], DrawPile)
  def refresh(discardPile: DiscardPile): DrawPile
  def shuffle : DrawPile

}

trait DiscardPileInterface {

  def discardCard(card: Card): DiscardPile
  def discardCards(cards: List[Card]): DiscardPile
  def refresh: DiscardPile

}

