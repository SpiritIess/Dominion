package de.htwg.se.Dominion.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HandSpec extends WordSpec with Matchers {
  "A Hand" when { "drawing from a pile of Copper-cards" should {
    val drawPile = List.fill(10)(CardSet.copperCard)
    val startingHand = Hand(drawPile)
    val (card1, newDrawPile1) = PlayerDrawPile(drawPile).drawOne()
    val (card2, newDrawPile2) = PlayerDrawPile(drawPile).drawOne()
    val (card3, newDrawPile3) = PlayerDrawPile(drawPile).drawOne()
    val (card4, newDrawPile4) = PlayerDrawPile(drawPile).drawOne()
    val (card5, newDrawPile5) = PlayerDrawPile(drawPile).drawOne()
    "have a drawPile to draw from" in  {
      drawPile.length should be (10)
    }
    "should reduce the size of drawPile when one card was drawn" in {
      newDrawPile1.size should be (9)
    }
    "have a card" in {
      card1.toString should be ("Copper")
    }
    "have a nice String representation" in {
      startingHand.toString should be ("Copper, Copper, Copper, Copper, Copper")
    }
  }}

}
