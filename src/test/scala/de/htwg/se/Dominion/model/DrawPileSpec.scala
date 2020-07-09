package de.htwg.se.Dominion.model

import de.htwg.se.Dominion.Dominion
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

class DrawPileSpec extends WordSpec with Matchers {

  "A PlayerDrawPile" when {"new" should {
    val player = Dominion.playerList(0)
    val playerDrawPile = DrawPile(Pile.startPile)

    "have a nice string representation" in {
      playerDrawPile.toString should be ("List(Copper, Copper, Copper, Copper, Copper, Copper, Copper, Property, Property, Property)")
    }
    "should not be in the same order after shuffling" in {
      playerDrawPile.shuffle should not be List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
        CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
        CardSet.propertyCard, CardSet.propertyCard)
    }
    "return the first element in playerDrawPile and the new drawPile without the drawn card" in {
      val testList = List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
        CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
        CardSet.propertyCard, CardSet.propertyCard)

      playerDrawPile.drawOne(player)._1 should be(CardSet.copperCard)
      playerDrawPile.drawOne(player)._2 should be(DrawPile(testList))
    }
  }
    "without any cards in it" should {
      val startPile = Pile.startPile
      val (hand, playerPile) = DrawPile(startPile).drawAdditional(10)
      "be empty" in {
        playerPile.isEmpty should be(true)
      }
      "be shuffled and contain all cards from discard pile" in {
        playerPile.refresh(DiscardPile().discardCards(hand)).pile.toSet should be(startPile.toSet)
      }
    }

  }

}