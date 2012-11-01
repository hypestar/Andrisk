package dk.stacktrace.risk.game_logic.mission;

import dk.stacktrace.risk.game_logic.continents.Continent;

public class ContinentMission implements Mission 
{
	private boolean plusOne;
	private Continent ContinentOne, ContinentTwo;
	

	public ContinentMission(Continent continentOne, Continent continentTwo, boolean plusOne)
	{
		this.ContinentOne = continentOne;
		this.ContinentTwo = continentTwo;
		this.plusOne = plusOne;
	}
	
	

	public boolean missionAccomplished()
	{

		return false;
	}

}
