package dk.stacktrace.risk.game_logic;

public class Battle {
	private int attackingArmy, defendingArmy;
	private Territory attackingTerritory, defendingTerritory;
	
	public Battle(Territory attackingTerritory, Territory defendingTerritory) 
	{
		this.attackingTerritory = attackingTerritory;
		this.defendingTerritory = defendingTerritory;
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
