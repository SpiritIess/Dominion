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
        case "Moat" => {
          val (newCards, newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards ::: newCards), newDrawPile)
        }
        case "Village" => {
          val (newCards, newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards ::: newCards), newDrawPile)
        }
        case "Lumberjack" => {
          player.mayBuy += extraBuys
          player.handValue += extraGold
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards), player.playerDrawPile)
        }
        case "Moneylender" => {
          for (i <- 0 until player.hand.handCards.length - 1) {
            if (player.hand.handCards(i) == CardSet.copperCard) {
              player.playerDiscardPile.discardCard(this)
              player.handValue += extraGold
              (Hand(player.hand.removeCardFromHand(position).handCards), player.playerDrawPile)
            }
          }
          println("No copper cards in hand, can't process effect!")
          player.playerDiscardPile.discardCard(this)
          (player.hand, player.playerDrawPile)
        }
        case "Adventurer" => {
          var (tempCard,tempDrawPile) = player.playerDrawPile.drawOne
          //var tempDrawPile = newDrawPile
          var tempCardsList = List(tempCard)
          var tempMoneyCardsList = List(tempCard)
          var moneyCardCounter = 0
          if(tempCard.cardType == 1) {
            moneyCardCounter += 1
            tempMoneyCardsList = List(tempCard)
          }
          //vars vlt in schleife definieren
          do {
            (tempCard, tempDrawPile) = tempDrawPile.drawOne;
            if(tempCard.cardType == 1) {
              moneyCardCounter += 1
              tempMoneyCardsList = tempMoneyCardsList ::: List(tempCard)
            } else {
              tempCardsList = tempCardsList ::: List(tempCard)
            }
          } while (moneyCardCounter < 2)
          player.playerDiscardPile.discardCards(tempCardsList ::: List(this))
          (Hand(player.hand.removeCardFromHand(position).handCards ::: tempMoneyCardsList),tempDrawPile)
        }
        case "Laboratory" => {
          val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.mayPlayAction += extraActions
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        }
        case "Funfair" => {
          player.handValue += extraGold
          player.mayBuy += extraBuys
          player.mayPlayAction += extraActions
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards), player.playerDrawPile)
        }
        case "Smithy" => {
          val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        }
        case "Market" => {
          val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.mayPlayAction += extraActions
          player.mayBuy += extraBuys
          player.playerDiscardPile.discardCard(this)
          (Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        }
      }
    }
  }
}