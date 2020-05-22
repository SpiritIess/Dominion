package de.htwg.se.Dominion.model

import scala.util.Random

case class PlayerDrawPile(pile : List[Card]) {
  override def toString:String = pile.toString()

  //function used to draw a Card from pile, returns drawn Card and freshly created pile without drawn card
  def drawOne(/*amount: Int = 1*/) : (Card, List[Card]) = {
    val drawnCard = pile.head
    val newPile = pile.drop(1)
    return (drawnCard, newPile)

  }

  //function used to shuffle pile, returns new, shuffled pile
  def shuffle(): List[Card] = {
    val x = Random.shuffle(pile)
    return x
  }
}


