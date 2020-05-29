package de.htwg.se.Dominion.model

case class Board() {

  private val moneyStack = Seq[String] ("Copper: " + Pile.copperPile.length, "Silver: " + Pile.silverPile.length, "Gold: " + Pile.goldPile.length)
  private val winninStack = Seq[String] ("Property: " + Pile.propertyPile.length, "Dukedom: " + Pile.dukedomPile.length, "Province: " + Pile.provincePile.length)
//  override def toString: String = {
//      "_______________ ______________ _________________\n" +
//      "|copper stack | |silver stack| |gold stack     |\n" +
//      "|_____________| |____________| |_______________|\n" +
//      "_______________ ______________ _________________\n" +
//      "|propertyStack| |dukedomStack| |provinceStack  |\n" +
//      "|_____________| |____________| ________________|\n" +
//      "_______________ ______________ _________________\n" +
//      "|moatStack    | |villageStack| |lumberjackStack|\n" +
//      "|_____________| |____________| |_______________|\n" +
//      "_______________ ______________ _________________\n" +
//      "|cellarStack  | |marketStack | |militiaStack   |\n" +
//      "|_____________| |____________| |_______________|\n" +
//      "_______________ ______________ _________________\n" +
//      "|mineStack    | |smithyStack | |conversionStack|\n" +
//      "|_____________| |____________| |_______________|\n" +
//      "_______________ ______________\n" +
//      "|workshopStack| |garbageStack|\n" +
//      "|_____________| |____________|\n\n\n" +
//      "_______________ _______________\n" +
//      "|discard pile | |draw pile    |\n" +
//      "|_____________| |_____________|\n"
//  }
  override def toString: String = {
    moneyStack.mkString("|", "| |", "|\n") + winninStack.mkString("|", "| |", "|\n")
  }


//  println("_______________ _______________ ______________ _______________ ______________\n")
//  println("|handcard 1   | |handcard 2   | |handcard 3  | |handcard 4   | |handcard 5  |\n")
//  println("|effects 1    | |effects 2    | |effects 3   | |effects 4    | |effects 5   |\n")
//  println("|_____________| |_____________| |____________| |_____________| |____________|\n")

}
