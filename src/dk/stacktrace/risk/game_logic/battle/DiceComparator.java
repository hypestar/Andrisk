package dk.stacktrace.risk.game_logic.battle;

import java.util.Comparator;

public class DiceComparator implements Comparator<Dice>{

	// negative : dice1 < dice2
	// zero : dice1 == dice2
	// positive : dice1 > dice2
	public int compare(Dice dice1, Dice dice2 ) {
		
		// true if comparing different kinds of dices i.e. comparing an attack dice with a defend dice. 
		if (!((dice1.isAttackDice() && dice2.isAttackDice()) || (!dice1.isAttackDice() && !dice2.isAttackDice())))
		{
			if (dice1.getDice() - dice2.getDice() == 0)
			{
				if (dice1.isAttackDice())
				{
					return -1;
				}
				else
				{
					return 1;
				}
			} 			
		}
		return dice1.getDice() - dice2.getDice();
	}
}
