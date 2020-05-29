package de.htwg.se.Dominion.model

case class Player(name: String, startingPile: List[Card] = PlayerDrawPile(Pile.startPile).shuffle()) {
   override def toString:String = name
//   val startingPile = PlayerDrawPile(Pile().startPile).shuffle()
   val startingHand: Hand = Hand(startingPile)
   val playerDrawPile: List[Card] = startingHand.newDrawPile5
}