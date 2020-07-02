package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.TuiPlayerSetup
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.controller.{GameState, TurnState}
import org.scalatest.{Matchers, WordSpec}

class TuiActionPhaseSpec extends WordSpec with Matchers{
  "A Dominion ActionPhaseTui " should{
    Dominion.playerList = Dominion.playerList.drop(Dominion.playerList.size)
    val controller = new Controller
    val tui = Tui(controller)
    controller.gameState = GameState.setUpPlayers
    tui.state = TuiPlayerSetup(controller, tui, 2)
    tui.state.processInputLine("Jakob Karsten")
    val player = Dominion.playerList.head

    "have an initial gameState and an initial TurnState" in {
      controller.gameState should be (GameState.playerTurn)
      controller.turnState should be (TurnState.actionPhase)
    }
    "process the effect of the first action card (village))" in {
      tui.state.processInputLine("4")
      player.hand.handCards.size should be (5)
      player.playerDrawPile.pile.size should be (13)
      player.playerDiscardPile.pile.size should be (1)
      player.mayPlayAction should be (2)
      player.mayBuy should be (1)
    }
    "process the effect of the second action card (smithy)" in {
      tui.state.processInputLine(("4"))
      player.hand.handCards.size should be (7)
      player.playerDrawPile.pile.size should be (10)
      player.playerDiscardPile.pile.size should be (2)
      player.mayPlayAction should be (1)
    }
    "process the effect of the third action card (market)" in {
      tui.state.processInputLine("4")
      player.hand.handCards.size should be (7)
      player.playerDrawPile.pile.size should be (9)
      player.playerDiscardPile.pile.size should be (3)
      player.mayPlayAction should be (1)
      player.mayBuy should be (2)
      player.handValue should be (1)
    }
    "process the effect of the fourth action card (laboratory)" in {
      tui.state.processInputLine("4")
      player.hand.handCards.size should be (8)
      player.playerDrawPile.pile.size should be (7)
      player.playerDiscardPile.pile.size should be (4)
      player.mayPlayAction should be (1)
    }
    "process the effect of the fifth action card (funFairMarket)" in {
      tui.state.processInputLine("5")
      player.hand.handCards.size should be (7)
      player.playerDiscardPile.pile.size should be (5)
      player.mayPlayAction should be (2)
      player.mayBuy should be (3)
      player.handValue should be (3)
    }
    "process the effect of the sixth action card (moat)" in {
      tui.state.processInputLine("4")
      player.hand.handCards.size should be (8)
      player.playerDrawPile.pile.size should be (5)
      player.playerDiscardPile.pile.size should be (6)
      player.mayPlayAction should be (1)
      //player.handValue should be (3)
    }
    "process the effect of the seventh action card (village)" in {
      tui.state.processInputLine("6")
      player.hand.handCards.size should be (8)
      player.playerDrawPile.pile.size should be (4)
      player.playerDiscardPile.pile.size should be (7)
      player.mayPlayAction should be (2)
    }
    "process the effect of the eighth action cared (lumberjack)" in {
      tui.state.processInputLine("4")
      player.hand.handCards.size should be (7)
      player.playerDrawPile.pile.size should be (4)
      player.playerDiscardPile.pile.size should be (8)
      player.mayPlayAction should be (1)
      player.mayBuy should be (4)
      player.handValue should be (5)
    }
    "process the effect of the ninth action card (village)" in {
      tui.state.processInputLine("7")
      player.hand.handCards.size should be (7)
      player.playerDrawPile.pile.size should be (3)
      player.playerDiscardPile.pile.size should be (9)
      player.mayPlayAction should be (2)
      //player.handValue should be (5)
    }
    "process the effect of the tenth action card (moneyLender)" in {
      tui.state.processInputLine("4")
      player.hand.handCards.size should be (5)
      player.playerDrawPile.pile.size should be (3)
      player.playerDiscardPile.pile.size should be (10)
      player.mayPlayAction should be (1)
      player.handValue should be (8)
    }
    "process the effect of the eleventh and last action card (adventurer)" in {
      tui.state.processInputLine("3")
      player.hand.handCards.size should be (6)
      player.playerDrawPile.pile.size should be (0)
      player.playerDiscardPile.pile.size should be (12)
      player.mayPlayAction should be (0)
      //player.handValue should be (8)
    }
  }
}