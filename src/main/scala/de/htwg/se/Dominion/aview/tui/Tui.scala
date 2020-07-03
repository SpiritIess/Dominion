package de.htwg.se.Dominion.aview.tui

import de.htwg.se.Dominion.aview.tui._
import de.htwg.se.Dominion.aview.tui.State
import de.htwg.se.Dominion.controller.{Controller, GameState, TurnState}
import de.htwg.se.Dominion.model.{Board, Player}
import de.htwg.se.Dominion.util.Observer

import scala.collection.mutable.ListBuffer
import scala.util.Try

case class Tui(controller: Controller) extends Observer {
  controller.add(this)
  var state: State = TuiGameStart(controller, this)
  controller.startGame
  var contents = state

  override def update: Boolean = {
    contents = Tui.getTui(this, controller, controller.getPlayer.get).getOrElse(println("something went wrong setting the Tui!\n"))
    redraw
    true
  }
  def redraw:Unit = {
    print(Board().toString)
  }

  object Tui {
    def getTui(tui:Tui, controller: Controller, player: Player, amount:Int = 0) : Try[State] = {
    controller.gameState match {
      case GameState.startScreen => Try(new TuiGameStart(controller, tui))
      case GameState.setUpPlayers => Try(new TuiPlayerSetup(controller, tui, amount))
      case GameState.playerTurn =>
        controller.turnState match {
          case TurnState.actionPhase => Try(TuiActionPhase(controller, tui, player))
          case TurnState.buyingPhase => Try(TuiBuyPhase(controller, tui, player))
        }
      //case GameState.endScreen => Try(new TuiEndScreen)
    }
    }
  }
}
