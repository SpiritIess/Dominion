package de.htwg.se.Dominion.model

case class Player(name: String, mayDraw: Boolean = true, mayBuy: Boolean = true, mayPlayAction : Boolean = true) {
   override def toString:String = name
   val startingPile = PlayerDrawPile(Pile().startPile).shuffle
   val startingHand = Hand(startingPile)
   val handList = startingHand.handList
   println(handList)
   val playerDrawPile = startingHand.newDrawPile
   //val processedEffectHand = Hand(playerDrawPile).updateHand(handList, processEffect().drawPile, newHandList)
}