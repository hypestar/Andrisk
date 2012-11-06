package dk.stacktrace.risk.controller;

import java.util.ArrayList;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dk.stacktrace.risk.GameSound;
import dk.stacktrace.risk.Main;
import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Player;
import dk.stacktrace.risk.game_logic.RiskGame;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.game_logic.battle.Battle;
import dk.stacktrace.risk.game_logic.enumerate.ArmyColor;
import dk.stacktrace.risk.game_logic.enumerate.GamePhase;
import dk.stacktrace.risk.gui.Army;
import dk.stacktrace.risk.gui.DoneButton;
import dk.stacktrace.risk.gui.MissionCardButton;

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
		game.dealMissions();
		gotoIntitialReinforcementPhase();
		
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
	
	public void tacticalMove(Territory source, Territory target, int numOfTroops)
	{
		source.moveTroops(numOfTroops);
		target.reinforce(numOfTroops);
		main.updateArmies();
		resetTerritorySelections();
	}

	
	public void setTargetTerritory(Territory target) {
		
		
		if(target == getSourceSelection())
		{
			return;
		}
		
		if(target != getTargetSelection())
		{
		GameSound.playSound(main, GameSound.SELECT_TERRITORY);	
		game.setTargetTerritory(target);
		}
		else
		{
			game.setTargetTerritory(null);
		}
		main.updateTerritorySelections();
	}
	
	public void setSourceTerritory(Territory source) {
		
		if(source != getSourceSelection())
		{
			game.setSourceTerritory(source);
		}
		else
		{
			game.setSourceTerritory(null);
		}
		GameSound.playSound(main, GameSound.SELECT_TERRITORY);
		main.updateTerritorySelections();
	}


	public void resetTerritorySelections() {
		game.setSourceTerritory(null);
		game.setTargetTerritory(null);
		main.updateTerritorySelections();
	}

	public Territory getSourceSelection()
	{
		return game.getSourceTerritory();
	}
	
	public Territory getTargetSelection() {
		return game.getTargetTerritory();
	}
	
	public Battle getBattle() {
		return game.getBattle();
	}


	public boolean onTouch(View v, MotionEvent event) {
		
		if (v instanceof DoneButton && event.getAction() == MotionEvent.ACTION_DOWN)
		{
			if(game.initialReinforcementPhaseIsDone() && game.reinforcementPhaseIsDone())
			{
				main.endTurnDialog();
				main.update();
			}
		}
		
		if (v instanceof MissionCardButton && event.getAction() == MotionEvent.ACTION_DOWN)
		{
			main.missionCardDialog(getActivePlayer().getMission());
		}
		
		
		if (v instanceof Army && event.getAction() == MotionEvent.ACTION_DOWN)
		{
			Army army = (Army) v;
			
			switch (game.getGamePhase()) {
			
			case INITIAL_REINFORCEMENT:
				Log.v("Controller", "INITIAL_REINFORCEMENT");
				if (army.getTerritory().getOwner().equals(getActivePlayer()))
				{
					GameSound.playSound(main, GameSound.DEPLOY_CLICK);
					army.getTerritory().reinforce(getActivePlayer().deploy());
					if(game.initialReinforcementPhaseIsDone())
					{
						gotoReinforcementPhase();
						
					}
					endTurn();
					main.update();
					return true;
				}
				
				break;
				
				
			case REINFORCEMENT:
				Log.v("Controller", "REINFORCEMENT");
				if (army.getTerritory().getOwner().equals(getActivePlayer()))
				{
					GameSound.playSound(main, GameSound.DEPLOY_CLICK);
					army.getTerritory().reinforce(getActivePlayer().deploy());
					if(game.reinforcementPhaseIsDone())
					{
						gotoAttackPhase();
					}
					main.update();
					return true;
				}
				break;
				
				
			case ATTACK:
				Log.v("Controller", "ATTACK");
				if (army.getTerritory().getOwner().equals(getActivePlayer()) && army.getTerritory().getArmySize() > 1)
				{
					setSourceTerritory(army.getTerritory());
					main.updateTerritorySelections();
					return true;
				}
				else if (!army.getTerritory().getOwner().equals(getActivePlayer()) && getSourceSelection() != null && army.getTerritory().isNeighbour(getSourceSelection()))
				{
					setTargetTerritory(army.getTerritory());

					// if the attacker only has two troops then we skip the attackSizeDialog
					if(getSourceSelection().getArmySize() == 2)
					{
						getSourceSelection().moveTroops(1);
						startBattle(1);
					}
					else
					{
						main.attackSizeDialog();
					}
					return true;
				}
				return false;

				
			case TACTICALMOVE:
				Log.v("Controller", "TACTICALMOVE");
				if (army.getTerritory().getOwner().equals(getActivePlayer()))
				{
					
					if(getSourceSelection() == null || getSourceSelection() == army.getTerritory())
					{
						setSourceTerritory(army.getTerritory());
					}
					else if (getSourceSelection().isNeighbour(army.getTerritory()))
					{
						setTargetTerritory(army.getTerritory());
						main.tacticalMoveDialog(getSourceSelection(), getTargetSelection());
					}
					return true;
				}
				break;

			default:
				break;
			}
		}
		
		
		//////////////////////////////
		
		return false;
	}

	public void retreat()
	{
		Battle battle = game.getBattle();
		battle.getAttackingTerritory().reinforce(battle.getAttackingArmy());
		resetTerritorySelections();
		main.update();
	}
	
	public void postBattle()
	{
		Battle battle = getBattle();
		Player winner = battle.getWinner();
		
		if (battle.attackerWon())
		{
			GameSound.playSound(main, GameSound.VICTORY);
			battle.getDefendingTerritory().setOwner(winner);	
			battle.getDefendingTerritory().reinforce(battle.getAttackingArmy());
			
			if(game.isOnlyOneAlive(winner))
			{
				game.setGameWinner(winner);
				resetTerritorySelections();
				Log.v("Controller.postBattle", "The winner of the game is " + winner.getName());
				
				main.winnerDialog();
				return;
			}
		}
		
		resetTerritorySelections();	
		main.update();
	}
	
	public void endTurn()
	{
		resetTerritorySelections();
		game.endTurn();
		main.update();
		
		if(game.getGamePhase() != GamePhase.INITIAL_REINFORCEMENT)
		{
			GameSound.playSound(main, GameSound.REINFORCEMENT_PHASE);	
		}

		if(game.weHaveAWinner())
		{
			Log.v("Winner by mission", "Winner is " + getActivePlayer().getName());
			Log.v(getActivePlayer().getName() + "'s mission", "" + getActivePlayer().getMission().getMissionDescription());
			main.winnerDialog();
		}
		
		Log.v("MISSION CARD", getActivePlayer().getName() + "'s mission : " + getActivePlayer().getMission().getMissionDescription());
	}
	
	private void gotoIntitialReinforcementPhase()
	{
		game.setInitialReinforcementForAllPlayers();
		game.setGamePhase(GamePhase.INITIAL_REINFORCEMENT);
		
	}
	
	public void gotoTacticalMovePhase()
	{
		if(game.reinforcementPhaseIsDone())
		{
			GameSound.playSound(main, GameSound.TACTICAL_MOVE_PHASE);
			resetTerritorySelections();
			game.setGamePhase(GamePhase.TACTICALMOVE);
		}
	}
	
	private void gotoReinforcementPhase()
	{	
		GameSound.playSound(main, GameSound.REINFORCEMENT_PHASE);
		resetTerritorySelections();
		game.setGamePhase(GamePhase.REINFORCEMENT);
	}

	private void gotoAttackPhase()
	{
		GameSound.playSound(main, GameSound.ATTACK_PHASE);
		game.setGamePhase(GamePhase.ATTACK);
	}
	
	public Player getGameWinner()
	{
		return game.getGameWinner();
	}
	
	public int getNumberOfTerritoriesOwnedBy(Player player)
	{
		return board.getNumberOfTerritoriesOwnedBy(player);
	}



	public void startBattle(int attackingArmy)
	{
		game.createBattle(attackingArmy);
		main.attackDialog();
	}
}
