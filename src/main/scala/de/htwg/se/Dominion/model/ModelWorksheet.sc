import de.htwg.se.Dominion.model.Card

case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {}
val copperCard: Card = Card(1, "Copper", 1, 0, 0, 0, 0, 0)
val donuts: List[Card] = List(copperCard, copperCard, copperCard, copperCard, copperCard, copperCard)
donuts.dropWhile(_.indexOf("Plain Donut 3") == 4)