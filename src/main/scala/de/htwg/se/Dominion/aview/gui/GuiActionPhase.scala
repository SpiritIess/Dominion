package de.htwg.se.Dominion.aview.gui

import java.awt.Color

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.Tui
import de.htwg.se.Dominion.controller.controllerComponent.Controller
import de.htwg.se.Dominion.controller.{GameState, TurnState}
import de.htwg.se.Dominion.model.playerComponent.Player
import de.htwg.se.Dominion.model.cardComponent.{Card, CardSet}
import de.htwg.se.Dominion.model.pileComponent.Pile
import de.htwg.se.Dominion.util.Observer
import javax.swing.{BorderFactory, ImageIcon}

import scala.swing.BorderPanel.Position._
import scala.swing._
import scala.swing.event._
import Swing._
import scala.collection.immutable.TreeMap
import scala.collection.mutable.ListBuffer
import scala.collection.{immutable, mutable}
import scala.swing.BorderPanel.Position

class GuiActionPhase(tui:Tui, controller: Controller) extends BoxPanel(Orientation.Vertical){
  preferredSize = new Dimension(1400, 1000)
  val player: Player = controller.getPlayer match {
    case Some(p) => p
    case None => print("Failed getting a player"); Player("?")
  }
  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 15)
  val infoPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Player: " + player)
    contents += new Label("Actions: " + player.mayPlayAction)
    contents += new Label("Buys: " + player.mayBuy)
    contents += new Label("Money: " + player.handValue)
    font = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 20)
    border = BorderFactory.createLineBorder(Color.BLACK, 2)
  }
//  contents += new BorderPanel {
//    layout(infoPanel) = North
//  }
  val drawPilePanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/card_back.jpg").getImage
      private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
    contents += new Label {
      text = "    Draw Pile: " + player.playerDrawPile.pile.size
      font = myFont
    }
  }
//  contents += drawPilePanel

  val handPanel: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    val Hand: List[Card] = controller.getPlayer.get.hand.handCards
    val labelList: IndexedSeq[Label] = for (i <- Hand.indices) yield new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + Hand(i).name + ".jpg").getImage
      private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
      listenTo(mouse.clicks)
      if (controller.turnState == TurnState.actionPhase) {
        reactions += {
          case _: MouseClicked =>
            println(s"Card number ${i + 1} " + Hand(i) + " was clicked ")
            controller.roundManager.processCardEffect(tui, player, i + 1)
        }
      } else {
        reactions += {
          case _: MouseClicked =>
            println(s"Card number ${i + 1} " + Hand(i) + " was clicked ")
            println("Currently not in action phase")

        }
      }
    }
    labelList.foreach(x => contents += x)
  }


  val moneyDrawPilesPanel: FlowPanel = new FlowPanel() {
    val decks: Map[Card, Int] = Map((CardSet.copperCard, Pile.piles(CardSet.copperCard)),
      (CardSet.silverCard, Pile.piles(CardSet.silverCard)),
      (CardSet.goldCard, Pile.piles(CardSet.goldCard)))
    val labelList: Iterable[BoxPanel] = for (i <- decks) yield new BoxPanel(Orientation.Vertical) {
      contents += new Label("Count: " + i._2)
      contents += new Label {
        private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + i._1.name + ".jpg").getImage
        private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        font = myFont
        listenTo(mouse.clicks)
        if (controller.turnState == TurnState.buyingPhase) {
          reactions += {
            case _: MouseClicked => {
              controller.roundManager.processBuy(tui, player, i._1)
            }
          }
        } else {
          reactions += {
            case _: MouseClicked => println("Currently not in buying phase")
          }
        }
      }
    }
    labelList.foreach(x => contents += x)
  }


