package de.htwg.se.Dominion.model

object Pile { //initializing all piles with their respective cards and their amounts
  val startPile =  List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
    CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
    CardSet.propertyCard, CardSet.propertyCard)

  val testPile = List(CardSet.copperCard, CardSet.dukedomCard, CardSet.gardenCard,
    CardSet.villageCard, CardSet.smithyCard, CardSet.marketCard, CardSet.labCard,
    CardSet.moatCard, CardSet.funFairCard, CardSet.lumberjackCard, CardSet.moneyLenderCard,
    CardSet.villageCard, CardSet.adventurerCard, CardSet.silverCard, CardSet.villageCard,
    CardSet.goldCard, CardSet.copperCard, CardSet.provinceCard, CardSet.copperCard)

  var piles: Map[Card, Int] = Map((CardSet.copperCard, 60),
    (CardSet.silverCard, 40),
    (CardSet.goldCard, 30),
    (CardSet.propertyCard, 24),
    (CardSet.dukedomCard, 12),
    (CardSet.provinceCard, 12),
    (CardSet.gardenCard, 12),
    (CardSet.moatCard, 10),
    (CardSet.villageCard, 10),
    (CardSet.lumberjackCard, 10),
    (CardSet.smithyCard, 10),
    (CardSet.moneyLenderCard, 10),
    (CardSet.marketCard, 10),
    (CardSet.adventurerCard, 10),
    (CardSet.labCard, 10),
    (CardSet.funFairCard, 10))
}