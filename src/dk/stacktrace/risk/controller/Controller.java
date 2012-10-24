package dk.stacktrace.risk.controller;

import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dk.stacktrace.risk.Main;
import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Player;
import dk.stacktrace.risk.game_logic.RiskGame;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.game_logic.enumerate.ArmyColor;
import dk.stacktrace.risk.game_logic.enumerate.GamePhase;
import dk.stacktrace.risk.gui.Army;

public class Controller implements OnTouchListener{
	
	private RiskGame game;
	private Board board;
	private Main main;

	public Controller(Main main) {
		this.main = main;
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
		//players.add(p4);
		//players.add(p5);
		//players.add(p6);
		
		game = new RiskGame(players);
		board = game.getBoard();
		
		dealTerritories();
		game.setInitialReinforcementForAllPlayers();
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



	public boolean onTouch(View v, MotionEvent event) {
		
		// INITIAL DEPLOYMENT PHASE
		if (v instanceof Army && game.getGamePhase() == GamePhase.INITIAL_REINFORCEMENT)
		{
			Army army = (Army) v; 
			if (army.getTerritory().getOwner().equals(getActivePlayer()))
			{
				if(game.deploymentPhaseIsDone())
				{
					game.setGamePhase(GamePhase.REINFORCEMENT);
					Log.v("ON Touch", "Initial Deployment phase is done");
				}
				
				army.getTerritory().reinforce(getActivePlayer().deploy());
				//main.select(army.getTerritory());
				//main.select(army.getTerritory().getNeighbours());
				//main.toggleHighlight(army.getTerritory().getNeighbours());
				//army.reinforce(army.getTerritory().getOwner().deploy());
				endTurn();
				main.update();

				return true;
			}
		}
		//////////////////////////////
		
		return false;
	}
	
	public void endTurn()
	{
		game.endTurn();
	}
	
}
