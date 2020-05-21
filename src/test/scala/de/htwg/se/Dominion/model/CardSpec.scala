package de.htwg.se.Dominion.model

import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardSpec extends WordSpec with Matchers {

  "A Card" when { "new" should {
    val card = Card(1, "Copper", 1, 0, 0, 0, 0, 0)
    "have an ID"  in {
      card.cardID should be(1)
    }
    "have a name"  in {
      card.name should be("Copper")
    }
    "have a cardType"  in {
      card.cardType should be(1)
    }
    "have a cost"  in {
      card.cost should be(0)
    }
    "have extraBuys"  in {
      card.extraBuys should be(0)
    }
    "have extraActions"  in {
      card.extraActions should be(0)
    }
    "have a extraGold"  in {
      card.extraGold should be(0)
    }
    "have a extraDraws"  in {
      card.extraDraws should be(0)
    }
    "have a nice String representation" in {
      card.toString should be("Copper")
    }
    "use an Action" in {
      card.usesAction should be(true)
    }
  }}

}
