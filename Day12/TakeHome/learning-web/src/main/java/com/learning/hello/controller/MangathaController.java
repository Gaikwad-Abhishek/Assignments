package com.learning.hello.controller;

import java.util.ArrayList;
import java.util.List;

import com.learning.hello.controller.exception.UnsupportedActionException;

import mangatha.Card;
import mangatha.Game;
import mangatha.Player;

public class MangathaController {
	
	private List<Player> players;
	private Game startGame;
	public String currentCard;
	
	public MangathaController(){
		players = new ArrayList<Player>();
	}
	public void addPlayer(String name,int betAmount,boolean position,Card playerCard) {
		Player player = new Player(name,betAmount,position, playerCard);
		players.add(player);
	}
	
	public Card selectedCard(int rank, int suit) {
		Card card = new Card(rank, suit);
		return card;
	}
	
	public void startGame() {
		this.startGame = new Game(players);
	}
	
	public String getCard() {
		this.currentCard = startGame.drawCard();
		return this.currentCard;
	}
	
	public boolean isWinner() {
		if(players.size()!=0) {
		return startGame.checkState();
		}
		return false;
	}
	
	public String getCurrentCard() {
		return this.currentCard;
	}
	
	public String getWinner() {
		return startGame.winner.getName();
	}
	
	public int getBetAmount() {
		int sum=0;
		for(Player player : players) {
			sum = sum + player.getBetAmount();
		}
		return sum;
	}
	
	public boolean getPosition() {
		return startGame.getCardPosition();
	}
	public void reset() {
		players = null;
		startGame = null;
		currentCard = null;
	}
	
 }
