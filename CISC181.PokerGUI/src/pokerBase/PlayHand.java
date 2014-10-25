package pokerBase;

import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pokerEnums.eGame;
import pokerEnums.eRank;
import pokerEnums.eSuit;

import java.io.StringReader;
import java.io.StringWriter;

public class PlayHand {
	public static void main(String[] args) throws Exception {

		//PlayFullHand();
		PlayFiveCardManualDeal();

	}

	private static void PlayFullHand() {
		// Regular five card stud
		Deck dStud = new Deck();
		Hand hStud = new Hand(dStud);

		// Joker five card
		Deck dJoker = new Deck(2);
		Hand hJoker = new Hand(dJoker);

		// Four Wilds five card
		ArrayList<Card> Wilds = new ArrayList<Card>();
		Wilds.add(new Card(eSuit.CLUBS, eRank.TWO,27));
		Wilds.add(new Card(eSuit.DIAMONDS, eRank.TWO,40));
		Wilds.add(new Card(eSuit.SPADES, eRank.TWO,14));
		Wilds.add(new Card(eSuit.HEARTS, eRank.TWO,1));

		Deck dWild = new Deck(0, Wilds);
		Hand hWild = new Hand(dWild);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	private static void PlayFiveCardManualDeal() throws Exception {
		
		//	Table is created
		Table tbl = new Table();
		
		//	Rule set is called (A given game has a rule set)
		Rule rle = new Rule(eGame.FiveStud);
		
		//	Game is created (tables have players, players play games, etc)
		GamePlay gme = new GamePlay(rle);
		tbl.AddGame(gme);
		
		//	Deck is created using game's ruleset
		Deck dStud = new Deck(gme.GetNumberOfJokers());
		
		//	Add the players to Table and Game, give them empty hands
		for (int i = 0; i < gme.GetMaxNumberOfPlayers(); i++) {
			Player p = new Player("Bob", null);
			p.SetPlayerNbr(i+1);			
			tbl.AddTablePlayer(p);
			gme.AddGamePlayer(p);
			p.SetHand(new Hand());			
		}
	
		//	Five Card in each hand
		for (int i = 0; i < gme.GetNumberOfCards(); i++) {
			for (Player p: gme.GetGamePlayers())
			{
				Hand h = p.GetHand();
				h.setPlayerID(p.GetPlayerID());
				h.AddCardToHand(dStud.drawFromDeck());			
				p.SetHand(h);
			}
		}
		
		//	Handle jokers
		for (Player p: gme.GetGamePlayers())
		{
			Hand h = p.GetHand();
			h.HandleJokerWilds();
			p.SetHand(h);
		}		
		
		//	Collect All Hands in a Game
		ArrayList<Hand> AllHands = new  ArrayList<Hand>();
		for (Player p: gme.GetGamePlayers())
		{
			AllHands.add(p.GetHand());
		}
		
		//	Find the best hand between players
		Collections.sort(AllHands, Hand.HandRank);
		
		System.out.println(AllHands.get(0).getPlayerID() + " is the winner!");
		System.out.println(AllHands.get(0).getHandStrength());
		
		for (Card c : AllHands.get(0).getCards())
		{
			System.out.print(c.getRank());
			System.out.print(" ");
			System.out.print(c.getSuit());
			System.out.print("      ");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
		
		
        //Write it
        JAXBContext ctx = JAXBContext.newInstance(Table.class);

        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        m.marshal(tbl, sw);
        sw.close();
        System.out.println(sw.toString());

		
        //Write it
        JAXBContext ctxDeck = JAXBContext.newInstance(Deck.class);

        Marshaller mDeck = ctxDeck.createMarshaller();
        mDeck.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter swDeck = new StringWriter();
        mDeck.marshal(dStud, swDeck);
        swDeck.close();
        System.out.println(swDeck.toString());


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void TexasHoldEm() {
		
		Table tbl = new Table();
		int iPlayers = 5;

		Deck dStud = new Deck();
		
		//	Add the players, give them empty hands
		for (int i = 0; i < iPlayers; i++) {
			Player p = new Player("Joe",null);
			p.SetPlayerNbr(i+1);			
			p.SetHand(new Hand());
			tbl.AddTablePlayer(p);
		}
		
		
		//	Two Card in each hand
		for (int i = 0; i < 2; i++) {
			for (Player p: tbl.GetTablePlayers())
			{
				Hand h = p.GetHand();
				h.setPlayerID(p.GetPlayerID());
				h.AddCardToHand(dStud.drawFromDeck());			
				p.SetHand(h);
			}
		}
		
		//	Draw Community Cards  3 cards.
		ArrayList<Card> Community = new ArrayList<Card>();
		dStud.drawFromDeck();
		Community.add(dStud.drawFromDeck());
		Community.add(dStud.drawFromDeck());
		Community.add(dStud.drawFromDeck());
		
//		Community.add(new Card(eSuit.JOKER,eRank.JOKER));
//		Community.add(new Card(eSuit.JOKER,eRank.JOKER));
//		Hand BestCommunityHand = Hand.EvalHand(Community);
		
		
		for (Player p: tbl.GetTablePlayers())
		{	Hand playerHand = new Hand();
			if (Community.size() == 3)
			{
				for (Card c : Community)
				{
					playerHand.AddCardToHand(c);
				}
				Hand pHand = p.GetHand();
				for (Card c: pHand.getCards())
				{
					playerHand.AddCardToHand(c);
				}
					
			}

		}
	}
	

}











