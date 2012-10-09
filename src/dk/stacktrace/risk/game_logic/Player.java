package dk.stacktrace.risk.game_logic;

import java.util.ArrayList;


public class Player {
	private String name;
	private ArrayList<Territory> territories;
	private ArmyColor armyColor;
	private int reinforcement;

	public Player() {
		this("New Player", ArmyColor.BLUE);
	}
	
	public Player(String name, ArmyColor armyColor) {
		this.name = name;
		this.armyColor = armyColor;
		reinforcement = 0;
		
	}
	
	public ArmyColor getArmyColor() {
		return armyColor;
	}
	
	public int getTerritoriesOwned()
	{
		return territories.size();
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

	public void setReinforcement(int reinforcement)
	{
		this.reinforcement = reinforcement;
	}

	public int getReinforcement() {
		return reinforcement;
	}
	
	public boolean hasTroopsToDeploy()
	{
		return reinforcement > 0;
	}

	public String getName() {
		return name;
	}
}

