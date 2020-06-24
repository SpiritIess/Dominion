package de.htwg.se.Dominion.model

import org.scalatest.{Matchers, WordSpec}

class HandSpec extends WordSpec with Matchers {
  "A Hand" when {
    val drawPile = DrawPile(Pile.testPile)
    val (handCards, newDrawPile) = drawPile.drawAdditional(5)
    val startingHand = Hand(handCards)
    "drawing from a pile of Copper-cards" should {
      val (card1, newDrawPile1) = newDrawPile.drawOne
      val (card2, newDrawPile2) = newDrawPile1.drawOne
      val (card3, newDrawPile3) = newDrawPile2.drawOne
      val (card4, newDrawPile4) = newDrawPile3.drawOne
      val (card5, newDrawPile5) = newDrawPile4.drawOne
      "contain 5 Cards" in  {
        handCards.length should be (5)
      }
      "should reduce the size of drawPile when one card was drawn" in {
        newDrawPile1.pile.size should be (4)
      }
      "have a copper card" in {
        startingHand.handCards.head should be (CardSet.moatCard)
      }
      "have a nice String representation" in {
        startingHand.toString should be ("Moat, Copper, Property, Moat, Moat")
      }
    }
    "removing a card from it" should {
      val newHand = startingHand.removeCardFromHand(1)
      "only have 4 cards left" in {
        newHand.handCards.size should be(4)
      }
      "return the amount of money in ones hand" in {
        startingHand.countGold() should be (1)
      }
    }}

}