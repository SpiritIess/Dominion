package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.aview.tui.TuiGameStart

class Controller {
  var gameState : GameState.Value = GameState.startScreen
  def setGameState(gameState: GameState.Value): Unit = this.gameState = gameState
}
