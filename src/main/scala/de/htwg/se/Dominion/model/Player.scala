package de.htwg.se.Dominion.model

case class Player(name: String) {
   override def toString:String = name
   val startingPile = PlayerDrawPile(Pile().startPile).shuffle()
   val startingHand = Hand(startingPile)
   val playerDrawPile = startingHand.newDrawPile5
}