package dk.stacktrace.risk.game_logic;

import java.util.ArrayList;

import android.util.Log;
import dk.stacktrace.risk.game_logic.continents.Africa;
import dk.stacktrace.risk.game_logic.continents.Asia;
import dk.stacktrace.risk.game_logic.continents.Australia;
import dk.stacktrace.risk.game_logic.continents.Europe;
import dk.stacktrace.risk.game_logic.continents.NorthAmerica;
import dk.stacktrace.risk.game_logic.continents.SouthAmerica;


public class Board {
	private Asia asia;
	private Europe europe;
	private NorthAmerica northAmerica;
	private SouthAmerica southAmerica;
	private Australia australia;
	private Africa africa;
	
	public Board() {
 		asia = new Asia(this);
 		europe = new Europe(this);
 		northAmerica = new NorthAmerica(this);
 		southAmerica = new SouthAmerica(this);
 		australia = new Australia(this);
 		africa = new Africa(this);
 		
 		asia.setNeighbours();
 		europe.setNeighbours();
 		northAmerica.setNeighbours();
 		southAmerica.setNeighbours();
 		australia.setNeighbours();
 		africa.setNeighbours();
 		
  		
	}
	

	public int calcReinforcementBonus(Player player)
	{
		int reinformentBonus = 0;
		
		// Add territory bonus
		reinformentBonus += getNumberOfTerritoriesOwnedBy(player) / 3;
		
		// Add continent reinforcement bonus
		if (asia.ownedBy(player))
		{
			reinformentBonus += asia.getReinforcementBonus();
		}
		if (europe.ownedBy(player))
		{
			reinformentBonus += europe.getReinforcementBonus();
		}
		if (northAmerica.ownedBy(player))
		{
			reinformentBonus += northAmerica.getReinforcementBonus();
		}
		if (southAmerica.ownedBy(player))
		{
			reinformentBonus += southAmerica.getReinforcementBonus();
		}
		if (australia.ownedBy(player))
		{
			reinformentBonus += australia.getReinforcementBonus();
		}
		if (africa.ownedBy(player))
		{
			reinformentBonus += africa.getReinforcementBonus();
		}
		
		// Minimum reinforcement is 3
		if (reinformentBonus < 3)
		{
			reinformentBonus = 3;
		}
		
		return reinformentBonus;
	}
	
	public ArrayList<Territory> getTerritoriesOwnedBy(Player player)
	{
		ArrayList<Territory> playersTerritories = new ArrayList<Territory>();
		for (Territory territory : getAllTerritories()) {
			if (territory.getOwner().equals(player))
			{
				playersTerritories.add(territory);
			}
		}
		return playersTerritories;
	}
	
	public int getNumberOfTerritoriesOwnedBy(Player player)
	{
		return getTerritoriesOwnedBy(player).size();
	}
	
	public ArrayList<Territory> getAllTerritories()
	{
		ArrayList<Territory> allTerritories = new ArrayList<Territory>();
		allTerritories.addAll(asia.getTerritories()); 
		allTerritories.addAll(europe.getTerritories());
		allTerritories.addAll(northAmerica.getTerritories());
		allTerritories.addAll(southAmerica.getTerritories());
		allTerritories.addAll(australia.getTerritories());
		allTerritories.addAll(africa.getTerritories());
		return allTerritories;
	}
	
	public boolean tacticalMove(Player player, Territory from, Territory to, int numOfTroops)
	{
		// Test if the player owns both territories and that they are neighbours
		if (from.getOwner().equals(player) && to.getOwner().equals(player) && to.isNeighbour(from))
		{
			to.reinforce(from.moveTroops(numOfTroops));
			return true;
		}
		else
		{
			return false;
		}
	}

	public Asia getAsia() {
		return asia;
	}

	public Europe getEurope() {
		return europe;
	}

	public NorthAmerica getNorthAmerica() {
		return northAmerica;
	}

	public SouthAmerica getSouthAmerica() {
		return southAmerica;
	}

	public Australia getAustralia() {
		return australia;
	}

	public Africa getAfrica() {
		return africa;
	}
	
	
	
	
}
