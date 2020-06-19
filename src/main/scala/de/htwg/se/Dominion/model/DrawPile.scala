package de.htwg.se.Dominion.model

import scala.util.Random

case class DrawPile(pile : List[Card]) extends PlayerPile(pile) {

  def drawOne : (Card, DrawPile) = (pile.head, DrawPile(pile.tail))

  def drawAdditional(amount: Int) : (List[Card], DrawPile) = (pile.take(amount), DrawPile(pile.takeRight(pile.length-amount)))

  def refresh(discardPile: DiscardPile): DrawPile = DrawPile(discardPile.pile).shuffle

  def shuffle : DrawPile = DrawPile(Random.shuffle(pile))

}


