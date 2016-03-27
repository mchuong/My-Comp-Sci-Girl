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

import java.io.*;

/**
 * Class {@link GameEngine} represents an instance of the game. It is
 * responsible for creating a game with the {@link #createGame()} and
 * progressing the game through until the game’s completion. The
 * {@link GameEngine} will keep track of the current state of the game, as well
 * as statistics. {@link #GameEngine()} will be used to create a game using the
 * {@link Grid} class.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class GameEngine {

	Grid grid;

	private boolean debugModeOn;
	private boolean playerMoved;
	private boolean playerLooked;
	private boolean gameOver;
	private boolean gridSuccess;
	private String gameState;

	private char aiEnemyDirection;

	public GameEngine(String newgameState, boolean loadGame) {

		this.gameState = newgameState;
		debugModeOn = false;
		playerMoved = false;
		playerLooked = false;
		gameOver = false;
		gridSuccess = true;

		aiEnemyDirection = '0';

		if (loadGame) {
			try {

				FileInputStream fileIn = new FileInputStream(this.gameState);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				grid = (Grid) in.readObject();

				in.close();

				fileIn.close();

			} catch (IOException i) {

				System.out.println("There is no available game to load.");
				System.out.println();
				System.out.println();
				gridSuccess = false;

				return;

			} catch (ClassNotFoundException c) {

				System.out.println("Class does not exist.");

				gridSuccess = false;

				return;

			}
		} else
			grid = new Grid();

	}

	public void createGame() {

		grid.createGridMap();

		if (debugModeOn) {
			grid.setGridMapVisibility(true);
			if (debugModeOn)
				grid.showFlaggedRoom();
		}

		else
			grid.setGridMapVisibility(false);

	}

	public void loadGame() {
		// TODO Auto-generated method stub

	}

	public boolean isSuccess() {
		return gridSuccess;
	}

	public void dispGridMap() {
		// This method displays the grid map

		for (int rowI = 0; rowI < SystemValue.NUM_OF_ROW; rowI++) {

			for (int colI = 0; colI < SystemValue.NUM_OF_COL; colI++)
				System.out.print("[" + grid.getCellSymbol(rowI, colI) + "]");

			System.out.println();

		}
	}

	public void dispPlayerStatus() {
		// This method displays the player's status
		System.out.println("==============================");
		System.out.println("Lives Remaining: " + grid.getPlayerLifeCount());
		System.out.println("Copies of Diablo III remaining: "
				+ grid.getPlayerAmmoCount());
		System.out.println("Turns taken: " + grid.getCounter());

		if (grid.getPlayerInvincibleTurnCount() > 0)
			System.out.println("Axe body spray count: "
					+ grid.getPlayerInvincibleTurnCount());
		System.out.println("==============================");
	}

	public int turnOfPlayer(char moveType, char direction) {
		// This method moves the player and returns the result of the move

		aiEnemyDirection = direction;

		if (playerLooked && moveType == SystemValue.LOOK)
			return ReturnValue.YOU_ALREADY_LOOKED;

		int playerTurnResult = grid.moveOfPlayer(moveType, direction);

		if (moveType == SystemValue.LOOK)
			playerLooked = true;

		if (playerTurnResult == ReturnValue.OUT_OF_BULLET)
			return playerTurnResult;

		if (playerTurnResult == ReturnValue.SHOW_THE_FLAG)
			grid.showFlaggedRoom();

		if (playerTurnResult == ReturnValue.YOU_FOUND_FLAG)
			gameOver();

		if (playerTurnResult != ReturnValue.WRONG_DIRECTION
				&& moveType != SystemValue.LOOK) {

			if (debugModeOn)
				grid.setGridMapVisibility(true);

			else
				grid.setGridMapVisibility(false);

			playerMoved = true;

		}

		return playerTurnResult;

	}

	public int turnOfEnemy() {
		// This method moves every enemies in the grid

		int enemyTurnResult = grid.moveOfEnemy(aiEnemyDirection);

		if (debugModeOn)
			grid.setGridMapVisibility(true);
		else
			grid.setGridMapVisibility(false);

		if (enemyTurnResult == ReturnValue.ENEMY_KILLED_YOU)
			gameOver();

		if (SystemValue.AI_MODE_ON)
			grid.markAiEnemy();

		return enemyTurnResult;

	}

	public void gameOver() {
		// TODO Auto-generated method stub
		gameOver = true;
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return gameOver;
	}

	public boolean didPlayerMove() {

		return playerMoved;
	}

	public boolean isPlayerTurnOver() {
		// TODO Auto-generated method stub
		if (playerMoved) {

			playerMoved = false;
			playerLooked = false;

			return true;

		} else
			return false;

	}

	public void resetGame() {

		grid.respawnPlayer();

		if (debugModeOn)
			grid.setGridMapVisibility(true);
		else
			grid.setGridMapVisibility(false);

	}

	public void debugModeOn() {
		// TODO Auto-generated method stub

		debugModeOn = true;

	}

	public void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream(this.gameState);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(grid);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			System.out.println("There was an error saving the game.");
			return;
		}
		System.out.println("Your game has been saved.");
	}

}
