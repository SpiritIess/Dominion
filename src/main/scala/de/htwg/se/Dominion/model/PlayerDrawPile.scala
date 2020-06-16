package de.htwg.se.Dominion.model

import scala.util.Random

case class PlayerDrawPile(pile : List[Card]) {
  override def toString:String = pile.toString()

  def drawOne : (Card, PlayerDrawPile) = (pile.head, PlayerDrawPile(pile.tail))

  def drawAdditional(amount: Int) : (List[Card], PlayerDrawPile) = (pile.take(amount), PlayerDrawPile(pile.takeRight(pile.length-amount)))

  def shuffle : PlayerDrawPile = PlayerDrawPile(Random.shuffle(pile))
}


