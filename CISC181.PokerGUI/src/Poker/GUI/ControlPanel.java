package Poker.GUI;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pokerAction.Action.*;
import pokerAction.Action;


//import pokerAction.Actions.*;
 
public class ControlPanel extends JPanel  implements ActionListener {

    /** The Check button. */
	private final JButton btnDeal;
	private final JButton btnStart;
	private final JButton btnEnd;
	private final JButton btnContinue;
	private boolean ContinuePressed = false;
	private final JButton btnLeave;
	private final JButton btnSit;
	private final JButton btnDraw;
	private final JButton btnDiscard;
	private final JButton btnFold;
	
	
	
    private final Object monitor = new Object();
    
	private Action selectedAction;
    
	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		
        setBackground(Color.blue);
        btnDeal = createActionButton(Action.DEAL);
        btnStart = createActionButton(Action.START);
        this.add(btnStart);
        btnEnd = createActionButton(Action.END);
        btnEnd.addActionListener(
            new ActionListener(){
            	public void actionPerformed(ActionEvent e) {
            		System.exit(0);	
            	}
            }
        );
        this.add(btnEnd);
        btnContinue = createActionButton(Action.CONTINUE);
        btnContinue.addActionListener(
				new ActionListener(){
	            	public void actionPerformed(ActionEvent e) {
	            		ContinuePressed = true;
	            	}
	            }
	        );
		
        this.add(btnContinue);
        btnContinue.setVisible(false);
        btnLeave= createActionButton(Action.LEAVE);
        btnSit= createActionButton(Action.SIT);
        btnDraw= createActionButton(Action.DRAW);
        btnDiscard= createActionButton(Action.DISCARD);
        btnFold= createActionButton(Action.FOLD);

	}
	
    /**
     * Waits for the user to click the Continue button.
     */
	
	public JButton getbtnStart(){
		return btnStart;
	}
	
	public JButton getbtnContinue(){
		return btnContinue;
	}
	
	public boolean getContinuePressed(){
		return ContinuePressed;
	}
	
	public void waitForUserInput() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                removeAll();
                add(btnContinue);
                repaint();
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnDeal) {
            selectedAction = Action.DEAL;
        } else if (source == btnStart) {
            selectedAction = Action.START;
        } else if (source == btnEnd) {
            selectedAction = Action.END;
        }
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
    
    
    public Action getUserInput(final Set<Action> allowedActions) {
    	System.out.println("Here");
        selectedAction = null;
        while (selectedAction == null) {
            // Show the buttons for the allowed actions.
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    removeAll();
                    if (allowedActions.contains(Action.CONTINUE)) {
                        add(btnContinue);
                    } else {
                        if (allowedActions.contains(Action.LEAVE)) {
                            add(btnLeave);
                        }
                        if (allowedActions.contains(Action.SIT)) {
                            add(btnSit);
                        }
                        if (allowedActions.contains(Action.DRAW)) {
                            add(btnDraw);
                        }
                        if (allowedActions.contains(Action.DISCARD)) {
                            add(btnDiscard);
                        }                        
                        if (allowedActions.contains(Action.FOLD)) {
                            add(btnFold);
                        }
                    }
                    repaint();
                }
            });
            
            // Wait for the user to select an action.
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    // Ignore.
                }
            }
            

        }
        
        return selectedAction;
    }
    
    
    
    
    
    
    
    private JButton createActionButton(Action action) {
        String label = action.getName();
        JButton button = new JButton(label);
        button.setMnemonic(label.charAt(0));
        button.setSize(100, 30);
        return button;
    }

}
