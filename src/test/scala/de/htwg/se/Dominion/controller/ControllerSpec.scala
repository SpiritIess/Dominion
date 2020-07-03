package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.{Tui, TuiActionPhase, TuiBuyPhase}
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.{CardSet, Player}
import de.htwg.se.Dominion.util.Observer
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    val player = Player("Jakob")
    val index = 1
    val controller = new Controller()
    val tui = new Tui(controller)
    val observer = new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Boolean = {updated = true; updated}
    }
    controller.add(observer)
    "have an initial state" in {
      controller.gameState should be(GameState.startScreen)
    }
    "notify its Observer after starting the Game" in {
      controller.startGame
      observer.updated should be (true)
      controller.gameState should be (GameState.startScreen)
    }
    "notify its Observer when starting a players turn" in {
      controller.startTurn(tui)
      observer.updated should be (true)
      controller.turnState should be(TurnState.actionPhase)
    }
    "notify its Observer after processing an action-card effect" in {
      controller.roundManager.processCardEffect(tui,player, index)
      observer.updated should be (true)
    }
    "notify its Observer after switching form action to buy phase" in {
      controller.roundManager.actionToBuyPhase(tui, player)
      observer.updated should be (true)
      controller.turnState should be (TurnState.buyingPhase)
      tui.state should be (TuiBuyPhase(controller, tui, player))
    }
    "notify its Observers after setting up two to four players" in {
      Dominion.playerList = new ListBuffer[Player]
      controller.setUpPlayers(tui, 2)
      tui.state.processInputLine("Jakob Karsten")
      observer.updated should be (true)
      controller.gameState should be (GameState.playerTurn)
      Dominion.playerList.length should be (2)
      Dominion.playerList.head should be (Player("Jakob"))
      Dominion.playerList(1) should be (Player("Karsten"))
    }
//    "notify its Observer after starting the first turn of the game" in {
//      controller.callNextPlayer(tui, )
//      observer.updated should be (true)
//      controller.gameState should be (GameState.playerOneTurn)
//      controller.turnState should be (TurnState.actionPhase)
      //tui.state should be (TuiActionPhase(controller, tui, player))
//    }
    "notify its Observers after quitting the game" in {
      controller.quitGame(tui)
      observer.updated should be (true)
      controller.gameState should be (GameState.endScreen)
    }
    "notify its Observers after discarding a Card to the DiscardPile" in {
      val player2 = Player("Markus")
      val handIndex = 1
      println("Markus hand:", player2.hand.toString)
      controller.discardCard(player2,player2.hand.handCards(handIndex),handIndex)
      observer.updated should be (true)
      player2.hand.handCards.length should be(4)
    }
    "notify its Observers after discarding multiple (here: all) cards in hand" in {
      val player2 = Player("Markus")
      controller.discardCards(player2, player2.hand.handCards)
      observer.updated should be (true)
      player2.hand.handCards.length should be (0)
    }
    "notify its Observers after a card is purchased and put on the players discardPile" in {
      val player2 = Player("Markus")
      controller.putOnDiscardPile(player2, CardSet.copperCard)
      observer.updated should be (true)
      player2.playerDiscardPile.pile.length should be (1)
    }
  }
}
