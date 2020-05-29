package de.htwg.se.Dominion.model

case class Hand(drawPile: List[Card]) {
  val (handList,newDrawPile) = PlayerDrawPile(drawPile).drawAdditional(5)

  override def toString:String = handList.toString()

  def updateHand(oldHand:List[Card], newDrawPile:List[Card], newCards:List[Card]) : (List[Card], List[Card]) = (oldHand.++:(newCards), newDrawPile)
  /*def countGold:Int = {
    val handCards = List(card1, card2, card3, card4, card5 /*,additionally drawn cards*/)


  }*/
}

