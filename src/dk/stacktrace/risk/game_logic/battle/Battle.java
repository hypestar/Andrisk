package dk.stacktrace.risk.game_logic.battle;

import java.util.ArrayList;
import java.util.Collections;

import dk.stacktrace.risk.game_logic.Territory;

public class Battle {
	private int attackingArmy;
	private Territory attackingTerritory, defendingTerritory;
	private ArrayList<Dice> winnerDices;
	
	public Battle(int attackingArmy, Territory attackingTerritory, Territory defendingTerritory) 
	{
		this.attackingArmy = attackingArmy;
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
			Collections.sort(attackDices);
		}
		
		for (Dice dice : defendDices)
		{
			dice.roll();
			Collections.sort(defendDices);
		}
		
		winnerDices = new ArrayList<Dice>();
		for (int i = 0; i < defendDices.size(); i++)
		{
			if(defendDices.get(i).compareTo(attackDices.get(i)) > 0)
			{
				winnerDices.add(defendDices.get(i));
				--attackingArmy;
			}
			else
			{
				winnerDices.add(attackDices.get(i));
				defendingTerritory.kill();
			}
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
		if(defendingTerritory.getArmySize() == 1)
		{
			return 1;
		}
		else if (defendingTerritory.getArmySize() >= 2 )
		{
			return 2;	
		}
		else
		{
			return 0;	
		}
	}
}
