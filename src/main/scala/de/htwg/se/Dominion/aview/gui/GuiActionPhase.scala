package de.htwg.se.Dominion.aview.gui

import java.awt.Color

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.{Controller, GameState, TurnState}
import de.htwg.se.Dominion.model.{Card, Pile, Player}
import javax.swing.{BorderFactory, ImageIcon}

import scala.swing.BorderPanel.Position._
import scala.swing._
import scala.swing.event._
import Swing._
import scala.collection.mutable.ListBuffer
import scala.collection.immutable

class GuiActionPhase(tui:Tui, controller: Controller) extends BoxPanel(Orientation.Vertical){
  preferredSize = new Dimension(1500, 900)
  val player: Player = controller.getPlayer match {
    case Some(p) => p
    case None => print("Failed getting a player"); System.exit(-1); Player("?")
  }
  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 15)
  val infoPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Player: " + player)
    contents += new Label("Actions: " + player.mayPlayAction)
    contents += new Label("Buys: " + player.mayBuy)
    contents += new Label("Money: " + player.hand.countGold())
    font = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 20)
    border = BorderFactory.createLineBorder(Color.BLACK, 2)
  }
  contents += new BorderPanel {
    layout(infoPanel) = North
  }
  val deckPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/card_back.jpg").getImage
      private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
    contents += new Label {
      text = "    Deck Count: " + player.playerDrawPile.pile.size
      font = myFont
    }
  }

  contents += deckPanel

  var s: String = ""
  var l: ListBuffer[Int] = ListBuffer()


  //Eigentlich will ich, dass jedes mal wenn eine action karte gespielt wird, das handPanel neu erstellt wird. WIE??
  val handPanel: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    val Hand: List[Card] = controller.getPlayer.get.hand.handCards
    val labelList: IndexedSeq[Label] = for (i <- Hand.indices) yield new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + Hand(i).name + ".jpg").getImage
      private val resize = temp.getScaledInstance(150, 250, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
      listenTo(mouse.clicks)
      reactions += {
        case _: MouseClicked =>
          println(s"Card number ${i + 1} " + Hand(i) + " was clicked ")
          controller.roundManager.processCardEffect(tui, player, i + 1)
          //das handPanel muss aktualisiert und neu ausgegeben werden, cards werden korrekt processed

      }
    }
    labelList.foreach(x => contents += x)
  }


  /*
  val cardStackPanel = new FlowPanel() {
    val cardStacks: Map[Card, Int] = Pile.piles
    val labelList : immutable.IndexedSeq[Label] = for (i<- cardStacks.foreach(x => x.))
  }
  */
  val yesButton = new Button("Yes")
  val noButton = new Button("No")
  val okButton = new Button("Okay")
  val doneButton = new Button("Done")

  val optionPanelQuestion: BoxPanel = new BoxPanel(Orientation.Vertical) {
    controller.turnState match {
      case TurnState.actionPhase =>
        contents += new Label("Which Card do you want to Play?")
        contents += new Label("Click on it")

      case TurnState.buyingPhase =>
        contents += new Label("Which Card do you want to buy?")
        contents += new Label("Click on its pile")

    }

  }
  val prevButton = new Button("\u2190")

  val playerPanel: BoxPanel = new BoxPanel(Orientation.Horizontal){
    contents += deckPanel
    contents += handPanel
    contents += optionPanelQuestion
  }
  contents += new BorderPanel {
    layout(infoPanel) = North
    layout(deckPanel) = Center
    layout(playerPanel) = South
    layout(prevButton) = West
  }

  reactions += {
    case ButtonClicked(`prevButton`) => controller.undo
  }
}
