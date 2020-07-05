package de.htwg.se.Dominion.controller

import de.htwg.se.Dominion.Dominion
import de.htwg.se.Dominion.aview.gui.{GuiPlayerSetup, SwingGui}
import de.htwg.se.Dominion.aview.tui.{Tui, TuiActionPhase, TuiPlayerSetup, TuiEndScreen}
import de.htwg.se.Dominion.model.{Board, Card, CardSet, Pile, Player}
import de.htwg.se.Dominion.util.{Observable, UndoManager}

class Controller(var gameState: GameState.Value = GameState.startScreen,
                 var turnState: TurnState.Value = TurnState.actionPhase) extends Observable{

  private var currentPlayer: Option[Player] = None
  private val undoManager = new UndoManager
  var roundManager: RoundManager = RoundManager(this)

  def startGame: Unit = {
    gameState = GameState.startScreen
    println("enter the number of players\n")
    notifyInController
  }
  def notifyInController:Unit = {
    notifyObservers
  }

  def startTurn(tui: Tui): Unit = {
    turnState = TurnState.actionPhase
    callNextPlayer(tui, Dominion.playerList.last)
    notifyInController
  }

  def getScore: Map[Player, Int] = {
    var score: Map[Player, Int] = Map()
    Dominion.playerList.foreach(x => score = score + (x -> getDeckScore(x)))
    score
  }

  def getDeckScore(player: Player): Int = {
    var deckScore: Int = 0
    deckScore += getPileScore(player, player.playerDrawPile.pile)
    deckScore += getPileScore(player, player.playerDiscardPile.pile)
    deckScore
  }

  def getPileScore(player: Player, cards: List[Card]): Int = {
    cards match {
      case CardSet.propertyCard =>  1
      case CardSet.dukedomCard =>  2
      case CardSet.provinceCard =>  3
      case CardSet.gardenCard => (player.playerDrawPile.pile.length + player.playerDiscardPile.pile.length) / 10
    }
  }

  def play(tui: Tui, player: Player, index: Int): Unit = {
    undoManager.doStep(new AnyCommand(turnState, tui, player, index, this))
    notifyInController
  }

  def getPlayer: Option[Player] =  {
    currentPlayer
  }

//  def firstTurn(tui:Tui): Unit ={
//    gameState = GameState.playerOneTurn
//    tui.state = TuiActionPhase(this, tui, Dominion.playerList.head)
//    println(s"${Dominion.playerList.head}, choose an action-card from your hand, " +
//      s"or press '0' to skip to the Buying-Phase and confirm your decision by pressing 'Enter'!\n")
//    notifyObservers
//  }
  def isEndGame(tui:Tui) : Unit = {
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

  def setUpPlayers(tui:Tui, amount:Int): Unit = {
    gameState = GameState.setUpPlayers
    println(Board().toString())
    //println("type in the names of the players, using a space as seperator\n")
    tui.state = TuiPlayerSetup(this, tui, amount)
    //SwingGui.content = new GuiPlayerSetup(this, tui)
    notifyInController
  }

  def updatePlayerList(tui: Tui, playerString: String): List[Player] = {
    val playerArray = playerString.split(" ")
    println(playerArray.mkString("\n"))
    println(Board().toString)
    playerArray.foreach(i => {
      Dominion.playerList += Player(i)
    })
    startTurn(tui)

    Dominion.playerList.toList
  }

  def quitGame(tui:Tui): Unit = {
    gameState = GameState.endScreen
//    tui.state = TuiEndScreen(this, tui)
//    println("you chose to quit the game\n")
    notifyInController
  }

  def callNextPlayer(tui:Tui, player: Player): Unit = {
    var nextPlayer: Option[Player] = None
    val nextPlayerIndex = Dominion.playerList.indexOf(player) + 1

    if (nextPlayerIndex == Dominion.playerList.length) {
      nextPlayer = Some(Dominion.playerList.head)
    } else {
      nextPlayer = Some(Dominion.playerList(nextPlayerIndex))
    }

    gameState = GameState.playerTurn
    turnState = TurnState.actionPhase
    currentPlayer = nextPlayer
    tui.state = TuiActionPhase(this, tui, nextPlayer.get)
  }

  def callPreviousPlayer(tui: Tui, player:Player): Unit = {
    val prevPlayerIndex = Dominion.playerList.indexOf(player) - 1
    if (prevPlayerIndex == -1) {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(Dominion.playerList.length))
      gameState = GameState.playerTurn
      turnState = TurnState.actionPhase
    }else {
      tui.state = TuiActionPhase(this, tui, Dominion.playerList(prevPlayerIndex))
      gameState = GameState.playerTurn
      turnState = TurnState.actionPhase
    }
  }

  def discardCard(player: Player, card: Card, positon: Int) : Unit = {
    player.playerDiscardPile = player.playerDiscardPile.discardCard(card)
    player.hand = player.hand.removeCardFromHand(positon)
  }

  def discardCards(player:Player, cards: List[Card]) :Unit = {
    player.playerDiscardPile = player.playerDiscardPile.discardCards(cards)
    for (i <- cards.indices){
      player.hand = player.hand.removeCardFromHand(0)
    }
  }

  def putOnDiscardPile(player: Player, card: Card): Unit = {
    player.playerDiscardPile = player.playerDiscardPile.discardCard(card)
  }

  def undo(): Unit = {
    undoManager.undoStep
    notifyInController
  }

  def redo(): Unit = {
    undoManager.redoStep
    notifyInController
  }
}