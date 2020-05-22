package de.htwg.se.Dominion.model

//cardtype: 1-> money, 2-> points, 3-> action
case class Card(cardID: Int, name: String, cardType: Int, cost:Int,
                extraBuys: Int, extraActions: Int, extraGold: Int,
                extraDraws: Int) {
  def usesAction: Boolean = cardType == 3

  override def toString:String = name

  //  case class Effect(cardID: Int) {
  //    val effect: Unit = cardID match {
  //      case 8 => //Burggraben
  //      case 11 => //Keller
  //      case 13 => //Miliz
  //      case 14 => //Mine
  //      case 16 => //Umbau
  //      case 17 => //Werkstatt
  //    }
  //  }
}