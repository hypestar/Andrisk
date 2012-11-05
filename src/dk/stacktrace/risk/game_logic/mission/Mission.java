package dk.stacktrace.risk.game_logic.mission;

import dk.stacktrace.risk.game_logic.Player;

public interface Mission
{
	public boolean missionAccomplished();
	public String getMissionDescription();
	public void setMissionOwner(Player player);
	public Player getMissionOwner();
	public String getMissionId();
}
