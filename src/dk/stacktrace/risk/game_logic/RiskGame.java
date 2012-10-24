package dk.stacktrace.risk.game_logic;

import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.enumerate.GamePhase;

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
	
	public void setInitialReinforcementForAllPlayers() {
		int initialReinforcement = 0;
		
		switch (players.size()) {
		case 2:
			initialReinforcement = 40;
			break;
		case 3:
			initialReinforcement = 35;
			break;
		case 4:
			initialReinforcement = 30;
			break;
		case 5:
			initialReinforcement = 25;
			break;
		case 6:
			initialReinforcement = 20;
			break;
		}
		
		for (Player player : players) 
		{
			player.reinforcement(initialReinforcement);
		}
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
				// the active player is not the last in the array
				if (i != players.size() -1)
				{
					return players.get(i+1);
				}
				// the active player is the last in the array.
				else
				{
					return players.get(0);
				}
			}
		}
		// Error
		return new Player();
	}
	
	public void endTurn()
	{
		activePlayer = nextPlayer();
		if(gamePhase != GamePhase.INITIAL_REINFORCEMENT)
		{
			activePlayer.reinforcement(board.calcReinforcementBonus(activePlayer));
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	public GamePhase getGamePhase() {
		return gamePhase;
	}
	
	public void setGamePhase(GamePhase gamePhase) {
		this.gamePhase = gamePhase;
	}
	
	public boolean deploymentPhaseIsDone()
	{
		for (Player player : players) 
		{
			if (player.hasTroopsToDeploy())
			{
				return false;
			}
		}
		return true;
	}
	

}
