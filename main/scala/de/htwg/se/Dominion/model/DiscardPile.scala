package de.htwg.se.Dominion.model

case class DiscardPile(pile: List[Card] = List()) extends PlayerPile(pile) {

  def discardCard(card: Card): DiscardPile = DiscardPile(pile ::: List(card))
  def discardCards(cards: List[Card]): DiscardPile = DiscardPile(pile ::: cards)

  def refresh: DiscardPile = DiscardPile()
}