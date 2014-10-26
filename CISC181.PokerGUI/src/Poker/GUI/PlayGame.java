package Poker.GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.Hand;
import pokerBase.Player;
import pokerEnums.eGame;
import pokerEnums.eRank;
import pokerEnums.eSuit;

public class PlayGame {
	private eGame gme;
	private ArrayList<Player> players = new ArrayList<Player>();

	public PlayGame(eGame gme) {
		this.gme = gme;
	}

	public eGame GetGame() {
		return this.gme;
	}

	public void AddPlayer(Player p) {
		players.add(p);
	}

	public void run() {

		playHand();

	}
	
	public void playHand() {
		switch (gme) {
		
		case FiveStud: {

			// Set a new deck d
			Deck d = new Deck(0);
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.EvalHand();
				p.SetHand(h);
			}
			// Determine winner
			Collections.sort(players, Player.PlayerRank);
			for (Player p : players) {
				if (p == players.get(0)){
					p.setWinner(true);
				}
				else{
					p.setWinner(false);
				}
			}
			
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);

			}

		} break;
		
		case FiveStudTwoJoker: {

			// Set a new deck d
			Deck d = new Deck(4);
			
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.HandleJokerWilds();
				p.SetHand(h);
			}
			
			// Determine winner
			Collections.sort(players, Player.PlayerRank);
			for (Player p : players) {
				if (p == players.get(0)){
					p.setWinner(true);
				}
				else{
					p.setWinner(false);
				}
			
			}
			
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);

			}

		} break;
		
		case DeucesWild: {

			// Set a new deck d with wild 2s
			ArrayList<Card> Wilds = new ArrayList<Card>();
			Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO, 40));
			Wilds.add(new Card(eSuit.HEARTS, eRank.TWO, 1));
			Wilds.add(new Card(eSuit.SPADES, eRank.TWO, 14));
			Wilds.add(new Card(eSuit.CLUBS, eRank.TWO, 27));
			Deck d = new Deck(0, Wilds);
			
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();	
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.HandleJokerWilds();
				p.SetHand(h);
			}
			
			Collections.sort(players, Player.PlayerRank);
			for (Player p : players) {
				if (p == players.get(0)){
					p.setWinner(true);
				}
				else{
					p.setWinner(false);
				}
			
			}
			
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);

			}

		} break;
		
		case FiveDraw: {

			// Set a new deck d
			Deck d = new Deck(0);
			
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 5; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				h.EvalHand();
				p.SetHand(h);
			}
			
			for (Player p: players){
				
			}

			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);

			}

		} break;
		
		case TexasHoldEm: {

			// Set a new deck d
			Deck d = new Deck(0);
			
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 2; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				p.SetHand(h);
			}
			
			//Create a community
			Hand community = new Hand();
			for (int i = 0; i < 5; i++) {
				Card c = d.drawFromDeck();
				community.AddCardToHand(c);
			}
			
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}
			
		} break;
		
		case Omaha: {

			// Set a new deck d
			Deck d = new Deck(0);
			
			// Reset each player's hand
			for (Player p : players) {
				p.resetHand();
			}

			// Create a hand for each player
			for (Player p : players) {
				Hand h = new Hand();
				for (int i = 0; i < 4; i++) {
					Card c = d.drawFromDeck();
					h.AddCardToHand(c);
				}
				p.SetHand(h);
			}
			
			//Create a community
			Hand community = new Hand();
			for (int i = 0; i < 5; i++) {
				Card c = d.drawFromDeck();
				community.AddCardToHand(c);
			}
				
			
			// Player has the hand, call the playerUpdated method to set the
			// screen
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);

			}

		} break;
		
		}
		
	}
}
