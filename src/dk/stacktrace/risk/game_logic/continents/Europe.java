package dk.stacktrace.risk.game_logic.continents;


import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.game_logic.enumerate.TerritoryID;

public class Europe extends Continent {
	private Territory
	iceland,
	scandinavia,
	greatBritain,
	northEurope,
	southEurope,
	westEurope,
	ukraine;
		
	public Europe(Board board) {
		this.board = board;
		territories = new ArrayList<Territory>();
		reinforcementBonus = 5;
		createTerritories();
		name = "Europe";
	}
	
	@Override
	protected void createTerritories() {
				iceland = new Territory("Iceland", TerritoryID.ICE);
				scandinavia = new Territory("Scandinavia", TerritoryID.SCA);
				greatBritain = new Territory("Great Britain", TerritoryID.GBR);
				northEurope = new Territory("North Europe", TerritoryID.NEU);
				southEurope = new Territory("South Europe", TerritoryID.SEU);
				westEurope = new Territory("West Europe", TerritoryID.WEU);
				ukraine = new Territory("Ukraine", TerritoryID.UKR);

				Collections.addAll(territories, 
						iceland,
						scandinavia,
						greatBritain,
						northEurope,
						southEurope,
						westEurope,
						ukraine);
	}

	@Override
	public void setNeighbours() {
		iceland.addNeighbour(board.getNorthAmerica().getGreenland());
		iceland.addNeighbour(greatBritain);
		iceland.addNeighbour(scandinavia);
		
		scandinavia.addNeighbour(iceland);
		scandinavia.addNeighbour(greatBritain);
		scandinavia.addNeighbour(northEurope);
		scandinavia.addNeighbour(ukraine);
		
		greatBritain.addNeighbour(iceland);
		greatBritain.addNeighbour(westEurope);
		greatBritain.addNeighbour(northEurope);
		greatBritain.addNeighbour(scandinavia);
		
		northEurope.addNeighbour(greatBritain);
		northEurope.addNeighbour(westEurope);
		northEurope.addNeighbour(southEurope);
		northEurope.addNeighbour(ukraine);
		northEurope.addNeighbour(scandinavia);
		
		southEurope.addNeighbour(westEurope);
		southEurope.addNeighbour(board.getAfrica().getNorthAfrica());
		southEurope.addNeighbour(board.getAfrica().getEgypt());
		southEurope.addNeighbour(board.getAsia().getMiddleEast());
		southEurope.addNeighbour(ukraine);
		southEurope.addNeighbour(northEurope);
		
		westEurope.addNeighbour(greatBritain);
		westEurope.addNeighbour(northEurope);
		westEurope.addNeighbour(southEurope);
		westEurope.addNeighbour(board.getAfrica().getNorthAfrica());
		
		ukraine.addNeighbour(northEurope);
		ukraine.addNeighbour(scandinavia);
		ukraine.addNeighbour(board.getAsia().getUral());
		ukraine.addNeighbour(board.getAsia().getAfghanistan());
		ukraine.addNeighbour(board.getAsia().getMiddleEast());
		ukraine.addNeighbour(southEurope);
	}

	public Territory getIceland() {
		return iceland;
	}

	public Territory getScandinavia() {
		return scandinavia;
	}

	public Territory getGreatBritain() {
		return greatBritain;
	}

	public Territory getNorthEurope() {
		return northEurope;
	}

	public Territory getSouthEurope() {
		return southEurope;
	}

	public Territory getWestEurope() {
		return westEurope;
	}

	public Territory getUkraine() {
		return ukraine;
	}

	public Board getBoard() {
		return board;
	}

	@Override
	public String getId()
	{
		return "eu";
	}
}
