package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.TuiPlayerSetup
import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.controller.GameState
import de.htwg.se.Dominion.model.Player
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer

class TuiPlayerSetUpSpec extends WordSpec with Matchers {
  "A Dominion PlayerSetUp Tui" should{
    val controller = new Controller
    val tui2 = Tui(controller)
    /*val tui3 = Tui(controller)
    val tui4 = Tui(controller)*/
    controller.gameState = GameState.setUpPlayers

    "when set up with two players, create a playerList make it the first players turn" in {
      Dominion.playerList = new ListBuffer[Player]
      tui2.state = TuiPlayerSetup(controller, tui2, 2)
      tui2.state.processInputLine("Jakob Karsten")
      Dominion.playerList.length should be (2)
      tui2.controller.gameState should be (GameState.playerTurn)

    }
    /*
    "when set up with three players, create a playerList make it the first players turn" in {
      tui3.state = TuiPlayerSetup(controller, tui3, 3)
      tui3.state.processInputLine("Jakob Karsten Joachim")
      Dominion.playerList.length should be (3)
      tui2.controller.gameState should be (GameState.playerOneTurn)
    }
    "when set up with four players, create a playerList make it the first players turn" in {
      tui4.state = TuiPlayerSetup(controller, tui4, 4)
      tui4.state.processInputLine("Jakob Karsten Joachim Nathalie")
      Dominion.playerList.length should be (4)
      tui4.controller.gameState should be (GameState.playerOneTurn)
    }*/
  }

}
