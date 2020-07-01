package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.controller.GameState
import org.scalatest.{Matchers, WordSpec}

class TuiGameStartSpec extends WordSpec with Matchers {
  "Dominion Tui with state GameStart" should {
    val controller = new Controller
    val tui = Tui(controller)

    "have an initial state" in {
      tui.state should be (TuiGameStart(controller, tui))
      controller.gameState should be (GameState.startScreen)
    }
    "do nothing other than printing a error message on bad input like '5'" in {
      tui.state.processInputLine("5")
      controller.gameState should be (GameState.startScreen)
    }
    "change its gameState to 'endScreen' when one inputs 'q'" in {
      tui.state.processInputLine("q")
      controller.gameState should be (GameState.endScreen)
    }
    "change its gameState to 'setUpPlayers' when a number between 2 and 4 is put as input" in {
      tui.state.processInputLine("2")
      controller.gameState should be (GameState.setUpPlayers)
    }
  }
}
