package dk.stacktrace.risk.game_logic.battle;

import java.util.Random;

public class Dice implements Comparable<Dice>{

	private Random rand; 
	private int dice;
	private boolean attackDice;
	
	public Dice(boolean attackDice) {
		this.attackDice = attackDice;
		rand = new Random();
	}
	
	public int roll()
	{
		return (dice = rand.nextInt(5) + 1);
	}
	
	public int getDice() {
		return dice;
	}
	
	public boolean isAttackDice() {
		return attackDice;
	}

	public int compareTo(Dice other) {
		// true if comparing different kinds of dices i.e. comparing an attack dice with a defend dice. 
		if (!((attackDice && other.isAttackDice()) || (!attackDice && !other.isAttackDice())))
		{
			if (dice - other.getDice() == 0)
			{
				if (attackDice)
				{
					return -1;
				}
				else
				{
					return 1;
				}
			} 			
		}
		return dice - other.getDice();
	}
}
