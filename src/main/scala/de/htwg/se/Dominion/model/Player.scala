package de.htwg.se.Dominion.model

case class Player(name: String, mayDraw: Boolean = true, mayBuy: Boolean = true, mayPlayAction : Boolean = true) {
   override def toString:String = name
   //val startingPile = PlayerDrawPile(Pile.startPile).shuffle
   val startingPile = PlayerDrawPile(Pile.testMoatPile).shuffle //for testing purposes
   val (handList,newDrawPile) = PlayerDrawPile(startingPile).drawAdditional(5)
   var hand = handList
   var playerDrawPile =newDrawPile
   //val processedEffectHand = Hand(playerDrawPile).updateHand(handList, processEffect().drawPile, newHandList)
   def updateHand(oldHand:List[Card], newCards:List[Card], newDrawPile:List[Card]) : (List[Card], List[Card]) = (oldHand.++:(newCards), newDrawPile)
}