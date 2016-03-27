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

import java.util.Scanner;

/**
 * Class {@link UserInterface} will represent the interactive part of the
 * program where input from the user will translate into methods being called.
 * {@link UserInterface} is also the entity that handles displaying information.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class UserInterface {

	private GameEngine ge;

	public UserInterface() {

	}

	public void dispMainMenu() {
		// Print out main menu
		System.out.println("+-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+\n"
				+ "|M|y| |C|o|m|p|S|c|i| |G|i|r|l|\n"
				+ "+-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+");
		System.out.println();
		System.out
				.println("In this game, you are a Computer Science geek and the love of your life, \n"
						+ "Kelly, has been captured by your very own CS141 professor! Kelly\n"
						+ "has been captured and placed into one of nine rooms in \n"
						+ "Building 8. Find her and free her from the grasp of Edwin!");
		System.out.println();

		System.out.println("+~~~~~~~~~~~~~~~~+");
		System.out.println("  1. Start Game");
		System.out.println("  2. Load Game");
		System.out.println("  3. How To Play");
		System.out.println("  4. Debug Mode");
		System.out.println("  5. Quit");
		System.out.println("+~~~~~~~~~~~~~~~~+");
	}

	public void selectMainMenu(Scanner sc) {
		// This method performs one of the main menu

		switch (sc.nextLine().charAt(0)) {

		case SystemValue.CHOICE_1:
			ge = new GameEngine("gameSave1.dat", false);

			selectGameMode(sc);

			ge.createGame();

			break;

		case SystemValue.CHOICE_2:
			ge = new GameEngine("gameSave1.dat", true);
			if (ge.isSuccess()) {

			} else {
				this.dispMainMenu();
				this.selectMainMenu(sc);
			}

			break;

		case SystemValue.CHOICE_3:

			dispHowToPlay();

			this.dispMainMenu();

			this.selectMainMenu(sc);

			break;

		case SystemValue.CHOICE_4:
			ge = new GameEngine("gameSave1.dat", false);

			ge.debugModeOn();

			selectGameMode(sc);

			ge.createGame();

			break;

		case SystemValue.CHOICE_5:

			quitGame();

			break;

		default:

			dispInvalidInputMessage();

			dispMainMenu();

			selectMainMenu(sc);

			break;

		}

	}

	private void selectGameMode(Scanner sc) {

		dispGameModeMenu();

		switch (sc.nextLine().charAt(0)) {

		case SystemValue.CHOICE_1:
			break;

		case SystemValue.CHOICE_2:

			SystemValue.AI_MODE_ON = true;

			break;

		default:

			dispInvalidInputMessage();

			selectGameMode(sc);

			break;

		}

	}

	private void dispInvalidInputMessage() {

		System.out.println("Your choice is invalid. Please try again.");
		System.out.println();

	}

	private void dispGameModeMenu() {

		System.out.println("1. Normal Mode");
		System.out.println("2. Hard Mode");

	}

	private void dispHowToPlay() {
		// This method displays how to play the game

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to My Comp Sci Girl.");
		System.out.println("");

		System.out
				.println("In this game, you are a Computer Science geek and the love of your life, Kelly, has been captured by the evil");

		System.out
				.println("Edwin.  She is trapped inside Building 8, forced to be Edwin’s coding slave unless you can find her and stop him.");

		System.out
				.println("Edwin has Kelly’s ex-boyfriends patrolling the halls.  You must find the room she is being held hostage in so you ");
		System.out.println("can rescue and marry her. ");
		System.out.println("");
		System.out.println("This is a turn based game!");
		System.out.println("");
		System.out.println("Game objective:");
		System.out.println("");
		System.out
				.println("1. Find the room where Kelly is being held hostage in order to marry her.");
		System.out
				.println("2. Avoid getting beaten up by Kelly’s ex-boyfriends.");
		System.out.println("");
		System.out.println("Game Rules:");
		System.out.println("");
		System.out.println("1. You can get beat up 3 times before you die.");
		System.out
				.println("2. You carry with you a copy of Diablo III that you can throw at Kelly's ex-boyfriends to get them off your back.");
		System.out
				.println("3. You are able to look 2 spaces ahead with the look option.");
		System.out
				.println("4. Your turn ends when you move or throw Diablo III. ");
		System.out
				.println("5. You can only enter rooms from the north, because that's where the door is located.");
		System.out
				.println("6. There are extra items around building 8 that can help you along the way:");
		System.out
				.println("     [d] - Diablo III.  This grants an additional copy of Diablo III Collector's Edition.");
		System.out
				.println("     [a] - Axe Body Spray.  You cannot be killed for 5 turns as you now smell like all her other ex boyfriends and they ");
		System.out.println("     cannot differentiate.");
		System.out
				.println("     [p] - Edwin's Day Planner.  You read Edwin's day planner, the first bullet line reads \"Kidnap Kelly\" and has the ");
		System.out
				.println("     room number next to it.  You now know where Kelly is.");
		System.out.println();
		System.out.println();
		System.out.println("Press any key to return to main menu...");
		if (sc.nextLine().length() >= 0) {
		}

	}

	private void quitGame() {
		// This method quit the game
		if (ge != null) {
			ge.gameOver();
		}
		System.out.println("Thanks for playing!");
		System.exit(1);
	}

	public void dispGameScreen() {

		ge.dispGridMap();

		ge.dispPlayerStatus();

		dispPlayMenu();

	}

	public void dispPlayMenu() {
		// Display the play menu

		System.out.print(SystemValue.QUIT + ". Quit");
		System.out.print("    ");
		System.out.print(SystemValue.SAVE + ". Save");
		System.out.print("     ");
		System.out.print(SystemValue.LOAD + ". Load");

		System.out.println();

		System.out.print(SystemValue.MOVE + ". Move");
		System.out.print("    ");
		System.out.print(SystemValue.SHOOT + ". Throw");
		System.out.print("    ");
		System.out.print(SystemValue.LOOK + ". Look");

		System.out.println();

	}

	public void playerTurn(Scanner sc) {
		// The player plays with this method

		char moveType = sc.nextLine().charAt(0);

		switch (moveType) {

		case SystemValue.QUIT:

			quitGame();

			break;

		case SystemValue.SAVE:

			ge.saveGame();

			dispDirectionMenu();

			break;

		case SystemValue.LOAD:

			ge = new GameEngine("gameSave1.dat", true);

			if (!ge.isSuccess()) {

				this.dispMainMenu();

				this.selectMainMenu(sc);

			}

			break;

		default:

			if (moveType != SystemValue.MOVE && moveType != SystemValue.SHOOT
					&& moveType != SystemValue.LOOK) {

				dispInvalidInputMessage();

				dispPlayMenu();

				playerTurn(sc);

			}

			moveOnToTheMove(moveType, sc);

			break;

		}

	}

	private void moveOnToTheMove(char moveType, Scanner sc) {

		dispDirectionMenu();

		char direction = sc.nextLine().charAt(0);

		switch (direction) {

		case SystemValue.NORTH:

			systemMessage(ge.turnOfPlayer(moveType, direction));

			break;

		case SystemValue.SOUTH:

			systemMessage(ge.turnOfPlayer(moveType, direction));

			break;

		case SystemValue.WEST:

			systemMessage(ge.turnOfPlayer(moveType, direction));

			break;

		case SystemValue.EAST:

			systemMessage(ge.turnOfPlayer(moveType, direction));

			break;

		default:

			dispInvalidInputMessage();

			moveOnToTheMove(moveType, sc);

			break;
		}

	}

	private void dispDirectionMenu() {
		// TODO Auto-generated method stub

		System.out.println("w. UP,\t\ts. DOWN");

		System.out.println("a. LEFT,\td. RIGHT");

	}

	public void enemyTurn() {

		systemMessage(ge.turnOfEnemy());

	}

	public boolean isGameOver() {
		// Returns true if the game is over, or false
		if (ge != null) {
			return ge.isGameOver();
		} else {
			return true;
		}
	}

	public boolean isPlayerTurnOver() {
		// Returns true if the player's turn is over, or false

		return ge.isPlayerTurnOver();

	}

	public void dispGameResult() {
		// Print out the game result after the game is over

	}

	private void systemMessage(int playerMove) {

		switch (playerMove) {
		case ReturnValue.ENEMY_CAUGHT_YOU:
			ge.dispGridMap();
			System.out
					.println("You’ve been beaten up by one of Kelly’s ex-boyfriends!");
			pressEnterKey();
			ge.resetGame();
			break;
		case ReturnValue.ENEMY_KILLED_YOU:
			ge.dispGridMap();
			System.out.println("You’ve been beaten up so badly that you died!");
			break;
		case ReturnValue.WRONG_DIRECTION:
			System.out.println("Wrong direction!");
			pressEnterKey();
			break;
		case ReturnValue.ROOM_IS_EMPTY:
			System.out.println("Kelly is not here!");
			pressEnterKey();
			break;
		case ReturnValue.YOU_FOUND_FLAG:
			System.out
					.println("===================================================================================");
			System.out
					.println("You’ve won the game and freed Kelly from the grasps of the Evil Edwin.");
			System.out.println("");
			System.out
					.println("In doing so, the two of you got married and each have a job working for a major company. You two live in a nice ");
			System.out
					.println("suburban home in Southern California and are living happily ever after.");
			System.out.println("");
			System.out
					.println("Edwin was eventually captured and forced to be a teacher a Cal Poly Pomona for the rest of his life. ");
			System.out.println("");
			System.out
					.println("===================================================================================");
			Scanner sc = new Scanner(System.in);

			System.out.println("Press any key to exit.");
			sc.nextLine();
			break;
		case ReturnValue.OUT_OF_BULLET:
			System.out
					.println("You have no more copies of Diablo III to throw!");
			pressEnterKey();
			break;
		case ReturnValue.YOU_ALREADY_LOOKED:
			System.out.println("You already looked in this turn!");
			pressEnterKey();
			break;
		case ReturnValue.AMMO_SUPPLIED:
			System.out.println("You found another copy of Diablo III!");
			pressEnterKey();
			break;
		case ReturnValue.SHOW_THE_FLAG:
			System.out
					.println("You pick up Edwin's day planner and read the first entry: 2:00 PM - Kidnap Kelly and lock her up.");
			System.out.println();
			System.out
					.println("The room number is listed.  You now know where he is hiding her!");
			pressEnterKey();
			break;
		case ReturnValue.YOU_ARE_INVINCIBLE:
			System.out
					.println("You find some Axe body spray and spray it on yourself...");
			System.out
					.println("You now smell like all of Kelly's ex-boyfriends and they will not attack you.");
			System.out.println("You have 5 turns before the scent wears off.");
			pressEnterKey();
			break;

		}

	}

	public void pressEnterKey() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Continue [press enter key]");
		sc.nextLine();
	}

}
