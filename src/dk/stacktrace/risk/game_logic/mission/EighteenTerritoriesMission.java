package dk.stacktrace.risk.game_logic.mission;

import java.util.ArrayList;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Player;
import dk.stacktrace.risk.game_logic.Territory;

public class EighteenTerritoriesMission implements Mission
{
	private Board board;
	private Player missionOwner;
	
	public EighteenTerritoriesMission(Board board)
	{
		this.board = board;
	}

	public boolean missionAccomplished()
	{
		ArrayList<Territory> capturedTerritories = board.getTerritoriesOwnedBy(missionOwner);
		int numTerritoriesWithTwoArmies = 0;
		
		for(Territory territory : capturedTerritories)
		{
			numTerritoriesWithTwoArmies += (territory.getArmySize() >= 2) ? 1 : 0;
		}
		
		return (numTerritoriesWithTwoArmies >= 18) ? true : false;
	}

	public String getMissionDescription()
	{
		return "Capture 18 territories and occupy each with two troops.";
	}

	public void setMissionOwner(Player player)
	{
		if((missionOwner == null)) 
		{
			missionOwner = player;
		}
	}

	public Player getMissionOwner()
	{
		return missionOwner;
	}

	public String getMissionId()
	{
		return "18";
	}

}
