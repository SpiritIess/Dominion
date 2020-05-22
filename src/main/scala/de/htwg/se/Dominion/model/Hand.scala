package de.htwg.se.Dominion.model

case class Hand(drawPile: List[Card]) {
  val (card1, newDrawPile1) = PlayerDrawPile(drawPile).drawOne()
  //println(card1, newDrawPile1)
  val (card2, newDrawPile2) = PlayerDrawPile(newDrawPile1).drawOne()
  //println(card2, newDrawPile2)
  val (card3, newDrawPile3) = PlayerDrawPile(newDrawPile2).drawOne()
  //println(card3, newDrawPile3)
  val (card4, newDrawPile4) = PlayerDrawPile(newDrawPile3).drawOne()
  //println(card4, newDrawPile4)
  val (card5, newDrawPile5) = PlayerDrawPile(newDrawPile4).drawOne()
  //println(card5, newDrawPile5)

  override def toString:String = {
    s"""${card1.toString}, ${card2.toString}, ${card3.toString}, ${card4.toString}, ${card5.toString}"""
  }
}
