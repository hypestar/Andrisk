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
	
	
	public void create2PlayerTestGame()
	{
		Player p1 = new Player("Hypestar", ArmyColor.PURPLE);
		Player p2 = new Player("Napoleon", ArmyColor.BLUE);
		Player p3 = new Player("Adolf", ArmyColor.GREEN);
		ArrayList<Player> players = new ArrayList<Player>();

		players.add(p1);
		players.add(p2);
		players.add(p3);
		game = new RiskGame(players);

		board = game.getBoard();
		Log.v("2p testgame", "6");
	}
	
	public void dealTerritories()
	{
		game.dealTerritories();
	}
}
