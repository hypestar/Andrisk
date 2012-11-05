package dk.stacktrace.risk.game_logic.mission;

import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

import dk.stacktrace.risk.game_logic.Player;
import dk.stacktrace.risk.game_logic.RiskGame;

public class KillPlayerMission implements Mission
{
	private static ArrayList<Player> targets = new ArrayList<Player>();
	private RiskGame game;
	private Player missionOwner, target;
	
	public KillPlayerMission(RiskGame game)
	{
		missionOwner = null;
		target = null;
		this.game = game;
		//KillPlayerMission.targets = (KillPlayerMission.targets != null) ? new ArrayList<Player>() : null; 
	}

	public boolean missionAccomplished()
	{
		
		if(game.isDead(target))
		{
			Log.v("missionAccomplished", "missionOwner " + missionOwner.getName());
			return true;
		}
		else
		{
			return false;	
		}
		
	}

	public String getMissionDescription()
	{
		return (missionOwner == null) ? "Target has not been set" : "Destroy all " + target.getName() + "'s troops.";
	}

	// sets the owner of the mission and sets target of the mission
	public void setMissionOwner(Player player)
	{
		ArrayList<Player> players = (ArrayList<Player>) game.getPlayers().clone();
		Collections.shuffle(players);

		Log.v("setMissionOwner", "targets " + KillPlayerMission.targets);
		if((missionOwner == null)) 
		{
			missionOwner = player;
			for (Player p : players)
			{
				if(!missionOwner.equals(p) && !KillPlayerMission.targets.contains(p))
				{
					target = p;
					KillPlayerMission.targets.add(p);

				}
			}
			Log.v("setMissionOwner", "target set to " + target);
		}
	}

	public Player getMissionOwner()
	{
		return missionOwner;
	}

	public String getMissionId()
	{
		return "kill_" + target.getArmyColor().toString().toLowerCase();
	}

}
