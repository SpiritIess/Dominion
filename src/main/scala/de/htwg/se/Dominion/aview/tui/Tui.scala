package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui._
import de.htwg.se.Dominion.aview.tui.State
import de.htwg.se.Dominion.controller.controllerComponent.Controller
import de.htwg.se.Dominion.controller.{GameState, TurnState}
import de.htwg.se.Dominion.model.Board
import de.htwg.se.Dominion.model.playerComponent.Player
import de.htwg.se.Dominion.util.Observer

import scala.collection.mutable.ListBuffer
import scala.util.Try

case class Tui(controller: Controller) extends Observer {
  controller.add(this)
  var state: State = TuiGameStart(controller, this)
  controller.startGame
//  var contents = state

  override def update: Boolean = {
    state = Tui.getTui(this, controller, controller.getPlayer).get
    redraw
    true
  }
  def redraw:Unit = {
    print(Board().toString)
  }

  object Tui {
    def getTui(tui:Tui, controller: Controller, player: Option[Player], amount:Int = 0) : Try[State] = {
    controller.gameState match {
      case GameState.startScreen => Try(TuiGameStart(controller, tui))
      case GameState.setUpPlayers => Try(TuiPlayerSetup(controller, tui, amount))
      case GameState.playerTurn =>
        controller.turnState match {
          case TurnState.actionPhase => Try(TuiActionPhase(controller, tui, player.get))
          case TurnState.buyingPhase => Try(TuiBuyPhase(controller, tui, player.get))
        }
//      case GameState.endScreen => Try(new TuiEndScreen)
    }
    }
  }
}
