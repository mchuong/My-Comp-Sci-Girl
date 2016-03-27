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
 *                           Dominick Do
 *                  Kwang Jae Jun
 *                  Kelly Nguyen
 *                  John Tran
 */
package edu.csupomona.cs.cs141.prog_project;

import java.io.Serializable;

/**
 * Class {@link Bullet} is a subclass of {@link Item} which in turn is a
 * subclass of {@link Cell}. {@link Bullet} will give the player more ammunition
 * by increasing their {@link #ammoCount} allowing the {@link Player} more
 * chances to {@link #shoot()} and kill {@link Enemy}.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public class Bullet extends Item implements Serializable {

	/**
	 * The default constructor initializes the {@link #symbol}.
	 */
	public Bullet() {

		super(SystemValue.BULLET_SYMBOL);

	}

	/**
	 * This method returns the ammo supplied. The {@link Player}
	 * {@link #gainAmmoCount} of {@code 1}. The {@link Player} has
	 * {@link #obtainItem}.
	 */
	public int consumeThisItem(Player targetPlayer) {

		targetPlayer.gainAmmoCount(1);

		obtainItem();

		return ReturnValue.AMMO_SUPPLIED;

	}
}
