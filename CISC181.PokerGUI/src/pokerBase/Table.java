package pokerBase;

import java.util.ArrayList;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
public class Table {
	@XmlElement
	private UUID TableID;
	@XmlElement
	private ArrayList<GamePlay> TableGame = new ArrayList<GamePlay>();
	@XmlElement (name = "TablePlayerID")
	private ArrayList<Player> TablePlayers = new ArrayList<Player>();
	
	
	public Table()
	{
		this.TableID = UUID.randomUUID();
	}
	
	public UUID GetTableID()
	{
		return this.TableID;
	}
	
	public void AddGame(GamePlay g)
	{
		TableGame.add(g);
	}
	
	public void RemoveGame(GamePlay g)
	{
		TableGame.remove(g);
	}
	
	public ArrayList<GamePlay> GetGames()
	{
		return TableGame;
	}
	
	public void AddTablePlayer(Player p)
	{
		TablePlayers.add(p);
	}
	
	public void RemoveTablePlayer(Player p)
	{
		TablePlayers.remove(p);
	}
	
	public ArrayList<Player> GetTablePlayers()
	{
		return TablePlayers;
	}
	
    /**
     * Main game loop.
     */
//    public void run() {
//        for (Player player : TablePlayers) {
//            player.getClient().joinedTable(TablePlayers);
//        }
//
//        while (true) {
//            int noOfActivePlayers = 0;
//            for (Player player : TablePlayers) {
//
//                    noOfActivePlayers++;
//            }
//            if (noOfActivePlayers > 1) {
//                playHand();
//            } else {
//                break;
//            }
//        }
//        
//        // Game over.
//        board.clear();
//        notifyBoardUpdated();
//        for (Player player : TablePlayers) {
//            player.resetHand();
//        }
//        notifyPlayersUpdated(false);
//        notifyMessage("Game over.");
    
    
    
}
