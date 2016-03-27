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
 * Class {@link Room} is a subclass of {@link Cell}. It represents the location
 * in the center of 9 {@link Cell}s in the {@link Grid}. The {@link Room} is the
 * location of the flag which represents the victory of that level. The flag is
 * randomly located in only one {@link Room}.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Room extends Cell implements Serializable {

	/**
	 * This field indicates whether the {@link Room} has a flag or not.
	 */
	boolean flagged;

	/**
	 * The default constructor initializes the {@link #symbol} and {@link #flagged}.
	 */
	public Room() {

		super(SystemValue.ROOM_SYMBOL);

		flagged = false;

	}

	/**
	 * This method sets the {@link #flagged} to {@code true}.
	 */
	public void flagRoom() {

		flagged = true;

	}

	/**
	 * This method returns the {@link #flagged}.
	 * @return {@link #flagged}
	 */
	public boolean isFlagged() {

		return flagged;

	}
}
