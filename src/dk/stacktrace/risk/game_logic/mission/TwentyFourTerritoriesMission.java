package dk.stacktrace.risk.game_logic.mission;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Player;

public class TwentyFourTerritoriesMission implements Mission
{

	private Board board;
	private Player missionOwner;
	
	public TwentyFourTerritoriesMission(Board board)
	{
		this.board = board;
	}

	public boolean missionAccomplished()
	{
		return (missionOwner != null) ?	board.getTerritoriesOwnedBy(missionOwner).size() > 23 : false;	
	}

	public String getMissionDescription()
	{
		
		return "Capture 24 territories.";
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
		return "24";
	}

}
