package dk.stacktrace.risk.controller;

import java.util.ArrayList;

import android.util.Log;

import dk.stacktrace.risk.game_logic.ArmyColor;
import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Player;
import dk.stacktrace.risk.game_logic.RiskGame;
import dk.stacktrace.risk.game_logic.Territory;

public class Controller {
	
	private RiskGame game;
	private Board board;

	public Controller() {
		
		
	}

	
	
	public ArrayList<Territory> getAllTerritories()
	{
		return board.getAllTerritories();
	}
	
	
	public void createTestGame()
	{
		Player p1 = new Player("Hypestar", ArmyColor.PURPLE);
		Player p2 = new Player("Napoleon", ArmyColor.BLUE);
		Player p3 = new Player("Adolf", ArmyColor.GREEN);
		Player p4 = new Player("Patton", ArmyColor.YELLOW);
		Player p5 = new Player("Ghenkis Khan", ArmyColor.ORANGE);
		Player p6 = new Player("Bush", ArmyColor.CYAN);
		
		ArrayList<Player> players = new ArrayList<Player>();

		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);
		game = new RiskGame(players);

		board = game.getBoard();
		Log.v("2p testgame", "6");
	}
	
	public void dealTerritories()
	{
		game.dealTerritories();
	}
	
	public int calcReinforcementBonus(Player player)
	{
		return board.calcReinforcementBonus(player);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Player getActivePlayer()
	{
		return game.getActivePlayer();
	}
	
}
