public class Tournament {

    Board gameBoard; //needs to remember the board which the game is being played on

    /**
     * creates a new board so that two players can play tic tac toe
     */
    public Tournament(){
         this.gameBoard = new Board();
    }

    /**
     * method that takes in two players and has them play tic tac toe against eachother
     * @param p1 the player that gets to go first and will be using the x symbol
     * @param p2 the player that goes second and uses the o symbol
     */
    public void playGame(Player p1, Player p2){
        p1.setSymbol(Board.X);
        p2.setSymbol(Board.O);

        while(this.gameBoard.getWinner() == -1 && !this.gameBoard.boardFilled()){ //runs while there's no winner and the board has space
            p1.makeMove(this.gameBoard);
            p2.makeMove(this.gameBoard);
            System.out.println(this.gameBoard); //print the board after each set of moves to see the progression of the game
        }

        if (this.gameBoard.getWinner() == -1){ // if it ends in a draw then both players mourn
            System.out.println("P1: " + p1.mourn());
            System.out.println("P2: " + p2.mourn());
        }

        else if (this.gameBoard.getWinner() == Board.X){ //if player 1 wins then they celebrate and p2 mourns
            System.out.println("P1: " + p1.celebrate());
            System.out.println("P2: " + p2.mourn());
        }

        else{ //if player 1 loses then p1 will mourn and p2 will celebrate the victory
            System.out.println("P1: " + p1.mourn());
            System.out.println("P2: " + p2.celebrate());
        }
    }

}
