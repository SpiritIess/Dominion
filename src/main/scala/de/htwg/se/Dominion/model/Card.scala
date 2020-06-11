package de.htwg.se.Dominion.model

import de.htwg.se.Dominion.Dominion

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {

  def usesAction: Boolean = cardType == 3

  override def toString: String = name

  def removeCardFromHand(position : Int, currentHand: List[Card]) : List[Card] = {
    val tempLeft = currentHand.dropRight(position+1)
    val tempRight = currentHand.drop(currentHand.length-position)
    println(tempRight)
    tempLeft ::: tempRight
  }

    def processEffect(position : Int, currentHand: List[Card], drawPile: List[Card]): (List[Card], List[Card]) = {
    if (cardType != 3) {
      println("error: only an action card has an effect when played!")
      (currentHand, drawPile)
    }
    else {
      name match {
        case "moat" => {
          val (newCards,newDrawPile) = PlayerDrawPile(drawPile).drawAdditional(extraDraws)
          //println(currentHand.toString()) klappt
          val temp = removeCardFromHand(position,currentHand)
          println(temp.toString())
          println((temp:::newCards).toString())
          println(newDrawPile.toString())
          (removeCardFromHand(position,currentHand):::newCards, newDrawPile)
        }
      }
    }
  }
}