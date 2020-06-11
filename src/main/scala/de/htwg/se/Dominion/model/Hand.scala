package de.htwg.se.Dominion.model

case class Hand(drawPile: List[Card]) {

  val (handList,newDrawPile) = PlayerDrawPile(drawPile).drawAdditional(5)

  override def toString:String = handList.toString()

  def updateHand(oldHand:List[Card], newCards:List[Card], newDrawPile:List[Card]) : (List[Card], List[Card]) = (oldHand.++:(newCards), newDrawPile)
}

