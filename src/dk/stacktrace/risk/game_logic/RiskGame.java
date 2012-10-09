package dk.stacktrace.risk.game_logic;

import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

public class RiskGame {
	private Board board;
	private ArrayList<Player> players;
	private Player activePlayer;
	private GamePhase gamePhase;
	
	/**
	 * @param args
	 */
	
	public RiskGame(ArrayList<Player> players) {
		this.players = players;
		board = new Board();
		activePlayer = this.players.get(0);
		gamePhase = GamePhase.INITIAL_REINFORCEMENT;
	}
	
	public void dealTerritories()
	{
		ArrayList<Territory> allTerritories = board.getAllTerritories();
		Collections.shuffle(allTerritories);
		for (Territory territory : allTerritories) {
			territory.setOwner(activePlayer);
			activePlayer = nextPlayer();
		}
	}
	
	private boolean addPlayer(Player player)
	{
		// A maximum of 6 players is allowed
		if (players.size() < 6)
		{
			players.add(player);
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	private Player nextPlayer()
	{
		for (int i = 0; i < players.size();i++)
		{
			if (players.get(i).equals(activePlayer))
			{
				if (i != players.size() -1)
				{
					return players.get(i+1);
				}
				else
				{
					return players.get(0);
				}
			}
		}
		// Error
		return new Player();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	

}
