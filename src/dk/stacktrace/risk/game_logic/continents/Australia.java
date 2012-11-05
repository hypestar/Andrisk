package dk.stacktrace.risk.game_logic.continents;

import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.Board;
import dk.stacktrace.risk.game_logic.Territory;
import dk.stacktrace.risk.game_logic.enumerate.TerritoryID;

public class Australia extends Continent {

	private Territory
	indonesia,
	newGuinea,
	westAustralia,
	eastAustralia;
	
	public Australia(Board board) {
		this.board = board;
		territories = new ArrayList<Territory>();
		reinforcementBonus = 2;
		createTerritories();
		name = "Australia";
	}

	
	@Override
	protected void createTerritories() {
				indonesia = new Territory("Indonesia", TerritoryID.IDO);
				newGuinea = new Territory("New Guinea", TerritoryID.NGU);
				westAustralia = new Territory("West Australia", TerritoryID.WAU);
				eastAustralia = new Territory("East Australia", TerritoryID.EAU);
				
				Collections.addAll(territories, 
						indonesia,
						newGuinea,
						westAustralia,
						eastAustralia);
	}

	@Override
	public void setNeighbours() {
		indonesia.addNeighbour(board.getAsia().getSiam());
		indonesia.addNeighbour(newGuinea);
		indonesia.addNeighbour(westAustralia);
		
		newGuinea.addNeighbour(indonesia);
		newGuinea.addNeighbour(eastAustralia);
		newGuinea.addNeighbour(westAustralia);

		westAustralia.addNeighbour(indonesia);
		westAustralia.addNeighbour(eastAustralia);
		westAustralia.addNeighbour(newGuinea);
		
		eastAustralia.addNeighbour(westAustralia);
		eastAustralia.addNeighbour(indonesia);
		eastAustralia.addNeighbour(newGuinea);
	}

	
	public Territory getIndonesia() {
		return indonesia;
	}

	public Territory getNewGuinea() {
		return newGuinea;
	}

	public Territory getWestAustralia() {
		return westAustralia;
	}

	public Territory getEastAustralia() {
		return eastAustralia;
	}


	@Override
	public String getId()
	{
	
		return "au";
	}

	


	

}
