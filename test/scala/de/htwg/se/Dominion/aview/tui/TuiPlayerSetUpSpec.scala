package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.TuiPlayerSetup
import de.htwg.se.Dominion.controller.{Controller, GameState}
import org.scalatest.{Matchers, WordSpec}

class TuiPlayerSetUpSpec extends WordSpec with Matchers {
  "A Dominion PlayerSetUp Tui" should{
    Dominion.playerList.drop(2)
    val controller = new Controller
    val tui2 = Tui(controller)
    val tui3 = Tui(controller)
    val tui4 = Tui(controller)
    controller.gameState = GameState.setUpPlayers

    "when set up with two players, create a playerList make it the first players turn" in {
      tui2.state = TuiPlayerSetup(controller, tui2, 2)
      tui2.state.processInputLine("Jakob Carsten")
      Dominion.playerList.length should be (2)
      tui2.controller.gameState should be (GameState.playerOneTurn)

    }
    /*
    "when set up with three players, create a playerList make it the first players turn" in {
      tui3.state = TuiPlayerSetup(controller, tui3, 3)
      tui3.state.processInputLine("Jakob Carsten Joachim")
      Dominion.playerList.length should be (3)
      tui2.controller.gameState should be (GameState.playerOneTurn)
    }
    "when set up with four players, create a playerList make it the first players turn" in {
      tui4.state = TuiPlayerSetup(controller, tui4, 4)
      tui4.state.processInputLine("Jakob Carsten Joachim Nathalie")
      Dominion.playerList.length should be (4)
      tui4.controller.gameState should be (GameState.playerOneTurn)
    }*/
  }

}
