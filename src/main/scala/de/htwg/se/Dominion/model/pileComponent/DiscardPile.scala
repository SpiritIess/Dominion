package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card

case class DiscardPile(pile: List[Card] = List()) extends PlayerPile(pile) with DiscardPileInterface{

  def discardCard(card: Card): DiscardPile = DiscardPile(pile ::: List(card))
  def discardCards(cards: List[Card]): DiscardPile = DiscardPile(pile ::: cards)

  def refresh: DiscardPile = DiscardPile()
}
