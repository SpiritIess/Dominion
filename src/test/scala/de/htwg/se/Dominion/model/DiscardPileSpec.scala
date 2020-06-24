package de.htwg.se.Dominion.model

import org.scalatest.{Matchers, WordSpec}

class DiscardPileSpec extends WordSpec with Matchers {

  "A DiscardPile" when {
    "new" should {
      val discardPile = DiscardPile()

      "be empty" in {
        discardPile.isEmpty should be(true)
      }
      "with one discarded card added have size 1" in {
        discardPile.discardCard(CardSet.copperCard).pile.size should be(1)
      }
    }
    "after initial turn with starting hand already discarded" should {
      val handCards = DrawPile(Pile.testPile).drawAdditional(5)_1
      val discardPile = DiscardPile().discardCards(handCards)
      "have 5 cards in it" in {
        discardPile.pile.size should be(5)
      }
      "the discarded cards should equal the discarded hand" in {
        discardPile.pile should be (handCards)
      }
      "when refreshed should return an empty DiscardPile" in {
        discardPile.refresh.pile.size should be(0)
      }
    }


  }
}