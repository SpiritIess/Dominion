package de.htwg.se.Dominion.model

import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

class CardSpec extends WordSpec with Matchers {

  "A copper card" should {
    val copperCard = Card(1, "Copper", 1, 0, 0, 0, 0, 0)
    "have an ID" in {
      copperCard.cardID should be(1)
    }
    "have a name" in {
      copperCard.name should be("Copper")
    }
    "have a cardType" in {
      copperCard.cardType should be(1)
    }
    "have no cost" in {
      copperCard.cost should be(0)
    }
    "have no extraBuys" in {
      copperCard.extraBuys should be(0)
    }
    "have no extraActions" in {
      copperCard.extraActions should be(0)
    }
    "have no extraGold" in {
      copperCard.extraGold should be(0)
    }
    "have no extraDraws" in {
      copperCard.extraDraws should be(0)
    }
    "have a nice String representation" in {
      copperCard.toString should be("Copper")
    }
    "not use an Action" in {
      copperCard.usesAction should be(false)
    }
  }

  "A market card" should {
    val marketCard: Card = Card(12, "market", 3, 5, 1, 1, 1, 1)
    "have an ID"  in {
      marketCard.cardID should be(12)
    }
    "have a name"  in {
      marketCard.name should be("market")
    }
    "have a cardType"  in {
      marketCard.cardType should be(3)
    }
    "have a cost"  in {
      marketCard.cost should be(5)
    }
    "have extraBuys"  in {
      marketCard.extraBuys should be(1)
    }
    "have extraActions"  in {
      marketCard.extraActions should be(1)
    }
    "have extraGold"  in {
      marketCard.extraGold should be(1)
    }
    "have extraDraws"  in {
      marketCard.extraDraws should be(1)
    }
    "have a nice String representation" in {
      marketCard.toString should be("market")
    }
    "use an Action" in {
      marketCard.usesAction should be(true)
    }
  }

}
