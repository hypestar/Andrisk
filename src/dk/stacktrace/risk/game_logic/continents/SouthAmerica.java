package dk.stacktrace.risk.game_logic.continents;

import dk.stacktrace.risk.game_logic.TerritoryID;

import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Territory;

public class SouthAmerica extends Continent {
	private Territory
	venezuela,
	brazil,
	peru,
	argentina;
	Board board;
	
	public SouthAmerica(Board board) {
		this.board = board;
		territories = new ArrayList<Territory>();
		reinforcementBonus = 2;
		createTerritories();
	}
	
	@Override
	protected void createTerritories() {
		venezuela = new Territory("Venezuela", TerritoryID.VEN);
		brazil = new Territory("Brazil", TerritoryID.BRA);
		peru = new Territory("Peru", TerritoryID.PER);
		argentina = new Territory("Argentina", TerritoryID.ARG);
		
		Collections.addAll(territories, 
				venezuela,
				brazil,
				peru,
				argentina);
	}

	@Override
	public void setNeighbours() {
		venezuela.addNeighbour(board.getNorthAmerica().getCentralAmerica());
		venezuela.addNeighbour(brazil);
		venezuela.addNeighbour(peru);
		
		brazil.addNeighbour(venezuela);
		brazil.addNeighbour(peru);
		brazil.addNeighbour(argentina);
		brazil.addNeighbour(peru);
		
		peru.addNeighbour(venezuela);
		peru.addNeighbour(brazil);
		peru.addNeighbour(argentina);
		
		argentina.addNeighbour(peru);
		argentina.addNeighbour(brazil);
	}

	public Territory getVenezuela() {
		return venezuela;
	}

	public Territory getBrazil() {
		return brazil;
	}

	public Territory getPeru() {
		return peru;
	}

	public Territory getArgentina() {
		return argentina;
	}

	public Board getBoard() {
		return board;
	}
}
