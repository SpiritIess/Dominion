package de.htwg.se.Dominion.model

import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Jakob Strakhof")
    "have a name"  in {
      player.name should be("Jakob Strakhof")
    }
    "have a nice String representation" in {
      player.toString should be("Jakob Strakhof")
    }
    "have copper cards in the startingPile" in {
      player.startingPile.pile.contains(CardSet.copperCard) should be (false) //true, wenn der startingPile nicht mehr aus moat-cards besteht
      player.startingPile.pile.contains(CardSet.moatCard) should be (true)
    }
    "have province cards in the startingPile" in {
      player.startingPile.pile.contains(CardSet.propertyCard) should be (false) //genau, wie bei contains(CardSet.copperCard)
    }
    "after drawing 5 cards the size of playerDrawPile should be reduced by 5" in {
      player.playerDrawPile.pile.size should be(5)
    }
  }}


}
