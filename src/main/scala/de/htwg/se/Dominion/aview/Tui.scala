package de.htwg.se.Dominion.aview

import de.htwg.se.Dominion.model.{Board, Card, Hand, Pile, Player, PlayerDrawPile}

class Tui {

  def processInputLine(input: String, player:Player) : Unit = {
    println("please press the number of the card you want to play from your hand, or press \"b\" to skip the Action-phase and start the buying-phase:\n")
    input match {
      case "1" => //play card1
      case "2" => //play card2
      case "3" => //play card3
      case "4" => //play card4
      case "5" => //play card5
      /*
      case "b" => {
        val currentGold = player.currentHand.countGold
        println(s"you have $currentGold Gold, which card do you want to buy?")
      }
      */
    }
  }

}
