public class NextOpenPlayer extends Player{

    private int row = 2; //starting position so that it fills from the top left corner to the bottom right one
    private int col = 0; //""

    public NextOpenPlayer(int symbol, String name){
        super(symbol, name);
    }

    /**
     * override method that has the computer take the next open slot starting in the top left corner
     */
    @Override
    public void makeMove(Board theBoard){
        if(theBoard.getWinner() == -1) {
            while (!theBoard.isOpen(this.col, this.row)) { //runs until coordinates of an open slot is found
                this.col++;
                if (this.col > 2) {
                    this.col = 0;
                    this.row--;
                }
            }

            theBoard.fillPosition(this.col, this.row, symbol);
        }

        else{
            System.out.println("Player " + theBoard.getWinner() + " has won the game");
        }
    }

}
