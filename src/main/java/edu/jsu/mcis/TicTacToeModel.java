package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        for(int row = 0; row < width; ++row){
            for(int col = 0; col < width; ++col){
                board[row][col] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        

        if(isValidSquare(row,col) == true && isSquareMarked(row,col) == false){
            if(xTurn == true){
                board[row][col] = Mark.X;
                xTurn = false;
            }
            else{
                board[row][col] = Mark.O;
                xTurn = true;
            }
        
            return true;
        }

        else{
            return false;
        }
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        boolean squareValid = true;
        int width = getWidth();

        if(col >= width || row >= width || row < 0 || col < 0){
            squareValid = false;
        }

        return squareValid;

    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        boolean squareMark;

        if(board[row][col].toString().equals("-")){
            squareMark = false;
        }
        else{
            squareMark = true;
        }

        return squareMark;
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        Mark markGet;
        markGet = Mark.EMPTY;

        if(board[row][col].toString().equals("X")){
            markGet = Mark.X;
        }
        else if(board[row][col].toString().equals("O")){
            markGet = Mark.O;
        }
        else if(board[row][col].toString().equals("-")){
            markGet = Mark.EMPTY; 
        }

        return markGet;
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */

        Result gameResult;

        if(isMarkWin(Mark.X) == true){
            gameResult = Result.X;
        }
        else if(isMarkWin(Mark.O) == true){
            gameResult = Result.O;
        }
        else if(isTie() == true){
            gameResult = Result.TIE;
        }
        else{
            gameResult = Result.NONE;
        }

        

        return gameResult; 
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
           int boardWidth = 0;
           int markCount = 0;
           int col = 0;
           int row = 0;
           boolean winCheck = false;

           boardWidth = getWidth();

        /*determines if a row is*/
            if(winCheck != true){
                for(row = 0; row < boardWidth; ++row){
                    for(col = 0; col < boardWidth; ++col){
                        if(getMark(row,col).toString().equals(mark.toString())){
                            markCount++;
                        }
                    }
                    if(markCount == boardWidth){
                        winCheck = true;
                    }
                    markCount = 0;
                }
            }

            if(winCheck != true){
                for(col = 0; col < boardWidth; ++col){
                    for(row = 0; row < boardWidth; ++row){
                        if(getMark(row,col).toString().equals(mark.toString())){
                            markCount++;
                        }
                    }
                    if(markCount == boardWidth){
                        winCheck = true;
                    }
                    markCount = 0;
                }
            }

            if(winCheck != true){
               for(row = 0; row < boardWidth; ++row){
                    col = row;
                    if(getMark(row,col).toString().equals(mark.toString())){
                        markCount++;
                    }
                }
                if(markCount == boardWidth){
                    winCheck = true;
                }
                markCount = 0;
            }

            if(winCheck != true){
                col = boardWidth - 1;
                for(row = 0; row < boardWidth; ++row){
                    if(getMark(row,col).toString().equals(mark.toString())){
                        markCount++;
                    }
                    col = col - 1;
                }
                if(markCount == boardWidth){
                    winCheck = true;
                }
                markCount = 0;
            }

        return winCheck;

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        boolean tieBool = true;
        int boardWidth = 0;

        boardWidth = getWidth();

        for(int row = 0; row < boardWidth; ++row){
            for(int col = 0; col < boardWidth; ++col){
                if(isSquareMarked(row,col) == false){
                    tieBool = false;
                }
            }
        }
        return tieBool;
        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        int boardWidth = 0;

        boardWidth = getWidth();
        

        for(int row = 0; row< boardWidth+1; ++row){
            for(int col = 0; col< boardWidth; ++col){
                if(row == 0){
                    output.append(String.valueOf((col)));
                    if(col == (boardWidth - 1)){
                        output.append("\n");

                    }
                }
                
                else if(row != 0){
                    if(col == 0 ){
                        output.append(String.valueOf(row-1) + " ");
                    }
                    output.append(board[row - 1][col].toString());    
                    if(col == (boardWidth - 1)){
                        output.append("\n");

                    }
                }
            }
        }


        return output.toString();
        
    }
    
}
