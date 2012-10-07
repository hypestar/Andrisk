package dk.stacktrace.risk.game_logic;

import java.util.ArrayList;


public class Player {
	private String name;
	private ArrayList<Territory> territories;
	private ArmyColor armyColor;

	public Player() {
		this("New Player", ArmyColor.BLUE);
	}
	
	public Player(String name, ArmyColor armyColor) {
		this.name = name;
		this.armyColor = armyColor;
		
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



	private String getName() {
		return name;
	}
}

