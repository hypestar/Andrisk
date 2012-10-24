package dk.stacktrace.risk.game_logic.battle;

import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.Territory;

public class Battle {
	private int attackingArmy, defendingArmy;
	private Territory attackingTerritory, defendingTerritory;
	private ArrayList<Dice> winnerDices;
	
	public Battle(Territory attackingTerritory, Territory defendingTerritory) 
	{
		this.attackingTerritory = attackingTerritory;
		this.defendingTerritory = defendingTerritory;
	}

	public void fight()
	{
		ArrayList<Dice> attackDices = new ArrayList<Dice>();
		ArrayList<Dice> defendDices = new ArrayList<Dice>();
		
		for (int dice = 0; dice < getNumOfAttackDices();dice++)
		{
			attackDices.add(new Dice(true));
		}
		
		for (int dice = 0; dice < getNumOfAttackDices();dice++)
		{
			attackDices.add(new Dice(false));
		}
		
		for (Dice dice : attackDices)
		{
			dice.roll();
			Collections.sort(attackDices, new DiceComparator());
		}
		
		for (Dice dice : defendDices)
		{
			dice.roll();
			Collections.sort(defendDices, new DiceComparator());
		}
		
		winnerDices = new ArrayList<Dice>();
		for (int i = 0; i < defendDices.size(); i++)
		{
			
		}
		
		
		
		
	}
	
	public void retreat()
	{
		attackingTerritory.reinforce(attackingArmy);
	}
	
	private int getNumOfAttackDices()
	{
		if(attackingArmy == 1)
		{
			return 1;
		}
		else if (attackingArmy == 2 )
		{
			return 2;	
		}
		else if (attackingArmy >= 3)
		{
			return 3;
		}
		else
		{
			return 0;			
		}
	}

	private int getNumOfDefendDices()
	{
		if(defendingArmy == 1)
		{
			return 1;
		}
		else if (defendingArmy >= 2 )
		{
			return 2;	
		}
		else
		{
			return 0;	
		}
	}
}
