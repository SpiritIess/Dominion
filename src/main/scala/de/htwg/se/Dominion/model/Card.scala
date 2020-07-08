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
      if(player.playerDrawPile.isEmpty) {
        player.playerDrawPile = player.playerDrawPile.refresh(player.playerDiscardPile)
      }
      name match {
        case "Moat" => {
          val (newCards, newDrawPile) = player.playerDrawPile.ensureDrawCapacity(extraDraws,player.playerDiscardPile)
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards ::: newCards), newDrawPile)
        }
        case "Village" => {
          val (newCard, newDrawPile) = player.playerDrawPile.ensureDrawCapacity(extraDraws,player.playerDiscardPile)
          player.mayPlayAction += extraActions
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards ::: newCard), newDrawPile)
        }
        case "Lumberjack" => {
          player.mayBuy += extraBuys
          player.handValue += extraGold
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards), player.playerDrawPile)
          //(Hand(player.hand.removeCardFromHand(position).handCards), player.playerDrawPile)
        }
        case "Moneylender" => {
          for (i <- 0 until player.hand.handCards.length - 1) {
            if (player.hand.handCards(i) == CardSet.copperCard) {
              player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
              player.handValue += extraGold
              var newHand = player.hand.removeCardFromHand(position)
              newHand = newHand.removeCardFromHand(i)
              return (Hand(newHand.handCards), player.playerDrawPile)
              //(Hand(player.hand.removeCardFromHand(position).handCards), player.playerDrawPile)
            }
          }
          println("No copper cards in hand, can't process effect!")
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          (player.hand, player.playerDrawPile)
        }
        case "Adventurer" => {
          var temp = player.playerDrawPile.drawOne
          var tempCardsList = List(CardSet.gardenCard)
          tempCardsList = tempCardsList.drop(1)
          var tempMoneyCardsList = List(temp._1)
          var moneyCardCounter = 0
          if(temp._1.cardType == 1) {
            moneyCardCounter += 1
            tempMoneyCardsList = List(temp._1)
          }
          do {
            temp = temp._2.drawOne
            if(temp._1.cardType == 1) {
              moneyCardCounter += 1
              tempMoneyCardsList = tempMoneyCardsList ::: List(temp._1)
            } else {
              tempCardsList = tempCardsList ::: List(temp._1)
            }
          } while (moneyCardCounter < 2)
          player.playerDiscardPile = player.playerDiscardPile.discardCards(tempCardsList ::: List(this))
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards ::: tempMoneyCardsList),temp._2)
        }
        case "Laboratory" => {
          val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.mayPlayAction += extraActions
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards ::: newCards), newDrawPile)
          //(Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        }
        case "Funfair" => {
          player.handValue += extraGold
          player.mayBuy += extraBuys
          player.mayPlayAction += extraActions
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards), player.playerDrawPile)
          //(Hand(player.hand.removeCardFromHand(position).handCards), player.playerDrawPile)
        }
        case "Smithy" => {
          val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards ::: newCards), newDrawPile)
          //(Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        }
        case "Market" => {
          val (newCards,newDrawPile) = player.playerDrawPile.drawAdditional(extraDraws)
          player.handValue += extraGold
          player.mayPlayAction += extraActions
          player.mayBuy += extraBuys
          player.playerDiscardPile = player.playerDiscardPile.discardCard(this)
          val newHand = player.hand.removeCardFromHand(position)
          (Hand(newHand.handCards ::: newCards), newDrawPile)
          //(Hand(player.hand.removeCardFromHand(position).handCards:::newCards), newDrawPile)
        }
      }
    }
  }
}