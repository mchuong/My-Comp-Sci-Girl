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
* Class {@link Unit} is a subclass to {@link Cell}. It is the superclass to
* {@link Player} and {@link Enemy} and therefore has characteristics that both
* those objects have. It has behaviors such as move ({@link #move(char)}) and die ({@link #die()}).
* It also has attributes such as {@link #isAlive}, row and column position which is essential to know there the {@link Unit}
* is located.
*
* @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
*/
public abstract class Unit extends Cell implements Serializable{

   private int rowPosition;
   private int colPosition;
   private boolean alive;

   public Unit(char unitSymbol) {

       super(unitSymbol);

       rowPosition = 0;
       colPosition = 0;
       alive = true;

   }

   public abstract int move(Cell[][] gridMap, char direction);

   public abstract boolean isWrongDirection(Cell targetCell, char direction);

   public void move(char dir) {

       switch (dir) {

       case SystemValue.NORTH:

           moveUp();

           break;

       case SystemValue.SOUTH:

           moveDown();

           break;

       case SystemValue.WEST:

           moveToLeft();

           break;

       case SystemValue.EAST:

           moveToRight();

           break;

       }
   }

   public void moveBack(char dir) {

       switch (dir) {

       case SystemValue.NORTH:

           moveDown();

           break;

       case SystemValue.SOUTH:

           moveUp();

           break;

       case SystemValue.WEST:

           moveToRight();

           break;

       case SystemValue.EAST:

           moveToLeft();

           break;

       }
   }

   private void moveUp() {

       rowPosition--;

   }

   private void moveDown() {

       rowPosition++;

   }

   private void moveToLeft() {

       colPosition--;

   }

   private void moveToRight() {

       colPosition++;

   }

   public void setUnitPosition(int rowIndex, int colIndex) {

       this.rowPosition = rowIndex;
       this.colPosition = colIndex;

   }

   public void die() {

       alive = false;

   }

   public Cell getTargetCell(Cell[][] gridMap, int rowPosition, int colPosition) {

       try {
           return gridMap[rowPosition][colPosition];

       } catch (ArrayIndexOutOfBoundsException e) {

           return null;

       }

   }

   public int getRowPosition() {

       return rowPosition;

   }

   public int getColPosition() {

       return colPosition;

   }

   public boolean isAlive() {

       return alive;

   }
}

