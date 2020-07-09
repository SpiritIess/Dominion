package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.Card

case class Hand(handCards: List[Card]) {

//  val (handList,newDrawPile) = PlayerDrawPile(drawPile).drawAdditional(5)

//  override def toString:String = handList.toString()

  override def toString:String = handCards.mkString(", ")

  def emtpyHand: Hand = {
    val newHand = handCards.drop(handCards.size)
    Hand(newHand)
  }
  def removeCardFromHand(position : Int) : Hand = {
    val tempLeft = handCards.take(position)
    val tempRight = handCards.takeRight(handCards.size - position - 1)
    Hand(tempLeft ::: tempRight)
  }
  def countGold() : Int = {
    var handVal = 0
    for(i<-0 until this.handCards.length - 1) {
      if(this.handCards(i).cardType == 1) {
        handVal += this.handCards(i).extraGold
      }
    }
//    println(handVal)
    handVal
  }
}
