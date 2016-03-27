package edu.csupomona.cs.cs141.prog_project;

import java.io.Serializable;

public class SystemValue implements Serializable {

	protected static final int NUM_OF_ROW = 9;

	protected static final int NUM_OF_COL = 9;

	protected static final int NUM_OF_ROOM = 9;

	protected static final int NUM_OF_ITEM = 3;

	protected static final int NUM_OF_ENEMY = 6;

	protected static final char MOVE = 'm';

	protected static final char SHOOT = 't';

	protected static final char LOOK = 'l';

	protected static final char SAVE = 'v';

	protected static final char LOAD = 'd';

	protected static final char NORTH = 'w';

	protected static final char SOUTH = 's';

	protected static final char QUIT = 'q';

	protected static final char WEST = 'a';

	protected static final char EAST = 'd';

	protected static final char EMPTY_SYMBOL = ' ';

	protected static final char ROOM_SYMBOL = 'R';

	protected static final char FLAG_SYMBOL = 'K';

	protected static final char ENEMY_SYMBOL = 'E';

	protected static final char PLAYER_SYMBOL = 'G';

	protected static final char BULLET_SYMBOL = 'd';

	protected static final char RADAR_SYMBOL = 'p';

	protected static final char INVINCIBILITY_SYMBOL = 'a';

	protected static final char CHOICE_1 = '1';
	protected static final char CHOICE_2 = '2';
	protected static final char CHOICE_3 = '3';
	protected static final char CHOICE_4 = '4';
	protected static final char CHOICE_5 = '5';

	protected static boolean AI_MODE_ON = false;

}
