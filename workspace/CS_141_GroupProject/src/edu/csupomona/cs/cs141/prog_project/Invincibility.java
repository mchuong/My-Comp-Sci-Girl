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
 * Class {@link Invincibility} is a subclass of {@link Item} which in turn is a
 * subclass of {@link Cell}. If the {@link Player} obtains the {@link Item}
 * {@link Invincibility}, they will be immune to all attacks by {@link Enemy}
 * for a duration of five turns. {@code 'i'} is the character that will be used
 * for the {@link Cell} for {@link #symbol}. This character will be what the
 * user sees if they are within visible range of the {@link Item}.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Invincibility extends Item implements Serializable {

	/**
	 * The default constructor initializes the {@link #symbol}.
	 */
	public Invincibility() {

		super(SystemValue.INVINCIBILITY_SYMBOL);

	}

	/**
	 * This method returns the {@link Player} as invincible. The {@link Player}
	 * {@link #gainInvincibleTurnCount(5)} when {@link #obtainItem}.
	 */
	public int consumeThisItem(Player targetPlayer) {

		targetPlayer.gainInvincibleTurnCount(5);

		obtainItem();

		return ReturnValue.YOU_ARE_INVINCIBLE;

	}
}
