package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.model.Player
import de.htwg.se.Dominion.util.Command

class PlayCommand(player: Player, index: Int, controller: Controller) extends Command{
  override def doStep: Unit = {
    ???
  }

  override def undoStep: Unit = ???

  override def redoStep: Unit = ???
}
