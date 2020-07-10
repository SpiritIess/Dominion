package de.htwg.se.Dominion.aview.gui

import java.awt.BorderLayout

import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.GameState
import de.htwg.se.Dominion.controller.controllerComponent.{Controller, ControllerInterface}
import de.htwg.se.Dominion.util.{Observable, Observer}
import javax.swing.{JFrame, JScrollPane, JTextArea}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(tui: Tui, controller: ControllerInterface) extends Frame with Observer{
  controller.add(this)
//  listenTo(controller)

  title = "Dominion"

  contents = new GuiGameStart(tui, controller)

//  contents = new BorderPanel {
//  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
//      contents += new MenuItem(Action("Save") {controller.save()})
//      contents += new MenuItem(Action("Load") {controller.load()})
      contents += new MenuItem(Action("Quit") {System.exit(0)})

    }
  }

  visible = true
  centerOnScreen()
  resizable = true
//  pack()

  def redraw():Unit = {
    repaint
  }

  override def update: Boolean = {
    contents = SwingGui.getPanel(tui, controller)
    redraw()
    true
  }
}
object SwingGui {
  def getPanel(tui: Tui, controller: ControllerInterface): Panel = {
    controller.gameState match {
      case GameState.startScreen => new GuiGameStart(tui, controller)
      case GameState.setUpPlayers => new GuiPlayerSetup(tui, controller)
      case GameState.playerTurn => new GuiActionPhase(tui, controller)
      case GameState.endScreen => new GuiEndScreen(controller)
    }
  }
}