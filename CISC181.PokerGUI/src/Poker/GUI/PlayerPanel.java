package Poker.GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import pokerAction.Action;
import pokerBase.Card;
import pokerBase.Hand;
import pokerBase.Player;
import pokerBase.Rule;
import pokerBase.Table;
import pokerEnums.eHandStrength;
import pokerPlay.Client;

public class PlayerPanel extends JPanel implements ActionListener {

	/** Filled dealer button image when player is dealer. */
	// private static final Icon BUTTON_PRESENT_ICON =
	// ResourceManager.getIcon("/images/button_present.png");
	//
	// /** Empty dealer button image when player is not dealer. */
	// private static final Icon BUTTON_ABSENT_ICON =
	// ResourceManager.getIcon("/images/button_absent.png");
	//

	public JButton click;
	public JDialog inputbox;
	public JLabel lblname;
	public JLabel lblcode;
	public JLabel lblacc;
	public JTextField txtname;
	public JTextField txtcode;
	public JTextField txtacc;

	private static final Icon CARD_PLACEHOLDER_ICON = ResourceManager
			.getIcon("/img/card_placeholder.png");

	private static final Icon CARD_BACK_ICON = ResourceManager
			.getIcon("/img/card_back.png");

	/** The border. */
	private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);

	/** The label with the player's name. */
	private JLabel nameLabel;

	private JLabel lblWinner;
	
	private JLabel lblHand;

	private JLabel[] cardLabels;
	
	private JButton[] removeButtons;
	
	

//	private JButton btnDiscard = new JButton();
//	private JButton btnJoin = new JButton();
//	private JButton btnLeave = new JButton();

	/** The board panel. */
	private BoardPanel boardPanel;

	/** The control panel. */
	private ControlPanel controlPanel;

	private Action selectedAction;

	private final Object monitor = new Object();

	/**
	 * Create the panel.
	 */
	public PlayerPanel(Table tbl, Rule rle, final Player plr) {

		setBorder(BORDER);
		setBackground(Color.yellow);
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		nameLabel = new MyLabel();
		
		lblHand = new MyLabel();

		lblWinner = new MyLabel();
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.insets = new Insets(1, 1, 1, 1);
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		nameLabel.setText(plr.GetPlayerName());
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(lblHand, gc);

		gc.gridx = 2;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(lblWinner, gc);
		
		cardLabels = new JLabel[7];
		removeButtons = new JButton[7];

		for (int i = 0; i < 7; i++) {
			cardLabels[i] = new JLabel(ResourceManager.getIcon("/img/card_placeholder.png"));
			if (i>4){
				cardLabels[i].setVisible(false);
			}
			removeButtons[i] = new JButton();
			removeButtons[i].setSize(70, 30);
			removeButtons[i].setText("Toss");
			gc.gridx = i;
			gc.gridy = 3;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weightx = 1.0;
			gc.weighty = 1.0;
			gc.anchor = GridBagConstraints.CENTER;
			gc.fill = GridBagConstraints.NONE;
			add(cardLabels[i], gc);
			gc.gridy = 2;
			add(removeButtons[i], gc);
			removeButtons[i].setVisible(false);
			
		}
		
		// Had to do this without loop because otherwise I get the incomprehensible error
		// "Cannot refer to the non-final local variable i defined in an enclosing scope" 
		// when inside the addActionListener...-_-
		
		// Does not work for unknown reason
		//for (int i=0; i<7; i++){
			//removeButtons[i].addActionListener(	
				//new ActionListener(){
	        		
					//public void actionPerformed(ActionEvent e) {
						//plr.GetHand().RemoveCardFromHand(i);
						//plr.getClient().playerUpdated(plr);
						//plr.getClient().playerActed(plr);
	        		//}
	        	//}
				//);
		//}
		
		removeButtons[0].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
	        			plr.GetHand().RemoveCardFromHand(0);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
					}
	        	}
	    );
		
		removeButtons[1].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
						plr.GetHand().RemoveCardFromHand(1);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
	        		}
	        	}
	    );
		
		removeButtons[2].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
						plr.GetHand().RemoveCardFromHand(2);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
	        		}
	        	}
	    );
		
		removeButtons[3].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
						plr.GetHand().RemoveCardFromHand(3);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
	        		}
	        	}
	    );
		
		removeButtons[4].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
						plr.GetHand().RemoveCardFromHand(4);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
	        		}
	        	}
	    );
		
		removeButtons[5].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
						plr.GetHand().RemoveCardFromHand(5);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
	        		}
	        	}
	    );
		
		removeButtons[6].addActionListener(	
				new ActionListener(){
	        		
					public void actionPerformed(ActionEvent e) {
						plr.GetHand().RemoveCardFromHand(6);
	        			plr.getClient().playerUpdated(plr);
	    				plr.getClient().playerActed(plr);
	        		}
	        	}
	    );
		
