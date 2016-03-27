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
 * Class {@link Item} is a subclass of {@link Cell}. If the {@link Player}
 * obtains an {@link Item} they will receive one of a variety of perks that will
 * give them an advantage in the game.
 * 
 * @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
 */
public abstract class Item extends Cell implements Serializable {

	/**
	 * The field represents the row position of the {@link Item}. Initially
	 * {@code 0}, this field is used in the method
	 * {@link #setItemPosition(int, int)}.
	 */
	private int rowPosition;

	/**
	 * The field represents the column position of the {@link Item}. Initially
	 * {@code 0}, this field is used in the method
	 * {@link #setItemPosition(int, int)}.
	 */
	private int colPosition;

	/**
	 * This field indicates whether an {@link Item} has been obtained or not.
	 * Initially {@code false}, this field changes to {@code true} by the method
	 * {@link #obtainItem()}.
	 */
	private boolean obtained;

	/**
	 * The constructor will initialize and send {@link #itemSymbol} to the super
	 * class, and initialize {@link #rowPosition}, {@link #colPosition}, and
	 * {@link #obtained}.
	 * 
	 * @param itemSymbol
	 *            The given item symbol from caller.
	 */
	public Item(char itemSymbol) {

		super(itemSymbol);

		rowPosition = 0;
		colPosition = 0;
		obtained = false;

	}

	/**
	 * This method is an abstract method that represents the {@link Player}
	 * consuming the item.
	 * 
	 * @param targetPlayer
	 *            The {@link Player} is the target.
	 * @return
	 */
	public abstract int consumeThisItem(Player targetPlayer);

	/**
	 * This method sets the item position.
	 * 
	 * @param rowPosition
	 *            This argument is the value of {@link #rowPosition}.
	 * @param colPosition
	 *            This argument is the value of {@link #colPosition}.
	 */
	public void setItemPosition(int rowPosition, int colPosition) {

		this.rowPosition = rowPosition;
		this.colPosition = colPosition;

	}

	/**
	 * This method sets {@link #obtained} to {@code true} when the item has been
	 * obtained.
	 */
	public void obtainItem() {

		obtained = true;

	}

	/**
	 * This method returns the {@link #rowPosition}.
	 * 
	 * @return {@link #rowPosition}
	 */
	public int getRowPosition() {

		return rowPosition;

	}

	/**
	 * This method returns the {@link #colPosition}.
	 * 
	 * @return {@link #colPosition}
	 */
	public int getColPosition() {

		return colPosition;

	}

	/**
	 * This method returns the {@link #obtained}.
	 * 
	 * @return {@link #obtained}
	 */
	public boolean isObtained() {

		return obtained;

	}

}
