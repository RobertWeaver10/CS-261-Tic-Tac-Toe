import java.util.Random;

/**
 * class that extends from the regular player class. Objects of this class have a different makeMove() method
 * from the regular player and the nextOpenPlayer. Objects of RandomPlayer will randomly select a spot on the
 * board to pick for the whole game.
 */
public class RandomPlayer extends Player {
    private int col;
    private int row;
    Random rng;

    public RandomPlayer (int symbol, String name){
        super(symbol, name);
         rng = new Random(); //call the client class constructor and then instantiate a new Random
    }

    /**
     * Override method that now has the computer pick a random open slot on the board
     *
     * @param theBoard inputs a board instance
     *
     */
    @Override
    public void makeMove(Board theBoard){
        if(theBoard.getWinner() == -1) { //if no one has won the game yet

            while(!theBoard.isOpen(this.col, this.row)){ //run until an open slot is found
                this.row = rng.nextInt(3); //want the random number to be between 0-2 inclusive so bound = 3
                this.col = rng.nextInt(3);
            }

            theBoard.fillPosition(this.col, this.row, symbol);

            if(theBoard.getWinner() != -1){ //if someone has won the game with the move they just did then print a win message
                System.out.println("player " + theBoard.getWinner() + " has won the game");
            }
        }

        else { //only runs if someone won the game before we had the chance to place a marker
            System.out.println("player " + theBoard.getWinner() + " has won the game");
        }
    }
}
