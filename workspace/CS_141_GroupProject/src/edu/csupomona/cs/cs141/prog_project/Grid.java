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

import java.util.Random;
import java.io.Serializable;

/**
 * Class {@link Grid} will represent the 9 x 9 grid of {@link Cell}s that
 * contain the {@link Unit}s, {@link Item}s, and {@link Room}s of the game. It
 * will internally hold the 2-D array of {@link Cell}s used to represent the
 * game’s world.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Grid implements Serializable {

	/**
	 * This field represents the {@link Grid}. This field is a two-dimensional
	 * array that holds {@link Cell}. The size of array is {@link #gridMap.length} by
	 * {@link #SystemValue.NUM_OF_COL}.
	 */
	private Cell[][] gridMap = new Cell[SystemValue.NUM_OF_ROW][SystemValue.NUM_OF_COL];

	/**
	 * This field represents the list of the {@link Room}. The size of array is
	 * {@link #roomList.length}.
	 */
	private Room[] roomList = new Room[SystemValue.NUM_OF_ROOM];

	/**
	 * This field represents the list of the {@link Enemy}. The size of array is
	 * {@link #SystemValue.NUM_OF_ENEMY}.
	 */
	private Unit[] enemyList = new Enemy[SystemValue.NUM_OF_ENEMY];

	private Item[] itemList = { new Bullet(), new Radar(), new Invincibility() };

	/**
	 * This field represents the {@link Player}.
	 */
	private Unit player;

	private int curEnemyCount;
	private int counter = 0;

	/**
	 * This default constructor will initialized or the fields: {@link #gridMap}
	 * , {@link #roomList}, {@link # itemList}, {@link #enemyLIst}, and
	 * {@link #player}
	 */
	public Grid() {

		for (int rowI = 0; rowI < gridMap.length; rowI++)
			for (int colI = 0; colI < SystemValue.NUM_OF_COL; colI++)
				gridMap[rowI][colI] = new Cell();

		for (int i = 0; i < roomList.length; i++)
			roomList[i] = new Room();

		for (int i = 0; i < enemyList.length; i++)
			enemyList[i] = new Enemy();

		player = new Player();

		curEnemyCount = enemyList.length;

	}

	// Below methods perform player turn

	public int moveOfPlayer(char moveType, char direction) {

		switch (moveType) {

		case SystemValue.MOVE:

			return playerMove(direction);

		case SystemValue.SHOOT:

			return playerShoot(direction);

		case SystemValue.LOOK:

			playerLook(direction);

			break;

		}

		return 0;

	}

	// Below methods perform player move

	private int playerMove(char direction) {

		int rowIndex = player.getRowPosition();
		int colIndex = player.getColPosition();

		gridMap[rowIndex][colIndex] = null;

		gridMap[rowIndex][colIndex] = new Cell();

		player.move(direction);

		int playerMoveResult = player.move(gridMap, direction);

		if (playerMoveResult == ReturnValue.WRONG_DIRECTION
				|| playerMoveResult == ReturnValue.ROOM_IS_EMPTY
				|| playerMoveResult == ReturnValue.YOU_FOUND_FLAG)
			player.moveBack(direction);

		gridMap[player.getRowPosition()][player.getColPosition()] = player;

		updateItemList();
		setCounter(this.counter);
		return playerMoveResult;

	}

	// This method perform player shoot

	private void updateItemList() {
		// TODO Auto-generated method stub

		int curItemCount = itemList.length;

		for (int i = 0; i < itemList.length; i++)
			if (itemList[i].isObtained())
				curItemCount--;

		Item[] newItemList = new Item[curItemCount];

		int newItemListIndex = 0;

		for (int i = 0; i < itemList.length; i++)
			if (!itemList[i].isObtained())
				newItemList[newItemListIndex++] = itemList[i];

		itemList = newItemList;

	}

	// End of player move

	private int playerShoot(char direction) {
		// TODO Auto-generated method stub

		if (((Player) player).getAmmoCount() < 1)
			return ReturnValue.OUT_OF_BULLET;

		((Player) player).playerShoot(gridMap, direction);

		updateEnemyList();

		return 0;

	}

	private void updateEnemyList() {
		// TODO Auto-generated method stub

		for (int i = 0; i < enemyList.length; i++)
			if (!enemyList[i].isAlive())
				curEnemyCount--;

		Unit[] newEnemyList = new Enemy[curEnemyCount];

		int newEnemyListIndex = 0;

		for (int i = 0; i < enemyList.length; i++)
			if (enemyList[i].isAlive())
				newEnemyList[newEnemyListIndex++] = enemyList[i];

		enemyList = newEnemyList;

	}

	// End of player shoot

	// This method perform player look

	private void playerLook(char direction) {
		// TODO Auto-generated method stub

		((Player) player).playerLook(gridMap, direction);

	}

	// End of player look

	// End of player turn

	/**
	 * 
	 * order==============----------------=========================--------
	 * 
	 * @return
	 */

	public int moveOfEnemy(char aiDirection) {

		int moveOfEnemyResult = 0;

		for (int i = 0; i < enemyList.length; i++) {
			
			moveOfEnemyResult = ((Enemy) enemyList[i]).lookForPlayer(player);

			if (moveOfEnemyResult == ReturnValue.ENEMY_FOUND_PLAYER
					&& ((Player) player).getInvincibleTurnCount()  < 0)
				return ((Enemy) enemyList[i]).catchPlayer(player);

		}

		for (int i = 0; i < enemyList.length; i++)
			if (((Enemy) enemyList[i]).didFindPlayer())
				enemyMove(i, aiDirection);
			else
				enemyMove(i, getRandomDirection());

		return 0;

	}

	private char getRandomDirection() {

		Random r = new Random();

		switch (r.nextInt(4)) {

		case 0:

			return SystemValue.NORTH;

		case 1:

			return SystemValue.SOUTH;

		case 2:

			return SystemValue.WEST;

		case 3:

			return SystemValue.EAST;
		}

		return '0';
	}

	private void enemyMove(int enemyListIndex, char direction) {
		// TODO Auto-generated method stub

		int rowIndex = enemyList[enemyListIndex].getRowPosition();
		int colIndex = enemyList[enemyListIndex].getColPosition();

		gridMap[rowIndex][colIndex] = null;

		gridMap[rowIndex][colIndex] = new Cell();

		enemyList[enemyListIndex].move(direction);

		if (enemyList[enemyListIndex].move(gridMap, direction) == ReturnValue.WRONG_DIRECTION) {
			enemyList[enemyListIndex].moveBack(direction);
			enemyList[enemyListIndex].move('w');
			if (enemyList[enemyListIndex].move(gridMap, 'w') == ReturnValue.WRONG_DIRECTION) {
				enemyList[enemyListIndex].moveBack('w');
				enemyList[enemyListIndex].move('s');
				if (enemyList[enemyListIndex].move(gridMap, 's') == ReturnValue.WRONG_DIRECTION) {
					enemyList[enemyListIndex].moveBack('s');
					enemyList[enemyListIndex].move('a');
					if (enemyList[enemyListIndex].move(gridMap, 'a') == ReturnValue.WRONG_DIRECTION) {
						enemyList[enemyListIndex].moveBack('a');
						enemyList[enemyListIndex].move('d');
						if (enemyList[enemyListIndex].move(gridMap, 'd') == ReturnValue.WRONG_DIRECTION) {
							enemyList[enemyListIndex].moveBack('d');
						}
					}
				}
			}
		}

		gridMap[enemyList[enemyListIndex].getRowPosition()][enemyList[enemyListIndex]
				.getColPosition()] = enemyList[enemyListIndex];

	}

	public void createGridMap() {
		// TODO Auto-generated method stub

		locateRoom();

		flagRoom();

		locateEnemy();

		locateItem();

		locatePlayer();

	}

	private void locateRoom() {
		// TODO Auto-generated method stub

		int roomListIndex = 0;

		for (int rowI = 1; rowI < gridMap.length; rowI += 3)
			for (int colI = 1; colI < SystemValue.NUM_OF_COL; colI += 3)
				gridMap[rowI][colI] = roomList[roomListIndex++];

	}

	private void flagRoom() {
		// TODO Auto-generated method stub

		Random r = new Random();

		roomList[r.nextInt(roomList.length)].flagRoom();

	}

	private void locateEnemy() {
		// TODO Auto-generated method stub

		int randomRowIndex = 0;
		int randomColIndex = 0;

		for (int i = 0; i < enemyList.length; i++) {

			while (true) {

				randomRowIndex = getRandomRowIndex();

				randomColIndex = getRandomColIndex();

				if (randomRowIndex > gridMap.length - 4 && randomColIndex < 3
						|| !gridMap[randomRowIndex][randomColIndex].isEmpty())
					continue;

				else {

					gridMap[randomRowIndex][randomColIndex] = enemyList[i];

					enemyList[i]
							.setUnitPosition(randomRowIndex, randomColIndex);

					break;

				}
			}
		}
	}

	private int getRandomRowIndex() {
		// TODO Auto-generated method stub

		Random r = new Random();

		return r.nextInt(SystemValue.NUM_OF_ROW);

	}

	private int getRandomColIndex() {
		// TODO Auto-generated method stub

		Random r = new Random();

		return r.nextInt(SystemValue.NUM_OF_COL);

	}

	private void locateItem() {
		// TODO Auto-generated method stub

		int randomRowIndex = 0;
		int randomColIndex = 0;

		for (int i = 0; i < itemList.length; i++) {

			while (true) {

				randomRowIndex = getRandomRowIndex();

				randomColIndex = getRandomColIndex();

				if (randomRowIndex > gridMap.length - 4 && randomColIndex < 3
						|| !gridMap[randomRowIndex][randomColIndex].isEmpty())
					continue;

				else {

					gridMap[randomRowIndex][randomColIndex] = itemList[i];

					itemList[i].setItemPosition(randomRowIndex, randomColIndex);

					break;

				}
			}
		}
	}

	private void locatePlayer() {
		// TODO Auto-generated method stub

		gridMap[gridMap.length - 1][0] = player;

		player.setUnitPosition(gridMap.length - 1, 0);

	}

	public char getCellSymbol(int rowIndex, int colIndex) {

		try {

			return gridMap[rowIndex][colIndex].getSymbol();

		} catch (ArrayIndexOutOfBoundsException e) {

			return '0';

		}
	}

	public int getPlayerLifeCount() {
		// TODO Auto-generated method stub

		return ((Player) player).getLifeCount();

	}

	public int getPlayerAmmoCount() {
		// TODO Auto-generated method stub

		return ((Player) player).getAmmoCount();
	}

	public int getPlayerInvincibleTurnCount() {
		// TODO Auto-generated method stub

		return ((Player) player).getInvincibleTurnCount();

	}

	public void showFlaggedRoom() {
		// TODO Auto-generated method stub

		for (int i = 0; i < roomList.length; i++)
			if (roomList[i].isFlagged())
				roomList[i].setSymbol(SystemValue.FLAG_SYMBOL);

	}

	public void setGridMapVisibility(boolean debugModeOn) {

		refreshItemCell();

		for (int rowI = 0; rowI < gridMap.length; rowI++)
			for (int colI = 0; colI < SystemValue.NUM_OF_COL; colI++)
				gridMap[rowI][colI].setVisibility(debugModeOn);

		for (int i = 0; i < roomList.length; i++)
			roomList[i].setVisibility(true);

		player.setVisibility(true);

		setPlayerVision();

	}

	private void refreshItemCell() {
		// TODO Auto-generated method stub

		for (int i = 0; i < itemList.length; i++) {
			if (gridMap[itemList[i].getRowPosition()][itemList[i]
					.getColPosition()].isEmpty())
				gridMap[itemList[i].getRowPosition()][itemList[i]
						.getColPosition()] = itemList[i];
		}
	}

	private void setPlayerVision() {

		int playerRowPosition = player.getRowPosition();
		int playerColPosition = player.getColPosition();

		if (playerRowPosition - 1 > -1)
			gridMap[playerRowPosition - 1][playerColPosition]
					.setVisibility(true);

		if (playerRowPosition + 1 < gridMap.length)
			gridMap[playerRowPosition + 1][playerColPosition]
					.setVisibility(true);

		if (playerColPosition - 1 > -1)
			gridMap[playerRowPosition][playerColPosition - 1]
					.setVisibility(true);

		if (playerColPosition + 1 < SystemValue.NUM_OF_COL)
			gridMap[playerRowPosition][playerColPosition + 1]
					.setVisibility(true);

	}

	public void respawnPlayer() {

		for (int n = 0; n < curEnemyCount; n++)
			removeTargetCell(enemyList[n].getRowPosition(),
					enemyList[n].getColPosition());

		locateEnemy();

		removeTargetCell(player.getRowPosition(), player.getColPosition());

		locatePlayer();

	}

	private void removeTargetCell(int targetRow, int targetCol) {

		gridMap[targetRow][targetCol] = new Cell();

	}

	public void dispGridMap() {
		// This method displays the grid map

		for (int rowI = 0; rowI < SystemValue.NUM_OF_ROW; rowI++) {

			for (int colI = 0; colI < SystemValue.NUM_OF_COL; colI++)
				System.out.print("[" + getCellSymbol(rowI, colI) + "]");

			System.out.println();

		}
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 *            the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter + 1;
	}

	public void markAiEnemy() {

		for (int i = 0; i < enemyList.length; i++) {
			if (((Enemy) enemyList[i]).lookForPlayer(player) == ReturnValue.ENEMY_FOUND_PLAYER)
				((Enemy) enemyList[i]).setFoundPlayer(true);
			else
				((Enemy) enemyList[i]).setFoundPlayer(false);

		}

	}
	/**
	 * @return the counter
	 */

}