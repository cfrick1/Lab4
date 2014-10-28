package Poker.GUI;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
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
	private ControlPanel controlPanel;
	private BoardPanel boardPanel;
	private final Object monitor = new Object();
	
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
	
	public ControlPanel getControlPanel(){
		return controlPanel;
	}
	
	public void setControlPanel(ControlPanel controlPanel){
		this.controlPanel = controlPanel;
	}
	
	public void setBoardPanel(BoardPanel boardPanel){
		this.boardPanel = boardPanel;
	}
	
	public synchronized void playHand() {
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
			
			// Determine Winner
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
			
			// No one has won yet
			
			for (Player p : players) {
				p.setWinner(false);
			}
			
			// Player has the hand, call the playerUpdated method to set the
			// screen
			
			for (Player p : players) {
				p.getClient().playerUpdated(p);
				p.getClient().playerActed(p);
			}
			
			 
			// Wait for the user to press the continue button...
			refresh();
			
			int x = 0;
			//while(controlPanel.getContinuePressed() == false){
			      //try {
					//Thread.sleep(1000);
					//x+=1;
					//System.out.println(x);
				//} catch (InterruptedException e) {
					 //TODO Auto-generated catch block
					//e.printStackTrace();
				//}  
			//}
					
			
			// While Each player has less than 5 cards, keep adding card to hand and evaluating.
			for (Player p : players) {
				for (int i = p.GetHand().getCards().size(); i < 5; i++) {
					Card c = d.drawFromDeck();
					p.GetHand().AddCardToHand(c);
				}
				p.GetHand().EvalHand();
				
			}
			
			// Determine Winner
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
			List<Card> community = new ArrayList<Card>();
			for (int i = 0; i < 5; i++) {
				Card c = d.drawFromDeck();
				community.add(c);
			}
			
			for (Player p : players) {
				List<Card> combo = new ArrayList<Card>();
				combo.addAll(p.GetHand().getCards());
				combo.addAll(community);
				List<Hand> possible = new ArrayList<Hand>();
				
				for (int v = 0; v < combo.size()-4; v++) {
					for (int w = v+1; w < combo.size()-3; w++) {
						for (int x = w + 1; x < combo.size()-2; x++) {
							for (int y = x + 1; y < combo.size()-1; y++) {
								for (int z = y + 1; z < combo.size(); z++) {
									Hand thisHand = new Hand();
									thisHand.AddCardToHand(combo.get(v));
									thisHand.AddCardToHand(combo.get(w));
									thisHand.AddCardToHand(combo.get(x));
									thisHand.AddCardToHand(combo.get(y));
									thisHand.AddCardToHand(combo.get(z));
									thisHand.EvalHand();
									possible.add(thisHand);
								}
							}
						}
					}
				}
				Collections.sort(possible, Hand.HandRank);
				p.GetHand().ScoreHand(possible.get(0).getHandStrength(), possible.get(0).getHighPairStrength(),
										possible.get(0).getLowPairStrength(), possible.get(0).getKicker());
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
			
			boardPanel.updatePanel(community);
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
			List<Card> community = new ArrayList<Card>();
			for (int i = 0; i < 5; i++) {
				Card c = d.drawFromDeck();
				community.add(c);
			}
				
			for (Player p : players) {
				List<Card> combo1 = new ArrayList<Card>();
				List<Card> combo2 = new ArrayList<Card>();
				combo1.addAll(p.GetHand().getCards());
				combo2.addAll(community);
				List<Hand> possible = new ArrayList<Hand>();
				for (int v = 0; v < combo1.size()-1; v++) {
					for (int w = v+1; w < combo1.size(); w++) {
						for (int x = 0; x < combo2.size()-2; x++) {
							for (int y = x + 1; y < combo2.size()-1; y++) {
								for (int z = y + 1; z < combo2.size(); z++) {
									Hand thisHand = new Hand();
									thisHand.AddCardToHand(combo1.get(v));
									thisHand.AddCardToHand(combo1.get(w));
									thisHand.AddCardToHand(combo2.get(x));
									thisHand.AddCardToHand(combo2.get(y));
									thisHand.AddCardToHand(combo2.get(z));
									thisHand.EvalHand();
									possible.add(thisHand);
								}
							}
						}
					}
				}
				Collections.sort(possible, Hand.HandRank);
				p.GetHand().ScoreHand(possible.get(0).getHandStrength(), possible.get(0).getHighPairStrength(),
										possible.get(0).getLowPairStrength(), possible.get(0).getKicker());
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
			
			boardPanel.updatePanel(community);
		} break;
		
		}
		
	}
	
	public void refresh() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

}
