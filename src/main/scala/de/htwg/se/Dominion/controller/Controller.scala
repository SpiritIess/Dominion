package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.aview.tui.TuiGameStart
import de.htwg.se.Dominion.model.Player

class Controller {
  var gameState : GameState.Value = GameState.startScreen
  def setGameState(gameState: GameState.Value): Unit = this.gameState = gameState
}
