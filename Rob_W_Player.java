
public class Rob_W_Player extends Player{


    /**
     * class that makes a smarter player than the corner, random, and next open players
     *  * the make move method first picks the center if available and if not then moves to
     *  * corners and after than then fills in remaining spaces to hopefully win or draw
     * @param symbol the symbol of the player (x, o)
     * @param name the name of the player
     */
    public Rob_W_Player(int symbol, String name){
        super(symbol,name);
    }

    /**
     * method that inputs the board and looks to see if the opponent has 2
     * marks in a single row/column and if the third slot is filled or not.
     * if opponent has 2 in a row returns the coordinates of the open slot
     * else returns null
     * @param theBoard the gameboard that is being used so that the computer can check if there are 2 in a row
     *                 for the opponent
     * @return boolean returns true if there are 2 in a row and the last slot is empty, false if not
     */
    private boolean TwoInARow (Board theBoard){
        boolean twoinarow = true;
        int opponentCount = 0;
        int blankCount = 0;
        for (int i = 0; i < 3; i++){ //check every column
            for (int j = 0; j < 3; j++){ //to go through each row in the column
                if(theBoard.getContents(i,j) != symbol && theBoard.getContents(i, j) != Board.BLANK){
                    opponentCount++;
                }
                if (theBoard.getContents(i,j) == Board.BLANK){
                    blankCount++;
                }
                if (opponentCount == 2 && blankCount == 1){
                    return twoinarow; //only will return true if the opponent has 2 and the last slot is blank
                }
            }
            opponentCount = 0; //reset the counters to check if there's 2 in a row in the rows
            blankCount = 0;
        }

        for (int row = 0; row < 3; row++){ //check every row
            for (int col = 0; col < 3; col++){ //go through every column for each row
                if (theBoard.getContents(col, row) != symbol && theBoard.getContents(col, row) != Board.BLANK){
                    opponentCount++;
                }
                if (theBoard.getContents(col, row) == Board.BLANK){
                    blankCount++;
                }
                if (opponentCount == 2 && blankCount == 1){
                    return twoinarow;
                }
            }
        }

        //make it this far means opponent either has 2 diagonally or doesn't have 2 in a row
        if (theBoard.getContents(0,0) != symbol && theBoard.getContents(0,0) != Board.BLANK &&
                theBoard.getContents(1,1) != symbol && theBoard.getContents(1,1) != Board.BLANK
                && theBoard.getContents(2,2) == Board.BLANK){
            return twoinarow; //checks the corners and returns true if the opponent has 2 in a row diagonally and the
                              // remaining spot is empty
        }
        if (theBoard.getContents(2,2) != symbol && theBoard.getContents(2,2) != Board.BLANK &&
                theBoard.getContents(1,1) != symbol && theBoard.getContents(1,1) != Board.BLANK
                && theBoard.getContents(0,0) == Board.BLANK){
            return twoinarow;
        }
        if (theBoard.getContents(0,0) != symbol && theBoard.getContents(0,0) != Board.BLANK &&
                theBoard.getContents(2,2) != symbol && theBoard.getContents(2,2) != Board.BLANK
                && theBoard.getContents(1,1) == Board.BLANK){
            return twoinarow;
        }
        if (theBoard.getContents(2,0) != symbol && theBoard.getContents(2,0) != Board.BLANK &&
                theBoard.getContents(1,1) != symbol && theBoard.getContents(1,1) != Board.BLANK
                && theBoard.getContents(0,2) == Board.BLANK){
            return twoinarow;
        }
        if (theBoard.getContents(0,2) != symbol && theBoard.getContents(0,2) != Board.BLANK &&
                theBoard.getContents(1,1) != symbol && theBoard.getContents(1,1) != Board.BLANK
                && theBoard.getContents(2,0) == Board.BLANK){
            return twoinarow;
        }
        if (theBoard.getContents(2,0) != symbol && theBoard.getContents(2,0) != Board.BLANK &&
                theBoard.getContents(0,2) != symbol && theBoard.getContents(0,2) != Board.BLANK
                && theBoard.getContents(1,1) == Board.BLANK){
            return twoinarow;
        }
        return false; //made it through every check and didn't find 2 in a row
    }
    //was going to try using this in my make move method but I wasn't able to figure out how I was going to use it
    //and then other work came up and so I never got around to implementing it into the makeMove() method.



    /**
     * method that has me win every game or at least draw if I can't win
     * @param theBoard inputs the board that the game is being played on
     */
    @Override
    public void makeMove (Board theBoard) {

        if (theBoard.getWinner() == -1 && !theBoard.boardFilled()) { //only runs if there isn't a winner and the board is not full
            if (theBoard.isOpen(1, 1)) { //if the center is open then we want to take it
                theBoard.fillPosition(1, 1, symbol);
            } else { //if the center is not open then we want to look to the corners to see if they are open
                if (theBoard.isOpen(0, 0) || theBoard.isOpen(0, 2) || theBoard.isOpen(2, 0) ||
                        theBoard.isOpen(2, 2)) {
                    int col = 0;
                    int row = 0;
                    while (!theBoard.isOpen(col, row)) { //loop through the corners until an open slot is found
                        col += 2;
                        if (col > 2) {
                            col = 0;
                            row += 2;
                        }
                    }
                    theBoard.fillPosition(col, row, symbol);
                } else {
                    if (theBoard.getContents(0, 1) == Board.BLANK) { //if the center and the corners are
                        theBoard.fillPosition(0, 1, symbol); //not open then we will check the remaining 4 spots place
                    }
                    if (theBoard.getContents(1, 2) == Board.BLANK) {
                        theBoard.fillPosition(1, 2, symbol);
                    }
                    if (theBoard.getContents(2, 1) == Board.BLANK) {
                        theBoard.fillPosition(2, 1, symbol);
                    }
                    if (theBoard.getContents(1, 0) == Board.BLANK) {
                        theBoard.fillPosition(1, 0, symbol);
                    }
                }
            }
        }
    }
}
