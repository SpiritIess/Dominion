package de.htwg.se.Dominion.model

case class Player(name: String) {
   override def toString:String = name
   //val startingPile = PlayerDrawPile(Pile.startPile).shuffle
   val startingPile: DrawPile = DrawPile(Pile.testPile)
   val (handList,newDrawPile) = startingPile.drawAdditional(5)
   var hand: Hand = Hand(handList)
   var playerDrawPile: DrawPile = newDrawPile
   var playerDiscardPile: DiscardPile = DiscardPile()
   //val processedEffectHand = Hand(playerDrawPile).updateHand(handList, processEffect().drawPile, newHandList)
   //   def updateHand(oldHand:List[Card], newCards:List[Card], newDrawPile:List[Card]) : (List[Card], List[Card]) = (oldHand.++:(newCards), newDrawPile)
}