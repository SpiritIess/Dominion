package de.htwg.se.Dominion.model

import scala.util.Random

case class PlayerDrawPile(pile : List[Card]) {
  override def toString:String = pile.toString()

  def drawOne : (Card, List[Card]) = (pile.head, pile.tail)

  def drawAdditional(amount: Int) : (List[Card], List[Card]) = (pile.take(amount), pile.takeRight(pile.length-amount))

  def shuffle : List[Card] = Random.shuffle(pile)
}


