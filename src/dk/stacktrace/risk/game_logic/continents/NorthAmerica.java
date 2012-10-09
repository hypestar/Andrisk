package dk.stacktrace.risk.game_logic.continents;

import dk.stacktrace.risk.game_logic.TerritoryID;

import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Territory;

public class NorthAmerica extends Continent {
	private Territory
	alaska,
	nwTerritory,
	greenland,
	alberta,
	ontario,
	quebec,
	westernUS,
	easternUS,
	centralAmerica;
	
	public NorthAmerica(Board board) {
		this.board = board;
		territories = new ArrayList<Territory>();
		reinforcementBonus = 5;
		createTerritories();
	}

	@Override
	protected void createTerritories() {
				alaska = new Territory("Alaska", TerritoryID.ALA); 
				nwTerritory = new Territory("NW Territory", TerritoryID.NWT);
				greenland = new Territory("Greenland", TerritoryID.GRE);
				alberta = new Territory("Alberta", TerritoryID.ALB);
				ontario = new Territory("Ontario", TerritoryID.ONT);
				quebec = new Territory("Quebec", TerritoryID.QUE);
				westernUS = new Territory("Western US", TerritoryID.WUS);
				easternUS = new Territory("Eastern US", TerritoryID.EUS);
				centralAmerica = new Territory("Central America", TerritoryID.CEN);		
		
				Collections.addAll(territories, 
						alaska,
						nwTerritory,
						greenland,
						alberta,
						ontario,
						quebec,
						westernUS,
						easternUS,
						centralAmerica);
	}

	@Override
	public void setNeighbours() {
		alaska.addNeighbour(nwTerritory);
		alaska.addNeighbour(alberta);
		alaska.addNeighbour(board.getAsia().getKamchatka());
		
		nwTerritory.addNeighbour(alaska);
		nwTerritory.addNeighbour(alberta);
		nwTerritory.addNeighbour(greenland);
		nwTerritory.addNeighbour(ontario);
		
		greenland.addNeighbour(nwTerritory);
		greenland.addNeighbour(ontario);
		greenland.addNeighbour(quebec);
		greenland.addNeighbour(board.getEurope().getIceland());
		
		alberta.addNeighbour(alaska);
		alberta.addNeighbour(nwTerritory);
		alberta.addNeighbour(ontario);
		alberta.addNeighbour(westernUS);
		
		ontario.addNeighbour(alberta);
		ontario.addNeighbour(nwTerritory);
		ontario.addNeighbour(greenland);
		ontario.addNeighbour(quebec);
		ontario.addNeighbour(easternUS);
		ontario.addNeighbour(westernUS);
		
		quebec.addNeighbour(ontario);
		quebec.addNeighbour(greenland);
		quebec.addNeighbour(easternUS);
		
		westernUS.addNeighbour(alberta);
		westernUS.addNeighbour(ontario);
		westernUS.addNeighbour(easternUS);
		westernUS.addNeighbour(centralAmerica);
		
		easternUS.addNeighbour(westernUS);
		easternUS.addNeighbour(ontario);
		easternUS.addNeighbour(quebec);
		easternUS.addNeighbour(centralAmerica);
		
		centralAmerica.addNeighbour(westernUS);
		centralAmerica.addNeighbour(easternUS);
		centralAmerica.addNeighbour(board.getSouthAmerica().getVenezuela());
		
	}

	public Territory getAlaska() {
		return alaska;
	}

	public Territory getNwTerritory() {
		return nwTerritory;
	}

	public Territory getGreenland() {
		return greenland;
	}

	public Territory getAlberta() {
		return alberta;
	}

	public Territory getOntario() {
		return ontario;
	}

	public Territory getQuebec() {
		return quebec;
	}

	public Territory getWesternUS() {
		return westernUS;
	}

	public Territory getEasternUS() {
		return easternUS;
	}

	public Territory getCentralAmerica() {
		return centralAmerica;
	}

	public Board getBoard() {
		return board;
	}
	
	
}
