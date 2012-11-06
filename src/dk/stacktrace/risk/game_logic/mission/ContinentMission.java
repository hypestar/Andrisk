package dk.stacktrace.risk.game_logic.mission;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Player;
import dk.stacktrace.risk.game_logic.continents.Continent;

public class ContinentMission implements Mission 
{
	private boolean plusOne;
	private Continent ContinentOne, ContinentTwo;
	private Board board;
	private Player missionOwner;
	private int numOfViews;

	public ContinentMission(Continent continentOne, Continent continentTwo, boolean plusOne, Board board)
	{
		this.ContinentOne = continentOne;
		this.ContinentTwo = continentTwo;
		this.plusOne = plusOne;
		this.board = board;
		missionOwner = null;
		numOfViews = 0;
	}
	

	public boolean missionAccomplished()
	{
		
		if(missionOwner == null)
		{
			return false;
		}
		
		if (plusOne)
		{
			return ContinentOne.ownedBy(missionOwner) && ContinentTwo.ownedBy(missionOwner) && board.getNumberOfContinentsOwnedBy(missionOwner) > 2;
		}
		else
		{
			return ContinentOne.ownedBy(missionOwner) && ContinentTwo.ownedBy(missionOwner);
		} 
	}


	public String getMissionDescription()
	{
		String mission = "Conquer " + ContinentOne.getName() + " and " + ContinentTwo.getName() + ".";
		String missionPlusOne = "Conquer " + ContinentOne.getName() + ", " + ContinentTwo.getName() + ", and one other continent.";
		
		return plusOne ? missionPlusOne : mission;
	}


	public void setMissionOwner(Player player)
	{
		if((missionOwner == null))
		missionOwner = player;
	}


	public Player getMissionOwner()
	{
		return missionOwner;
	}


	public String getMissionId()
	{
		return "continent_" + ContinentOne.getId() + "_" + ContinentTwo.getId();
	}

	public void registerMissionView()
	{
		++numOfViews;
	}


	public int getNumberOfViews()
	{
		return numOfViews;
	}
}
