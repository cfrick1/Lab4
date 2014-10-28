package Poker.GUI;

// Screen Resolution minimum to see whole window: 1350 x 700

import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import pokerAction.Action;
import pokerBase.Card;
import pokerBase.Player;
import pokerBase.Rule;
import pokerBase.Table;
import pokerEnums.eGame;
import pokerPlay.Client;

public class Main extends JFrame implements Client {

	/** The table. */
	private Table tbl;

	private Map<String, Player> players;
	
	/** The GridBagConstraints. */
	private GridBagConstraints gc;

	/** The board panel. */
	private BoardPanel boardPanel;

	/** The control panel. */
	private ControlPanel controlPanel;

	/** The player panels. */
	private Map<String, PlayerPanel> playerPanels;

	private PlayGame pGame;
	
	private Rule rle;
	
	private eGame RuleType;
	// Needed to add this for the Start button to know which game to start, could not think of another way
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Main window = new Main();
		
	}

	/**
	 * Create the application.
	 */
	public Main() {
		
		initializeAll();
		setSize(1350, 700);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeAll() {
		
		
		tbl = new Table();
		RuleType = eGame.FiveStud;
		rle = new Rule(eGame.FiveStud);
		pGame = new PlayGame(eGame.FiveStud);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JRadioButtonMenuItem rdbtnmntmFiveStud = new JRadioButtonMenuItem("5 Card Stud");
		rdbtnmntmFiveStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.FiveStud;
				rle = new Rule(eGame.FiveStud);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(false);	
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						p.getremoveButtons()[i].setVisible(false);
						if(i>4){
							p.getCardLabels()[i].setVisible(false);
						}
						else{
							p.getCardLabels()[i].setVisible(true);
						}
					}
				}
				controlPanel.getbtnContinue().setVisible(false);
				pGame = new PlayGame(eGame.FiveStud);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
			}
		});
		mnGame.add(rdbtnmntmFiveStud);
		
		JRadioButtonMenuItem rdbtnmntmCardJoker = new JRadioButtonMenuItem("5 Card Joker");
		rdbtnmntmCardJoker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.FiveStudTwoJoker;
				rle = new Rule(eGame.FiveStudTwoJoker);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(false);
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						p.getremoveButtons()[i].setVisible(false);
						if(i>4){
							p.getCardLabels()[i].setVisible(false);
						}
						else{
							p.getCardLabels()[i].setVisible(true);
						}
					}
				}
				controlPanel.getbtnContinue().setVisible(false);
				pGame = new PlayGame(eGame.FiveStudTwoJoker);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
			}
		});
		mnGame.add(rdbtnmntmCardJoker);
		
		JRadioButtonMenuItem rdbtnmntmDeucesWild = new JRadioButtonMenuItem("Deuces Wild");
		rdbtnmntmDeucesWild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.DeucesWild;
				rle = new Rule(eGame.DeucesWild);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(false);
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						p.getremoveButtons()[i].setVisible(false);
						if(i>4){
							p.getCardLabels()[i].setVisible(false);
						}
						else{
							p.getCardLabels()[i].setVisible(true);
						}
					}
				}
				controlPanel.getbtnContinue().setVisible(false);
				pGame = new PlayGame(eGame.DeucesWild);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
			}
		});
		mnGame.add(rdbtnmntmDeucesWild);
		
		JRadioButtonMenuItem rdbtnmntm5CardDraw = new JRadioButtonMenuItem("5 Card Draw");
		rdbtnmntm5CardDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.FiveDraw;
				rle = new Rule(eGame.FiveDraw);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(false);
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						if (i<5){
							p.getremoveButtons()[i].setVisible(true);
						}
						
						if(i>4){
							p.getCardLabels()[i].setVisible(false);
							p.getremoveButtons()[i].setVisible(false);
						}
						else{
							p.getCardLabels()[i].setVisible(true);
						}
					}
				}
				controlPanel.getbtnContinue().setVisible(true);
				pGame = new PlayGame(eGame.FiveDraw);
				pGame.setControlPanel(controlPanel);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
				
			}
		});
		mnGame.add(rdbtnmntm5CardDraw);
		
		JRadioButtonMenuItem rdbtnmntm7CardDraw = new JRadioButtonMenuItem("7 Card Draw");
		rdbtnmntm7CardDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.SevenDraw;
				rle = new Rule(eGame.SevenDraw);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(false);
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						p.getCardLabels()[i].setVisible(true);
						p.getremoveButtons()[i].setVisible(true);	
					}
				}
				pGame = new PlayGame(eGame.SevenDraw);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
			}
		});
		mnGame.add(rdbtnmntm7CardDraw);
		
		JRadioButtonMenuItem rdbtnmntmHoldEm = new JRadioButtonMenuItem("Texas Hold 'em");
		rdbtnmntmHoldEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.TexasHoldEm;
				rle = new Rule(eGame.TexasHoldEm);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(true);
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						p.getremoveButtons()[i].setVisible(false);
						if(i>1){
							p.getCardLabels()[i].setVisible(false);
						}
						else{
							p.getCardLabels()[i].setVisible(true);
						}
					}
				}
				controlPanel.getbtnContinue().setVisible(false);
				pGame = new PlayGame(eGame.TexasHoldEm);
				pGame.setBoardPanel(boardPanel);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
			}
		});
		mnGame.add(rdbtnmntmHoldEm);
		
		JRadioButtonMenuItem rdbtnmntmOmaha = new JRadioButtonMenuItem("Omaha");
		rdbtnmntmOmaha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RuleType = eGame.Omaha;
				rle = new Rule(eGame.Omaha);
				for (int i = 0; i < 5; i++)
					boardPanel.getcardLabels()[i].setVisible(true);
				for (PlayerPanel p: playerPanels.values()){
					for (int i = 0; i < 7; i++){
						p.getremoveButtons()[i].setVisible(false);
						if(i>3){
							p.getCardLabels()[i].setVisible(false);
						}
						else{
							p.getCardLabels()[i].setVisible(true);
						}
					}
				}
				controlPanel.getbtnContinue().setVisible(false);
				pGame = new PlayGame(eGame.Omaha);
				pGame.setBoardPanel(boardPanel);
				for (Player p: players.values()){
					pGame.AddPlayer(p);
				}
				pGame.run();
			}
		});
		mnGame.add(rdbtnmntmOmaha);
	
		ButtonGroup menuGroup = new ButtonGroup();
		menuGroup.add(rdbtnmntmFiveStud);
		menuGroup.add(rdbtnmntmCardJoker);
		menuGroup.add(rdbtnmntmDeucesWild);
		menuGroup.add(rdbtnmntm5CardDraw);
		menuGroup.add(rdbtnmntm7CardDraw);
		menuGroup.add(rdbtnmntmHoldEm);
		menuGroup.add(rdbtnmntmOmaha);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(UIConstants.TABLE_COLOR);
		setLayout(new GridBagLayout());
		
		gc = new GridBagConstraints();
		controlPanel = new ControlPanel();
		controlPanel.getbtnStart().addActionListener(
	        	new ActionListener(){
	        		public void actionPerformed(ActionEvent e) {
	        			rle = new Rule(RuleType);
	    				pGame = new PlayGame(RuleType);
	    				for (Player p: players.values()){
	    					pGame.AddPlayer(p);
	    				}
	    				pGame.run();
	        		}
	        	}
	    );
		
		
		boardPanel = new BoardPanel(controlPanel, rle);
		addComponent(boardPanel, 1, 1, 1, 1);
		
		makePlayers();

		// Show the frame.
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		pGame.run();	
	}
	
	private void makePlayers(){
		players = new LinkedHashMap<String, Player>();
		
		Player p1 = new Player("Bert", this);
		players.put(p1.GetPlayerID().toString(), p1);
		
		Player p2 = new Player("Joe", this);
		players.put(p2.GetPlayerID().toString(), p2);
		
		Player p3 = new Player("Jim", this);
		players.put(p3.GetPlayerID().toString(), p3);
		
		Player p4 = new Player("Bob", this);
		players.put(p4.GetPlayerID().toString(), p4);
		
        for (Player player : players.values()) {
        	pGame.AddPlayer(player);
        }
        
		playerPanels = new HashMap<String, PlayerPanel>();
		
		int i = 0;
		for (Player player : players.values()) {
			PlayerPanel panel = new PlayerPanel(tbl, rle, player);
			playerPanels.put(player.GetPlayerName(), panel);
			switch (i++) {
			case 0:
				// North position.
				addComponent(panel, 1, 0, 1, 1);
				break;
			case 1:
				// East position.
				addComponent(panel, 2, 1, 1, 1);
				break;
			case 2:
				// South position.
				addComponent(panel, 1, 2, 1, 1);
				break;
			case 3:
				// West position.
				addComponent(panel, 0, 1, 1, 1);
				break;
			default:
				// Do nothing.
			}
		}
	}
	
	private void addComponent(Component component, int x, int y, int width,
			int height) {
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.NONE;
		gc.weightx = 0.0;
		gc.weighty = 0.0;
		getContentPane().add(component, gc);
	}

	@Override
	public void messageReceived(String message) {
        boardPanel.setMessage(message);
        boardPanel.waitForUserInput();
		
	}

	@Override
	public void joinedTable(List<Player> players) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handStarted(Player dealer) {
		// TODO Auto-generated method stub
			
	}

	@Override
	public void actorRotated(Player actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerUpdated(Player player) {
		System.out.println("PlayerUpdated in Main");
        PlayerPanel playerPanel = playerPanels.get(player.GetPlayerName());
        if (playerPanel != null) {
            playerPanel.update(player);
        }
		
	}

	@Override
	public void boardUpdated(List<Card> cards) {
		System.out.println("BoardUpdated in Main");
		if (boardPanel != null){
			boardPanel.updatePanel(cards);
		}
	}

	@Override
	public void playerActed(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action act(Set<Action> allowedActions) {
		// TODO Auto-generated method stub
		return null;
	}
}
