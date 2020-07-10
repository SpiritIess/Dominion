package de.htwg.se.Dominion.controller.controllerComponent

import com.google.inject.Inject
import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.tui.{Tui, TuiActionPhase, TuiEndScreen, TuiPlayerSetup}
import de.htwg.se.Dominion.controller.{GameState, TurnState}
import de.htwg.se.Dominion.model.Board
import de.htwg.se.Dominion.model.cardComponent.{Card, CardSet}
import de.htwg.se.Dominion.model.pileComponent.{DrawPile, Hand, Pile}
import de.htwg.se.Dominion.model.playerComponent.Player
import de.htwg.se.Dominion.util.{Observable, UndoManager}

class Controller @Inject() () extends ControllerInterface {

  var gameState: GameState.Value = GameState.startScreen
  var turnState: TurnState.Value = TurnState.actionPhase
  private var currentPlayer: Option[Player] = None
  private val undoManager = new UndoManager
  override var roundManager: RoundManager = RoundManager(this)

  override def startGame: Unit = {
    gameState = GameState.startScreen
    println("enter the number of players\n")
    notifyInController
  }
  override def notifyInController:Unit = {
    notifyObservers
  }

  override def startTurn(tui: Tui): Unit = {
    turnState = TurnState.actionPhase
    callNextPlayer(tui, Dominion.playerList.last)
    notifyInController
  }

  override def getScore: Map[Player, Int] = {
    var score: Map[Player, Int] = Map()
    Dominion.playerList.foreach(x => score = score + (x -> getDeckScore(x)))
    score
  }

  override def getDeckScore(player: Player): Int = {
    var deckScore: Int = 0
    deckScore += getPileScore(player, player.playerDrawPile.pile)
    deckScore += getPileScore(player, player.playerDiscardPile.pile)
    deckScore
  }

  override def getPileScore(player: Player, cards: List[Card]): Int = {
    cards match {
      case CardSet.propertyCard =>  1
      case CardSet.dukedomCard =>  2
      case CardSet.provinceCard =>  3
      case CardSet.gardenCard => (player.playerDrawPile.pile.length + player.playerDiscardPile.pile.length) / 10
    }
  }

  override def actionToBuyPhase(tui: Tui, player: Player): Unit = {
    roundManager.actionToBuyPhase(tui, player)
  }

  override def play(tui: Tui, player: Player, index: Int): Unit = {
    undoManager.doStep(new AnyCommand(turnState, tui, player, index, this))
    notifyInController
  }

  override def buyCard(tui: Tui, player:Player, card:Card) :Unit = {
    undoManager.doStep(new AnyCommand(turnState, tui, player, 0, this, card))
    notifyInController
  }

  override def getPlayer: Option[Player] =  {
    currentPlayer
  }

  override def isEndGame(tui:Tui) : Unit = {
    var emptyPileCounter : Int = 0
    for(i<-  Pile.piles) {
      if(emptyPileCounter != 3) {
        if (i._2 == 0) {
          emptyPileCounter += 1
        }
      } else {
        println("Game-End-condition is met, calculating winner!\n")
        tui.state = TuiEndScreen(this)
        gameState = GameState.endScreen
        notifyInController
      }
    }
  }

  override def setUpPlayers(tui:Tui, amount:Int): Unit = {
    gameState = GameState.setUpPlayers
    println(Board().toString())
    //println("type in the names of the players, using a space as seperator\n")
    tui.state = TuiPlayerSetup(this, tui, amount)
    //SwingGui.content = new GuiPlayerSetup(this, tui)
    notifyInController
  }

  override def updatePlayerList(tui: Tui, playerString: String): List[Player] = {
    val playerArray = playerString.split(" ")
    println(playerArray.mkString("\n"))
    //println(Board().toString)
    playerArray.foreach(i => {
      Dominion.playerList += Player(i)
    })
    startTurn(tui)
    Dominion.playerList.toList
  }

  override def quitGame(tui:Tui): Unit = {
    gameState = GameState.endScreen
    //    tui.state = TuiEndScreen(this, tui)
    //    println("you chose to quit the game\n")
    notifyInController
  }

  override def refreshPlayer(player: Player) :Unit = {
    if(player.playerDrawPile.pile.size >= 5) {
      val (handList, newDrawPile) = player.playerDrawPile.drawAdditional(5)
      player.hand = Hand(handList)
      player.playerDrawPile = newDrawPile
    } else {
      val temp = player.playerDrawPile.drawAdditional(player.playerDrawPile.pile.size)
      player.playerDrawPile = DrawPile(player.playerDrawPile.pile).refresh(player.playerDiscardPile)
      player.playerDiscardPile.refresh
      val (handList, newDrawPile) = player.playerDrawPile.drawAdditional(5 - temp._1.size)
      player.hand = Hand(handList)
      player.playerDrawPile = newDrawPile
    }
  }

  override def callNextPlayer(tui:Tui, player: Player): Unit = {
    var nextPlayer: Option[Player] = None
    val nextPlayerIndex = Dominion.playerList.indexOf(player) + 1
    if (nextPlayerIndex == Dominion.playerList.length) {
      nextPlayer = Some(Dominion.playerList.head)
    } else {
      nextPlayer = Some(Dominion.playerList(nextPlayerIndex))
    }
    if(player.hand.handCards.nonEmpty) {
      player.playerDiscardPile = player.playerDiscardPile.discardCards(player.hand.handCards)
      player.hand = player.hand.emtpyHand
    }
    refreshPlayer(player)
    player.mayBuy = 1
    player.mayPlayAction = 1
    gameState = GameState.playerTurn
    turnState = TurnState.actionPhase
    currentPlayer = nextPlayer
    playerReset()
    tui.state = TuiActionPhase(this, tui, currentPlayer.get)
    notifyInController
  }

  override def playerReset(): Unit = {
    currentPlayer.get.mayBuy = 1
    currentPlayer.get.mayPlayAction = 1
    currentPlayer.get.handValue = 0
    //    val (hand, drawpile) = currentPlayer.get.playerDrawPile.drawAdditional(5)
    //    currentPlayer.get.hand = Hand(hand)
    //    currentPlayer.get.playerDrawPile = drawpile

  }

  override def callPreviousPlayer(tui: Tui, player:Player): Unit = {
    val prevPlayerIndex = Dominion.playerList.indexOf(player) - 1
    if (prevPlayerIndex == -1) {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(Dominion.playerList.length))
      gameState = GameState.playerTurn
      turnState = TurnState.actionPhase
      notifyInController
    }else {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(prevPlayerIndex))
      gameState = GameState.playerTurn
      turnState = TurnState.actionPhase
      notifyInController
    }
  }

  override def discardCard(player: Player, card: Card, positon: Int) : Unit = {
    player.playerDiscardPile = player.playerDiscardPile.discardCard(card)
    player.hand = player.hand.removeCardFromHand(positon)
  }

  override def discardCards(player:Player, cards: List[Card]) :Unit = {
    player.playerDiscardPile = player.playerDiscardPile.discardCards(cards)
    for (i <- cards.indices){
      player.hand = player.hand.removeCardFromHand(0)
    }
  }

  override def putOnDiscardPile(player: Player, card: Card): Unit = {
    player.playerDiscardPile = player.playerDiscardPile.discardCard(card)
  }

  override def undo: Unit = {
    undoManager.undoStep
    notifyInController
  }

  override def redo: Unit = {
    undoManager.redoStep
    notifyInController
  }
}