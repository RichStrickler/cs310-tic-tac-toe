package edu.jsu.mcis;

public class TicTacToeController {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView();
        
    }

    public void start() {
    
        /* MAIN LOOP (repeats until game is over) */

        /* Display the board using the View's "showBoard()", then use
           "getNextMove()" to get the next move from the player.  Enter
           the move (using the Model's "makeMark()", or display an error
           using the View's "showInputError()" if the move is invalid. */

        while(model.isGameover() == false){

            view.showBoard(model.toString());
            TicTacToeMove playerMove = new TicTacToeMove(0,0);
            playerMove = view.getNextMove(model.isXTurn());
            int row = playerMove.getRow();
            int col = playerMove.getCol();
            boolean validMove = model.makeMark(row,col);
            
            if(validMove == false){
                view.showInputError();
            }
        }
        
        /* After the game is over, show the final board and the winner */

        view.showBoard(model.toString());

        view.showResult(model.getResult().toString());
        
    }

}
