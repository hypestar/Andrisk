package dk.stacktrace.risk.game_logic;

import java.util.ArrayList;

import dk.stacktrace.risk.game_logic.enumerate.ArmyColor;
import dk.stacktrace.risk.game_logic.mission.Mission;


public class Player {
	private String name;
	//private ArrayList<Territory> territories;
	private ArmyColor armyColor;
	private int reinforcement, numOfKilledEnemyTroops, numOfTroopsKilled;
	private Mission mission;


	
	
	public Player(String name, ArmyColor armyColor) {
		this.name = name;
		this.armyColor = armyColor;
		reinforcement = 0;
		numOfKilledEnemyTroops = 0;
		numOfTroopsKilled = 0;
	}
	
	public ArmyColor getArmyColor() {
		return armyColor;
	}
	
	
	@Override
	public boolean equals(Object o) {

		if (!(o instanceof Player))
		{
			return false;
		}
		else if (((Player) o).getName().equals(name) )
		{
			return true;
		}
		return false;
	}

	public int deploy()
	{
		if(hasTroopsToDeploy())
		{
		--reinforcement;
		return 1;
		}
		return 0;
	}
	
	public void reinforcement(int reinforcement)
	{
		this.reinforcement += reinforcement;
	}

	public int getNumOfTroopsToDeploy() {
		return reinforcement;
	}
	
	public boolean hasTroopsToDeploy()
	{
		return reinforcement > 0;
	}

	public String getName() {
		return name;
	}
	
	public void setMission(Mission mission)
	{
		this.mission = mission;
	}
	
	public Mission getMission()
	{
		return mission;
	}
	
	public void logEnemyKill()
	{
		numOfKilledEnemyTroops += 1; 
	}
	
	public void logTroopKill()
	{
		numOfTroopsKilled += 1;
	}
	
	public int getNumOfKilledEnemyTroops()
	{
		return numOfKilledEnemyTroops;
	}
	
	public int getNumOfTroopsKilled()
	{
		return numOfTroopsKilled;
	}
}

