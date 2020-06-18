package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.controller.{Controller, GameState, TurnState}
import de.htwg.se.Dominion.model.{CardSet, Hand, Player}

case class TuiPlayerTurn(controller : Controller, tui:Tui) extends State {
  var player = Dominion.playerList.head
  controller.setTurnState(TurnState.actionPhase)
  controller.gameState match {
    case GameState.playerOneTurn => player = Dominion.playerList.head
    case GameState.playerTwoTurn => player = Dominion.playerList(1)
    case GameState.playerThreeTurn => player = Dominion.playerList(2)
    case GameState.playerFourTurn => player = Dominion.playerList(3)
    case _ => println("something went wrong setting the controller.gameState - variable")
  }

  override def processInputLine(input: String): Unit = {
    if (controller.turnState == TurnState.actionPhase) {
      val inputNumber = input.toInt
      if (inputNumber > 0 && inputNumber < 10) {
        val (handList, playerDrawPile) = player.hand.handCards.head.processEffect(inputNumber - 1, player.hand, player.playerDrawPile)
        if (player.mayPlayAction == 1)
          print(s"No more Actions available for ${player.name}, moving to Buying-Phase, please press 'Enter' to confirm\n")
          controller.setTurnState(TurnState.buyingPhase)
        player.hand = handList
        player.playerDrawPile = playerDrawPile
      } else if (inputNumber == 0) {
        println("Skipping Action-Phase! Press Enter to Start Buying-Phase.\n")
        controller.setTurnState(TurnState.buyingPhase)
      }
    } else if (controller.turnState == TurnState.buyingPhase) {
      player.hand.handValue += countGold(player.hand)
      println(s"Player ${player.name}, has ${player.hand.handValue} money, which card/s do you want to buy?\n")
    } else {
      println("bad input, please press a number from 1 to 9 on your keyboard and confirm with 'Enter'.\n")
    }
  }

  def countGold(hand: Hand) : Int = {
    var handVal = 0
    for(i<-0 to hand.handCards.length - 1) {
      if(hand.handCards(i).cardType == 1) {
        handVal += player.hand.handCards(i).extraGold
      }
    }
    println(handVal)
    handVal
  }

  override def printTui(): Unit = ???

  override def handle(): Unit = ???
}
