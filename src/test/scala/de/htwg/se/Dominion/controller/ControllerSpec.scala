package de.htwg.se.Dominion.controller

import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" should {
    val controller = new Controller
    "have an initial state" in {
      controller.gameState should be(GameState.startScreen)
    }
    "be able to switch states" in {
      controller.setGameState(GameState.playerOneTurn)
      controller.gameState should be(GameState.playerOneTurn)
    }
    "be able to end the game" in {
      controller.setGameState(GameState.endScreen)
      controller.gameState should be(GameState.endScreen)
    }
  }
}
