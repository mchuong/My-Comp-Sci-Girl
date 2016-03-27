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
 *   Team Members:        Michelle Chuong
 *                  Dominick Do
 *                  Kwang Jae Jun
 *                  Kelly Nguyen
 *                  John Tran
 */
package edu.csupomona.cs.cs141.prog_project;

import java.io.Serializable;

/**
 * Class {@link Radar} is a subclass of {@link Item} which in turn is a subclass
 * of {@link Cell}. If the {@link Player} obtains the {@link Item} {@link Radar}
 * , they will be be able to see which room is {@link #flagged}.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Radar extends Item implements Serializable {

	/**
	 * The default constructor initializes the {@link #symbol}.
	 */
	public Radar() {

		super(SystemValue.RADAR_SYMBOL);

	}

	/**
	 * This method returns the location of the flag. The {@link Player} has
	 * {@link #obtainItem()}.
	 */
	public int consumeThisItem(Player targetPlayer) {

		obtainItem();

		return ReturnValue.SHOW_THE_FLAG;

	}
}
