package de.htwg.se.Dominion.model

import de.htwg.se.Dominion.Dominion

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {

  def usesAction: Boolean = cardType == 3

  override def toString: String = name

  def processEffect(position : Int, currentHand: Hand, drawPile: PlayerDrawPile): (Hand, PlayerDrawPile) = {
    if (cardType != 3) {
      println("error: only an action card has an effect when played! Choose an action card!")
      (currentHand, drawPile)
    }
    else {
      name match {
        case "moat" => val (newCards,newDrawPile) = drawPile.drawAdditional(extraDraws)
          (Hand(currentHand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
      }
    }
  }
}