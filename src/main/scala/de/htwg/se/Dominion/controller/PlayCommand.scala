package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.model.Player
import de.htwg.se.Dominion.util.Command

class PlayCommand(tui: Tui, player: Player, index: Int, controller: Controller) extends Command{
  override def doStep: Unit = Dominion.playerList = controller.roundManager.getPlayerList(tui, player, index)

  override def undoStep: Unit = ???

  override def redoStep: Unit = ???
}
