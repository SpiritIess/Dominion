package de.htwg.se.Dominion.model

case class Player(name: String) {
   override def toString:String = name
   var mayPlayAction = 1
   var mayBuy = 1
   val startingPile = PlayerDrawPile(Pile.startPile).shuffle
   //val startingPile: PlayerDrawPile = PlayerDrawPile(Pile.testMoatPile).shuffle //for testing purposes
   val (handList,newDrawPile) = startingPile.drawAdditional(5)
   var hand: Hand = Hand(handList)
   var playerDrawPile: PlayerDrawPile = newDrawPile
   //val processedEffectHand = Hand(playerDrawPile).updateHand(handList, processEffect().drawPile, newHandList)
//   def updateHand(oldHand:List[Card], newCards:List[Card], newDrawPile:List[Card]) : (List[Card], List[Card]) = (oldHand.++:(newCards), newDrawPile)
}