package de.htwg.se.Dominion.model

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {

  def usesAction: Boolean = cardType == 3

  override def toString: String = name

  def processEffect(position : Int, currentHand: Hand, drawPile: DrawPile): (Hand, DrawPile) = {
    if (cardType != 3) {
      println("error: only an action card has an effect when played! Choose an action card!")
      (currentHand, drawPile)
    }
    else {
      name match {
        case "Moat" => val (newCards,newDrawPile) = drawPile.drawAdditional(extraDraws)
          currentHand.mayPlayAction += extraActions - 1
          (Hand(currentHand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        case "Village" => val (newCards, newDrawPile) = drawPile.drawAdditional(extraDraws)
          currentHand.mayPlayAction += extraActions - 1
          (Hand(currentHand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        case "Lumberjack" => currentHand.mayBuy += extraBuys
          currentHand.value += 2
          (Hand(currentHand.removeCardFromHand(position).handCards),drawPile)
        /*case "Cellar" => currentHand.mayPlayAction += extraActions - 1
          println("Please type in all the positions of the cards you want to remove from your hand:\n")
          */
      }
    }
  }
}