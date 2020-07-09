package de.htwg.se.Dominion.model.pileComponent

import de.htwg.se.Dominion.model.cardComponent.CardSet
import org.scalatest.{Matchers, WordSpec}

class HandSpec extends WordSpec with Matchers {
  "A Hand" when {
    val drawPile = DrawPile(Pile.testPile)
    val (handCards, newDrawPile) = drawPile.drawAdditional(5)
    val startingHand = Hand(handCards)
    "drawing from a pile of Copper-cards" should {

      "make the hand contain 5 Cards" in  {
        handCards.length should be (5)
      }
      "should reduce the size of drawPile when one or more cards were drawn" in {
        newDrawPile.pile.size should be (14)
      }
      "have a copper card" in {
        startingHand.handCards.head should be (CardSet.copperCard)
      }
      "have a nice String representation" in {
        startingHand.toString should be ("Copper, Dukedom, Garden, Village, Smithy")
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