package de.htwg.se.Dominion.model

object Pile { //initializing all piles with their respective cards and their amounts
  val startPile =  List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
    CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
    CardSet.propertyCard, CardSet.propertyCard)

  val testPile = List(CardSet.moatCard, CardSet.copperCard, CardSet.propertyCard, CardSet.moatCard,
    CardSet.moatCard, CardSet.copperCard, CardSet.copperCard, CardSet.moatCard,
    CardSet.moatCard, CardSet.moatCard)

  val piles: Map[Card, Int] = Map((CardSet.copperCard, 60),
    (CardSet.silverCard, 40),
    (CardSet.goldCard, 30),
    (CardSet.propertyCard, 24),
    (CardSet.dukedomCard, 12),
    (CardSet.provinceCard, 12),
    (CardSet.moatCard, 10),
    (CardSet.villageCard, 10),
    (CardSet.lumberjackCard, 10),
    (CardSet.cellarCard, 10),
    (CardSet.marketCard, 10),
    (CardSet.militiaCard, 10),
    (CardSet.mineCard, 10),
    (CardSet.smithyCard, 10),
    (CardSet.conversionCard, 10),
    (CardSet.workshopCard, 10))
}