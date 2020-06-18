package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.aview.tui.TuiGameStart
import de.htwg.se.Dominion.model.Player

class Controller {
  var gameState : GameState.Value = GameState.startScreen
  var turnState : TurnState.Value = TurnState.actionPhase
  def setGameState(gameState: GameState.Value): Unit = this.gameState = gameState
  def setTurnState(turnState: TurnState.Value): Unit = this.turnState = turnState
}
