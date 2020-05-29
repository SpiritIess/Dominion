package de.htwg.se.Dominion.model

object Pile { //initializing all piles with their respective cards and their amounts
  val startPile =  List(CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.copperCard,
                        CardSet.copperCard, CardSet.copperCard, CardSet.copperCard, CardSet.propertyCard,
                        CardSet.propertyCard, CardSet.propertyCard)

  val copperPile: List[Card] = List.fill(60)(CardSet.copperCard)

  val silverPile: List[Card] = List.fill(40)(CardSet.silverCard)

  val goldPile: List[Card] =   List.fill(30)(CardSet.goldCard)

  val propertyPile: List[Card] = List.fill(24)(CardSet.propertyCard)

  val dukedomPile: List[Card] = List.fill(12)(CardSet.dukedomCard)

  val provincePile: List[Card] = List.fill(12)(CardSet.provinceCard)

  val moatPile: List[Card] = List.fill(10)(CardSet.moatCard)

  val villagePile: List[Card] = List.fill(10)(CardSet.villageCard)

  val lumberjackPile: List[Card] = List.fill(10)(CardSet.lumberjackCard)

  val cellarPile: List[Card] = List.fill(10)(CardSet.cellarCard)

  val marketPile: List[Card] = List.fill(10)(CardSet.marketCard)

  val militiaPile: List[Card] = List.fill(10)(CardSet.militiaCard)

  val minePile: List[Card] = List.fill(10)(CardSet.mineCard)

  val smithyPile: List[Card] = List.fill(10)(CardSet.smithyCard)

  val conversionPile: List[Card] = List.fill(10)(CardSet.conversionCard)

  val workshopPile: List[Card] = List.fill(10)(CardSet.workshopCard)
}
