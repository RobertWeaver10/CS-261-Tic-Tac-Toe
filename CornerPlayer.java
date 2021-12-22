public class CornerPlayer extends RandomPlayer{

    /**
     * player that extends from the random player. this player will go for corners first, then randomly
     * pick from remaining spots after the corners are filled
     * @param symbol the player's symbol (x, o)
     * @param name the name of the player
     */
    public CornerPlayer(int symbol, String name){
        super(symbol, name);
    }

    /**
     * method that has the computer pick a corner if one is available and if not then picks a random available slot
     * @param theBoard the gameboard so that the user can mark a place on the board
     */
    @Override
    public void makeMove(Board theBoard){
        int col = 0;
        int row = 0;
        if(theBoard.isOpen(0,0) || theBoard.isOpen(0,2) || theBoard.isOpen(2,0) ||
        theBoard.isOpen(2,2)){ //check to see if a corner spot is open
            while(!theBoard.isOpen(col, row)){ //runs until an open corner spot is found
                col += 2;
                if(col > 2){
                    col = 0;
                    row += 2;
                }
            }
            theBoard.fillPosition(col,row,symbol);
        }

        else{
            super.makeMove(theBoard); //if a corner spot is not open then it calls the client class method to randomly
                                        //make a move
        }

    }

}