//  val moneyDrawPilesPanel = new BoxPanel(Orientation.Horizontal) {
//    val decks: Map[Card, Int] = Map((CardSet.copperCard, Pile.piles(CardSet.copperCard)),
//      (CardSet.silverCard, Pile.piles(CardSet.silverCard)),
//      (CardSet.goldCard, Pile.piles(CardSet.goldCard)))
//    val labelList: Iterable[Label] = for (i <- decks) yield new Label {
//      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + i._1.name + ".jpg").getImage
//      private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
//      icon = new ImageIcon(resize)
//      text = "Count: " + i._2
//      font = myFont
//      listenTo(mouse.clicks)
//      reactions += {
//        case _: MouseClicked => {
//          controller.roundManager.processBuy(tui, player, i._1)
//        }
//      }
//    }
//    labelList.foreach(x => contents += x)
//  }

  val pointsDrawPilesPanel: FlowPanel = new FlowPanel() {
    val decks: Map[Card, Int] = Map((CardSet.propertyCard, Pile.piles(CardSet.propertyCard)),
      (CardSet.dukedomCard, Pile.piles(CardSet.dukedomCard)),
      (CardSet.provinceCard, Pile.piles(CardSet.provinceCard)),
      (CardSet.gardenCard, Pile.piles(CardSet.gardenCard)))
    val labelList: Iterable[BoxPanel] = for (i <- decks) yield new BoxPanel(Orientation.Vertical) {
      contents += new Label("Count: " + i._2)
      contents += new Label {
        private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + i._1.name + ".jpg").getImage
        private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        font = myFont
        listenTo(mouse.clicks)
        if (controller.turnState == TurnState.buyingPhase) {
          reactions += {
            case _: MouseClicked => {
              controller.roundManager.processBuy(tui, player, i._1)
            }
          }
        } else {
          reactions += {
            case _: MouseClicked => println("Currently not in buying phase")
          }
        }
      }
    }
    labelList.foreach(x => contents += x)
  }

  val actionCardStackPanel1: FlowPanel = new FlowPanel() {
    val decks: Map[Card, Int] = Map((CardSet.moatCard, Pile.piles(CardSet.moatCard)),
      (CardSet.villageCard, Pile.piles(CardSet.villageCard)),
      (CardSet.lumberjackCard, Pile.piles(CardSet.lumberjackCard)),
      (CardSet.smithyCard, Pile.piles(CardSet.smithyCard)),
      (CardSet.moneyLenderCard, Pile.piles(CardSet.moneyLenderCard)))
    val labelList: Iterable[BoxPanel] = for (i <- decks) yield new BoxPanel(Orientation.Vertical) {
      contents += new Label("Count: " + i._2)
      contents += new Label {
        private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + i._1.name + ".jpg").getImage
        private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        font = myFont
        listenTo(mouse.clicks)
        if (controller.turnState == TurnState.buyingPhase) {
          reactions += {
            case _: MouseClicked => {
              controller.roundManager.processBuy(tui, player, i._1)
            }
          }
        } else {
          reactions += {
            case _: MouseClicked => println("Currently not in buying phase")
          }
        }
      }
    }
    labelList.foreach(x => contents += x)
  }

  val actionCardStackPanel2: FlowPanel = new FlowPanel() {
    val decks: Map[Card, Int] = Map((CardSet.marketCard, Pile.piles(CardSet.marketCard)),
      (CardSet.adventurerCard, Pile.piles(CardSet.adventurerCard)),
      (CardSet.labCard, Pile.piles(CardSet.labCard)),
      (CardSet.funFairCard, Pile.piles(CardSet.funFairCard)))
    val labelList: Iterable[BoxPanel] = for (i <- decks) yield new BoxPanel(Orientation.Vertical) {
      contents += new Label("Count: " + i._2)
      contents += new Label {
        private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/" + i._1.name + ".jpg").getImage
        private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        font = myFont
        listenTo(mouse.clicks)
        if (controller.turnState == TurnState.buyingPhase) {
          reactions += {
            case _: MouseClicked => {
              controller.roundManager.processBuy(tui, player, i._1)
            }
          }
        } else {
          reactions += {
            case _: MouseClicked => println("Currently not in buying phase")
          }
        }
      }
    }
    labelList.foreach(x => contents += x)
  }


  val discardPilePanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label {
      private val temp = new ImageIcon("src/main/scala/de/htwg/se/Dominion/aview/resources/card_back.jpg").getImage
      private val resize = temp.getScaledInstance(130, 203, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
    contents += new Label {
      text = "    Discard Pile: " + player.playerDiscardPile.pile.size
      font = myFont
    }
  }



  val yesButton = new Button("Yes")
  val noButton = new Button("No")
  val okButton = new Button("Okay")
  val endPhase =
    if(controller.turnState == TurnState.buyingPhase)
      new Button("End Buy Phase")
    else new Button("End Action Phase")
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

  val buttonPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += endPhase
    contents += prevButton
  }

  val playerPanel: BoxPanel = new BoxPanel(Orientation.Horizontal){
    contents += buttonPanel
    contents += drawPilePanel
    contents += discardPilePanel
    contents += handPanel
    contents += optionPanelQuestion
  }
  val moneyPointsDrawPilesPanel = new FlowPanel(){
    contents += moneyDrawPilesPanel
    contents += pointsDrawPilesPanel
  }
  val drawPilesPanel : BoxPanel = new BoxPanel(Orientation.Vertical){
    contents += moneyPointsDrawPilesPanel
    contents += actionCardStackPanel1
    contents += actionCardStackPanel2
  }

  contents += new BorderPanel {
    layout(infoPanel) = North
    layout(drawPilesPanel) = Center
    layout(playerPanel) = South
  }

  listenTo(prevButton)
  listenTo(endPhase)

  reactions += {
    case ButtonClicked(`prevButton`) => controller.undo
    case ButtonClicked(`endPhase`) =>
      if(controller.turnState == TurnState.buyingPhase)
        controller.callNextPlayer(tui, player)
      else controller.actionToBuyPhase(tui, player)
  }
}
