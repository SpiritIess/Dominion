package de.htwg.se.Dominion.model

case class Hand(handCards: List[Card]) {
  var handValue = 0

//  val (handList,newDrawPile) = PlayerDrawPile(drawPile).drawAdditional(5)

//  override def toString:String = handList.toString()

  override def toString:String = handCards.mkString(", ")

//  def updateHand(oldHand:List[Card], newCards:List[Card], newDrawPile:List[Card]) : (List[Card], List[Card]) = (oldHand.++:(newCards), newDrawPile)

  def removeCardFromHand(position : Int) : Hand = {
    val tempLeft = handCards.dropRight(position + 1)
    val tempRight = handCards.drop(handCards.length-position)
    Hand(tempLeft ::: tempRight)
  }
}

