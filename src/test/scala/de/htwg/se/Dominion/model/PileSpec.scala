package de.htwg.se.Dominion.model

import org.scalatest._

class PileSpec extends WordSpec with Matchers {
  "A Pile" when {"new" should {
    val pile = Pile
    "have a startPile" in {
      pile.startPile should be(List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
        CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
        CardSet.propertyCard, CardSet.propertyCard))
    }
    "have a copperPile with 60 cards in it" in {
      Pile.piles(CardSet.copperCard) should be (60)
    }
    "have a silverPile with 40 cards in it" in {
      Pile.piles(CardSet.silverCard) should be (40)
    }

    "have a goldPile with 30 cards in it" in {
      Pile.piles(CardSet.goldCard) should be (30)
    }

    "have a propertyPile with 24 cards in it" in {
      Pile.piles(CardSet.propertyCard) should be (24)
    }

    "have a dukedomPile with 12 cards in it" in {
      Pile.piles(CardSet.dukedomCard) should be (12)
    }

    "have a provincePile with 12 in it" in {
      Pile.piles(CardSet.provinceCard) should be (12)
    }

    "have a moatPile with 10 cards in it" in {
      Pile.piles(CardSet.moatCard) should be (10)
    }

    "have a villagePile with 10 cards in it" in {
      Pile.piles(CardSet.villageCard) should be (10)
    }

    "have a lumberjackPile with 10 cards in it" in {
      Pile.piles(CardSet.lumberjackCard) should be (10)
    }

    "have a cellarPile with 10 cards in it" in {
      Pile.piles(CardSet.cellarCard) should be (10)
    }

    "have a marketPile with 10 cards in it" in {
      Pile.piles(CardSet.marketCard) should be (10)
    }

    "have a militiaPile with 10 cards in it" in {
      Pile.piles(CardSet.militiaCard) should be (10)
    }

    "have a minePile with 10 cards in it" in {
      Pile.piles(CardSet.mineCard) should be (10)
    }

    "have a smithyPile with 10 cards in it" in {
      Pile.piles(CardSet.smithyCard) should be (10)
    }

    "have a conversionPile with 10 cards in it" in {
      Pile.piles(CardSet.conversionCard) should be (10)
    }

    "have a workshopPile with 10 cards in it" in {
      Pile.piles(CardSet.workshopCard) should be (10)
    }
  }}
}