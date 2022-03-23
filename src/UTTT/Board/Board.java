package UTTT.Board;

public abstract class Board {
    protected String boardType;
    protected int winner;

//    public abstract String displayBoard();

    public int getWinner() {
        return this.winner;
    }

    public void setWinner(int wpc) {
        this.winner = wpc;
    }

    public abstract Board deepCopy();
}
