package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.GameState.gameState
import de.htwg.se.Dominion.controller.TurnState.turnState
import de.htwg.se.Dominion.model.Player
import de.htwg.se.Dominion.util.Command

class AnyCommand(turnState: TurnState.Value, tui: Tui, player: Player, index: Int, controller: Controller) extends Command{

  var mementoTurn: turnState = turnState
  var mementoPlayer = player

  override def doStep: Unit = {
    Dominion.playerList= controller.roundManager.getPlayerList(turnState, tui, player, index)._1
    controller.turnState = controller.roundManager.getPlayerList(turnState, tui, player, index)._2
  }

  override def undoStep: Unit = {
    val newMementoTurn = controller.turnState
    val playerIndex = Dominion.playerList.indexOf(player)
    val newMementoPlayer = Dominion.playerList(playerIndex)
    controller.turnState = mementoTurn
    Dominion.playerList(playerIndex) = mementoPlayer

    mementoTurn = newMementoTurn
    mementoPlayer = newMementoPlayer
  }


  override def redoStep: Unit = {
    val newMementoTurn = controller.turnState
    val playerIndex = Dominion.playerList.indexOf(player)
    val newMementoPlayer = Dominion.playerList(playerIndex)
    controller.turnState = mementoTurn
    Dominion.playerList(playerIndex) = mementoPlayer

    mementoTurn = newMementoTurn
    mementoPlayer = newMementoPlayer
  }
}