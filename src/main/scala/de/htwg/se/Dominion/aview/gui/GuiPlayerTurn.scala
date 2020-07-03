package de.htwg.se.Dominion.aview.gui

import java.awt.Color

import de.htwg.se.Dominion.controller.Controller
import de.htwg.se.Dominion.model.Player
import javax.swing.BorderFactory

import scala.swing._
import scala.swing.event._
import Swing._

class GuiPlayerTurn(controller: Controller) extends BoxPanel(Orientation.Vertical){
  preferredSize = new Dimension(1800, 1200)
  val player: Player = controller.getPlayer.get
  val infoPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Player: " + (player)
    contents += new Label("Actions: " + controller.getCurrentActions)
    contents += new Label("Buys: " + controller.getCurrentBuys)
    contents += new Label("Money: " + controller.getCurrentMoney)
//    font = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 20)
    border = BorderFactory.createLineBorder(Color.BLACK, 2)
  }
}
