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
/**
* This class represents the cell in the {@link Grid}. This class is designed to
* be extended. So this class will be changed to an abstract class in the
* future. This class has {@link #symbol}, {@link #empty}, and {@link #visible}.
* They represents the symbol of the cell, if the cell is empty, and if the cell
* is visible. Each variable has a getter method. This class also contains two
* methods to set the {@link #symbol} and {@link visible}.
*
* @author Michelle Chuong, Dominick Do, Kwang Jae Jun, Kelly Nguyen, John Tran
*/
import java.io.Serializable;
public class Cell implements Serializable{

   /**
    * This field represents the symbol of the {@link Cell}. Initially
    * {@code ' '}, this field can only be set by the method
    * {@link #setSymbol(char)}. This field is {@code true} if empty, or (@code
    * false}
    */
   private char symbol;

   /**
    * This filed indicates if the {@link Cell} is empty or not. Initially
    * {@code true}.
    */
   private boolean empty;

   /**
    * This field indicates if the {@link Cell} is visible or not. Initially
    * {@code false}, this field can only be set by the method
    * {@link #setVisibility(boolean)}. This field is {@code true} if visible,
    * or (@code false}
    */
   private boolean visible;

   /**
    * This default constructor initialize those fields, {@link #symbol},
    * {@link #empty}, and {@link #visible}.
    */
   public Cell() {

       setSymbol(SystemValue.EMPTY_SYMBOL);

       empty = true;
       visible = false;

   }

   /**
    * This constructor will initialize {@link #symbol} with the given symbol,
    * {@link #symbol}, and {@link #empty}.
    *
    * @param symbol
    *            The given symbol from caller.
    */
   public Cell(char symbol) {

       setSymbol(symbol);

       empty = false;
       visible = false;

   }

   /**
    * This method sets the {@link #symbol} with the given symbol.
    *
    * @param symbol
    *            The given symbol from caller.
    */
   public void setSymbol(char symbol) {

       this.symbol = symbol;

   }

   public void setEmpty(boolean empty) {

       this.empty = empty;

   }

   /**
    * This method sets the visibility of the {@link Cell}.
    *
    * @param visible
    *            This argument is the value of the {@link #visible}.
    */
   public void setVisibility(boolean visible) {

       this.visible = visible;

   }

   /**
    * This method returns the symbol. If the {@link Cell} is invisible, it
    * returns {@code '*'}, or the {@link #symbol}.
    *
    * @return If the {@link Cell} is invisible, it returns {@code '*'}, or the
    *         {@link #symbol}.
    */
   public char getSymbol() {

       if (!visible)
           return '*';

       return symbol;

   }

   /**
    * This method returns the {@link #empty}.
    *
    * @return{@link #empty}
    */
   public boolean isEmpty() {

       return empty;

   }

   /**
    * This method returns the {@link #visible}.
    *
    * @return {@link #visible}
    */
   public boolean isVisible() {

       return visible;

   }
}