//		gc.gridx = 0;
//		gc.gridy = 4;
//		gc.gridwidth = 1;
//		gc.gridheight = 1;
//		gc.weightx = 1.0;
//		gc.weighty = 1.0;
//		gc.anchor = GridBagConstraints.CENTER;
//		gc.fill = GridBagConstraints.NONE;
//		btnJoin = createActionButton(Action.SIT);
//		add(btnJoin, gc);
//
//		gc.gridx = 1;
//		gc.gridy = 4;
//		gc.gridwidth = 1;
//		gc.gridheight = 1;
//		gc.weightx = 1.0;
//		gc.weighty = 1.0;
//		gc.anchor = GridBagConstraints.CENTER;
//		gc.fill = GridBagConstraints.NONE;
//
//		btnLeave = createActionButton(Action.LEAVE);
//		add(btnLeave, gc);
//
//		gc.gridx = 2;
//		gc.gridy = 4;
//		gc.gridwidth = 1;
//		gc.gridheight = 1;
//		gc.weightx = 1.0;
//		gc.weighty = 1.0;
//		gc.anchor = GridBagConstraints.CENTER;
//		gc.fill = GridBagConstraints.NONE;
//		btnDiscard = createActionButton(Action.DISCARD);
//		add(btnDiscard, gc);

	}

	private static class MyLabel extends JLabel {

		/**
		 * Constructor.
		 */
		public MyLabel() {
			setBorder(UIConstants.LABEL_BORDER);
			setForeground(UIConstants.TEXT_COLOR);
			setHorizontalAlignment(JLabel.HORIZONTAL);
			setText(" ");
		}

	} // MyLabel

	private JButton createActionButton(Action action) {
		String label = action.getName();
		JButton button = new JButton(label);
		button.setMnemonic(label.charAt(0));
		button.setSize(70, 30);
		button.addActionListener(this);
		return button;
	}

	public  void update(Player p)
	{		
		ArrayList<Card> cardsinHand = p.GetHand().getCards();
		int HandSize = p.GetHand().getCards().size();
				
			int i=0;			
			for (Card c: cardsinHand)
			{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cardLabels[i].setIcon(ResourceManager.getIcon("/img/" + c.getCardImg())); 
				i++;
			}
			
			for (int x = HandSize; x < cardLabels.length; x++){
				cardLabels[x].setIcon(ResourceManager.getIcon("/img/card_placeholder.png"));
			}
		 Hand h = p.GetHand();
		
	     for(eHandStrength eHS : eHandStrength.values()) { 
	    	 if (eHS.getHandStrength() == h.getHandStrength())
	    	 {
	    		 lblHand.setText(eHS.toString());
	    	 }
	      }
	     if (p.getWinner()){
	    	 lblWinner.setText("Winner");
	     }
	     else{
	    	 lblWinner.setText(" ");
	     }
	}
	
	public JButton[] getremoveButtons(){
		return removeButtons;
	}
	
	public JLabel[] getCardLabels(){
		return cardLabels;
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

/*	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnLeave) {
			selectedAction = Action.LEAVE;
		} else if (source == btnDiscard) {
			selectedAction = Action.DISCARD;
		} else if (source == btnJoin) {
			selectedAction = Action.SIT;

			JButton ok = new JButton("Sit");
			ok.setBounds(140, 50, 90, 25);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inputbox.setVisible(false);
					nameLabel.setText(txtname.getText()); 
					
					Player p = new Player(txtname.getText());
				}
			}
			);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBounds(240, 50, 90, 25);
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inputbox.setVisible(false);
				}
			}
			);			

			inputbox = new JDialog();

			inputbox.setTitle("Player Add");
			inputbox.getContentPane().setLayout(null);
			inputbox.setBounds(150, 150, 400, 150);

			lblname = new JLabel("Player Name");
			lblname.setBounds(20, 20, 100, 20);
			inputbox.getContentPane().add(lblname);

			txtname = new JTextField("");
			txtname.setBounds(140, 20, 190, 20);

			inputbox.getContentPane().add(txtname);
			inputbox.getContentPane().add(ok);
			inputbox.getContentPane().add(btnCancel);
			inputbox.show();

		}
		synchronized (monitor) {
			monitor.notifyAll();
		}

	}*/


}
