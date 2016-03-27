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
 * Class {@link Player} is a subclass of {@link Unit} which in turn is a
 * subclass of {@link Cell}. This class represents the user and has attributes
 * that a player should have, such as {@link #ammoCount} which tells how much
 * ammo the user has. It also has behaviors that a player should have like
 * action ({@link #action()}) , shoot ({@link #shoot()}), scout (
 * {@link #scout()}), and pick up an item ({@link #pickUpItem(char)}).
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Player extends Unit implements Serializable {

	private int lifeCount;
	private int ammoCount;
	private int invincibleTurnCount;

	public Player() {

		super(SystemValue.PLAYER_SYMBOL);

		ammoCount = 1;
		invincibleTurnCount = -1;
		lifeCount = 3;

	}

	// Below methods perform player move

	public int move(Cell[][] gridMap, char direction) {

		Cell targetCell = getTargetCell(gridMap, getRowPosition(),
				getColPosition());

		if (isWrongDirection(targetCell, direction))
			return ReturnValue.WRONG_DIRECTION;

		loseInvincibleTurnCount();

		switch (targetCell.getSymbol()) {

		case SystemValue.EMPTY_SYMBOL:

			break;

		case SystemValue.ROOM_SYMBOL:

			return enterTheRoom((Room) targetCell);

		case SystemValue.FLAG_SYMBOL:

			return enterTheRoom((Room) targetCell);

		default:

			return obtainTheItem((Item) targetCell);
		}

		return 0;

	}

	public boolean isWrongDirection(Cell targetCell, char direction) {

		if (targetCell == null)
			return true;

		else if (targetCell.getSymbol() == SystemValue.ENEMY_SYMBOL)
			return true;

		else if (targetCell.getSymbol() == SystemValue.ROOM_SYMBOL
				&& direction != SystemValue.SOUTH)
			return true;

		else if (targetCell.getSymbol() == SystemValue.FLAG_SYMBOL
				&& direction != SystemValue.SOUTH)
			return true;

		return false;

	}

	private int enterTheRoom(Room targetRoom) {
		// TODO Auto-generated method stub

		if (targetRoom.isFlagged())
			return ReturnValue.YOU_FOUND_FLAG;

		return ReturnValue.ROOM_IS_EMPTY;

	}

	private int obtainTheItem(Item targetItem) {
		// TODO Auto-generated method stub

		return targetItem.consumeThisItem(this);

	}

	private void loseInvincibleTurnCount() {

		if (invincibleTurnCount > -1)
			invincibleTurnCount--;

	}

	// End of player move

	// Below methods perform player shoot

	public void playerShoot(Cell[][] gridMap, char direction) {
		// TODO Auto-generated method stub

		int rowIndex = getRowPosition();
		int colIndex = getColPosition();

		switch (direction) {

		case SystemValue.NORTH:

			while (shoot(gridMap, --rowIndex, colIndex))
				;

			break;

		case SystemValue.SOUTH:

			while (shoot(gridMap, ++rowIndex, colIndex))
				;

			break;

		case SystemValue.WEST:

			while (shoot(gridMap, rowIndex, --colIndex))
				;

			break;

		case SystemValue.EAST:

			while (shoot(gridMap, rowIndex, ++colIndex))
				;

			break;

		}

		ammoCount--;

	}

	private boolean shoot(Cell[][] gridMap, int rowIndex, int colIndex) {
		// TODO Auto-generated method stub

		try {
			if (!gridMap[rowIndex][colIndex].isEmpty()
					&& (gridMap[rowIndex][colIndex].getSymbol() != SystemValue.ENEMY_SYMBOL
							&& gridMap[rowIndex][colIndex].getSymbol() != SystemValue.INVINCIBILITY_SYMBOL
							&& gridMap[rowIndex][colIndex].getSymbol() != SystemValue.BULLET_SYMBOL && gridMap[rowIndex][colIndex]
							.getSymbol() != SystemValue.RADAR_SYMBOL))
				return false;
			else if (!gridMap[rowIndex][colIndex].isEmpty()
					&& gridMap[rowIndex][colIndex].getSymbol() == SystemValue.ENEMY_SYMBOL) {

				((Unit) gridMap[rowIndex][colIndex]).die();

				gridMap[rowIndex][colIndex] = null;

				gridMap[rowIndex][colIndex] = new Cell();

			}
		} catch (ArrayIndexOutOfBoundsException e) {

			return false;

		}

		return true;

	}

	// End of player shoot

	// Below methods perform player look

	public void playerLook(Cell[][] gridMap, char direction) {
		// TODO Auto-generated method stub

		int rowIndex = getRowPosition();
		int colIndex = getColPosition();

		switch (direction) {

		case SystemValue.NORTH:

			look(gridMap, rowIndex - 2, colIndex);

			break;

		case SystemValue.SOUTH:

			look(gridMap, rowIndex + 2, colIndex);

			break;

		case SystemValue.WEST:

			look(gridMap, rowIndex, colIndex - 2);

			break;

		case SystemValue.EAST:

			look(gridMap, rowIndex, colIndex + 2);

			break;

		}

	}

	private void look(Cell[][] gridMap, int rowIndex, int colIndex) {
		// TODO Auto-generated method stub

		try {

			gridMap[rowIndex][colIndex].setVisibility(true);

		} catch (ArrayIndexOutOfBoundsException e) {

		}

	}

	// End of player look

	public void loseLife() {

		lifeCount--;

	}

	public void gainAmmoCount(int n) {

		ammoCount += n;

	}

	public void gainInvincibleTurnCount(int n) {

		if (invincibleTurnCount < 0)
			invincibleTurnCount = n;
		else
			invincibleTurnCount += n;

	}

	public int getLifeCount() {

		return lifeCount;

	}

	public int getAmmoCount() {

		return ammoCount;

	}

	public int getInvincibleTurnCount() {

		return invincibleTurnCount;

	}

}