package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeView {
    
    private final Scanner keyboard;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView() {
        
        /* Initialize scanner (for console keyboard) */
        
        keyboard = new Scanner(System.in);
        
    }
	
    public TicTacToeMove getNextMove(boolean isXTurn) {
        
        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */
        
        int row = 0;
        int col = 0;
        int player = 1;
        String turnName;

        if(isXTurn == false){
            turnName = "O";
            player = 2;
        } 
        else{
            turnName = "X";
            player = 1;
        }

        System.out.println("Player "+ player +" (" + turnName + ") Move:");
        System.out.println("Enter the row and column numbers, separated by a space: ");
        row = keyboard.nextInt();
        col = keyboard.nextInt();
            
        
        

        TicTacToeMove playerMove = new TicTacToeMove(row,col);
       

        return playerMove;

    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "!");

    }
    
    public void showBoard(String board) {
        
        System.out.println("\n\n" + board);
        
    }
	
}
