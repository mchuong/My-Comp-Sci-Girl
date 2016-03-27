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

public class Main {

   /**
    * @param args
    */
   public static void main(String[] args) {
       UserInterface ui = new UserInterface();

       Scanner sc = new Scanner(System.in);

       ui.dispMainMenu();

       ui.selectMainMenu(sc);
       
       while (!ui.isGameOver()) {
           while (!ui.isPlayerTurnOver()) {

               ui.dispGameScreen();

               ui.playerTurn(sc);

           }

           if (!ui.isGameOver())
               ui.enemyTurn();

       }

       ui.dispGameResult();

   }
}