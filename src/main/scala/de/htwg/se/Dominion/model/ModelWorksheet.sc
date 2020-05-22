import scala.util.Random

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int){
  def usesAction: Boolean = cardType == 3
}

case class Effect(cardID: Int) {
  val effect: Unit = cardID match {
    case 8 => //Burggraben
    case 11 => //Keller
    case 13 => //Miliz
    case 14 => //Mine
    case 16 => //Umbau
    case 17 => //Werkstatt
  }
}

val copperCard = Card(1,1,0,0,0,0,0)
val silverCard = Card(2,1,0,0,0,0,0)
val goldCard   = Card(3,1,0,0,0,0,0)

val propertyCard = Card(4,2,0,0,0,0,0)
val dukedomCard  = Card(5,2,0,0,0,0,0)
val provinceCard = Card(6,2,0,0,0,0,0)

val moatCard = Card(8,3,2,0,0,0,2)
val villageCard = Card(9,3,3,0,2,0,1)
val lumberjackCard = Card(10,3,3,1,0,2,0)
val cellarCard = Card(11,3,2,0,1,0,0)
val marketCard = Card(12,3,5,1,1,1,1)
val militiaCard = Card(13,3,4,0,0,2,0)
val mineCard = Card(14,3,5,0,0,0,0)
val smithyCard = Card(15,3,4,0,0,0,3)
val conversionCard = Card(16,3,4,0,0,0,0)
val workshopCard = Card(17,3,3,0,0,0,0)


case class cardStack(cardID: Int, amountLeft: Int) {
  def isEmpty: Boolean = amountLeft < 1
}

val copperStack = cardStack(1,60)
val silverStack = cardStack(2,40)
val goldStack = cardStack(3,30)
val propertyStack = cardStack(4,24)
val dukedomStack = cardStack(5,12)
val provinceStack = cardStack(6,12)
val moatStack = cardStack(8,10)
val villageStack = cardStack(9,10)
val lumberjackStack = cardStack(10,10)
val cellarStack = cardStack(11,10)
val marketStack = cardStack(12,10)
val militiaStack = cardStack(13,10)
val mineStack = cardStack(14,10)
val smithyStack = cardStack(15,10)
val conversionStack = cardStack(16,10)
val workshopStack = cardStack(17,10)

case class playerDrawPile(cardAmount: Int, card: Card) {
  def isEmpty: Boolean = cardAmount == 0
  val cardPile = Array(copperCard, copperCard, copperCard, copperCard, copperCard, cellarCard, copperCard,
    propertyCard, propertyCard, propertyCard)
  def shuffle(): Unit = {
    val r = scala.util.Random
    for (i <- 0 until (cardPile.length - 1)
         if i > 0) {
      val index = r.nextInt(i + 1)
      //simple swap
      val temp = cardPile(index)
      cardPile(index) = cardPile(i)
      cardPile(i) = temp
    }
  }
}

//playerDrawPile.shuffle()

case class playerDiscardPile(card: Card, amount: Int){

}

case class playerHand(card: Card, startAmount: Int)

case class playerTurn(nrActions: Int, nrBuys: Int, draw: Int)

for (i <- 1 to 3) println(i)