/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Project
 *
 * This program fulfills the guidelines stated in the project
 * description. It creates a text-based game using
 * object-oriented programming and demonstrates the three programming
 * paradigms: Inheritance, Encapsulation, and Polymorphism.
 *
 * Team Kelly
 *   Team Members:         Michelle Chuong
 *                  Dominick Do
 *                  Kwang Jae Jun
 *                  Kelly Nguyen
 *                  John Tran
 */
package edu.csupomona.cs.cs141.prog_project;

import java.io.Serializable;

/**
 * Class {@link Enemy} is a subclass of {@link Unit} which in turn is a subclass
 * of {@link Cell}. This class represents the foe and has behaviors that an
 * {@link Enemy} should have, such as {@link #kill(Unit)} which will kill the
 * {@link Unit} that the {@link Enemy} comes into contact with, in this case
 * {@link Player}.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Enemy extends Unit implements Serializable {

	/**
	 * This field indicates that the {@link Player} has been found.
	 */
	private boolean foundPlayer;

	/**
	 * The default constructor initializes the fields {@link #symbol} and 
	 * {@link #foundPlayer}.
	 */
	public Enemy() {

		super(SystemValue.ENEMY_SYMBOL);

		foundPlayer = false;

	}
	
	/**
	 * This method returns {@code 0}. It takes {@link #getTargetCell(Cell[][], int, int)} 
	 * and {@link #move(char)} into the method {@link #isWrongDirection(Cell, char)}. 
	 * If {@link #isWrongDirection(Cell, char)} is {@code true}, it will return the move 
	 * as a wrong direction.
	 */
	public int move(Cell[][] gridMap, char direction) {

		Cell targetCell = getTargetCell(gridMap, getRowPosition(),
				getColPosition());

		if (isWrongDirection(targetCell, direction))
			return ReturnValue.WRONG_DIRECTION;

		return 0;
	}

	/**
	 * This method returns {@code true} if the {@link #targetCell} is {@code null}, 
	 * or any of the following {@link #ROOM_SYMBOL}, {@link #FLAG_SYMBOL}, 
	 * {@link #ENEMY_SYMBOL}, or {@link #PLAYER_SYMBOL}. Else it returns {@code false}.
	 */
	public boolean isWrongDirection(Cell targetCell, char direction) {

		if (targetCell == null
				|| targetCell.getSymbol() == SystemValue.ROOM_SYMBOL
				|| targetCell.getSymbol() == SystemValue.FLAG_SYMBOL
				|| targetCell.getSymbol() == SystemValue.ENEMY_SYMBOL
				|| targetCell.getSymbol() == SystemValue.PLAYER_SYMBOL)
			return true;

		return false;
	}

	/**
	 * This method returns {@link #ENEMY_FOUND_PLAYER} if the {@link Player}'s row position
	 * is equal to the {@link Enemy}'s row position and the {@link Player}'s column 
	 * position minus the {@link Enemy}'s column position is equal to one. Also if the 
	 * {@link Player} and {@link Enemy} have the same column position and the row position 
	 * of the {@link Player} minus the row position of the {@link Enemy} is equal to one, 
	 * {@link #ENEMY_FOUND_PLAYER} is returned. Else the method returns {@code 0}.
	 * @param player
	 * 			The {@link Player} to be looked for.
	 * @return
	 */
	public int lookForPlayer(Unit player) {

		if (player.getRowPosition() == getRowPosition()
				&& Math.abs(player.getColPosition() - getColPosition()) == 1)
			return ReturnValue.ENEMY_FOUND_PLAYER;

		else if (player.getColPosition() == getColPosition()
				&& Math.abs(player.getRowPosition() - getRowPosition()) == 1)
			return ReturnValue.ENEMY_FOUND_PLAYER;

		else
			return 0;

	}
	
	/**
	 * This method demonstrates the {@link Player} being caught. The {@link Player} 
	 * loses a life and if the {@link Player} {@link #lifeCount} is less than {@code 1}, 
	 * then the {@link #killPlayer(Unit)} and returns {@link #ENEMY_KILLED_YOU}. Else 
	 * it returns {@link #ENEMY_CAUGHT_YOU}.
	 * @param player
	 * 			This argument is the {@link Player}.
	 * @return {@link #ENEMY_KILLED_YOU} or {@link #ENEMY_CAUGHT_YOU}
	 */
	public int catchPlayer(Unit player) {

		((Player) player).loseLife();

		if (((Player) player).getLifeCount() < 1) {

			killPlayer(player);

			return ReturnValue.ENEMY_KILLED_YOU;

		} else
			return ReturnValue.ENEMY_CAUGHT_YOU;

	}
	
	/**
	 * This method demonstrates the {@link Player} being killed by calling 
	 * the method {@link #die()}.
	 * @param player
	 * 			The {@link Player} to be killed.
	 */
	private void killPlayer(Unit player) {

		player.die();

	}

	/**
	 * This method sets the {@link #foundPlayer} of the {@link Enemy}.
	 * @param foundPlayer
	 * 			This argument is the value of the {@link #foundPlayer}.
	 */
	public void setFoundPlayer(boolean foundPlayer) {

		this.foundPlayer = foundPlayer;

	}
	
	/**
	 * This method returns {@link #foundPlayer}.
	 * @return {@link #foundPlayer}
	 */
	public boolean didFindPlayer() {

		return foundPlayer;

	}

}
