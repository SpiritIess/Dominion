package de.htwg.se.Dominion.model

case class Pile() { //initializing all piles with their respective cards and their amounts
  val startPile =  List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
                        CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
                        CardSet.propertyCard, CardSet.propertyCard)

  val copperPile = List.fill(60)(CardSet.copperCard)

  val silverPile = List.fill(40)(CardSet.silverCard)

  val goldPile =   List.fill(30)(CardSet.goldCard)

  val propertyPile = List.fill(24)(CardSet.propertyCard)

  val dukedomPile = List.fill(12)(CardSet.dukedomCard)

  val provincePile = List.fill(12)(CardSet.provinceCard)

  val moatPile = List.fill(10)(CardSet.moatCard)

  val villagePile = List.fill(10)(CardSet.villageCard)

  val lumberjackPile = List.fill(10)(CardSet.lumberjackCard)

  val cellarPile = List.fill(10)(CardSet.cellarCard)

  val marketPile = List.fill(10)(CardSet.marketCard)

  val militiaPile = List.fill(10)(CardSet.militiaCard)

  val minePile = List.fill(10)(CardSet.mineCard)

  val smithyPile = List.fill(10)(CardSet.smithyCard)

  val conversionPile = List.fill(10)(CardSet.conversionCard)

  val workshopPile = List.fill(10)(CardSet.workshopCard)
}