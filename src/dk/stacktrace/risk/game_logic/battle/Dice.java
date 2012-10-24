package dk.stacktrace.risk.game_logic.battle;

import java.util.Random;

public class Dice {

	private Random rand; 
	private int dice;
	private boolean attackDice;
	
	public Dice(boolean attackDice) {
		this.attackDice = attackDice;
	}
	
	public int roll()
	{
		return rand.nextInt(5) + 1;
	}
	
	public int getDice() {
		return dice;
	}
	
	public boolean isAttackDice() {
		return attackDice;
	}
	
	
}
