package de.htwg.se.Dominion.model

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {

  def usesAction: Boolean = cardType == 3

  override def toString: String = name

  def processEffect(position : Int, player: Player): (Hand, DrawPile) = {
    if (cardType != 3) {
      println("error: only an action card has an effect when played! Choose an action card!")
      (player.hand, player.playerDrawPile)
    }
    else {
      player.mayPlayAction -= 1
      name match {
        case "Moat" => val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          //currentHand.mayPlayAction += CardSet.moatCard.extraActions
          (Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        case "Village" => val (newCards, newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          //currentHand.mayPlayAction += CardSet.villageCard.extraActions
          (Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        case "Lumberjack" => player.mayBuy += extraBuys
          player.handValue += 2
          (Hand(player.hand.removeCardFromHand(position).handCards),player.playerDrawPile)
        /*case "Cellar" => currentHand.mayPlayAction += extraActions - 1
          println("Please type in all the positions of the cards you want to remove from your hand:\n")
          */
      }
    }
  }
}