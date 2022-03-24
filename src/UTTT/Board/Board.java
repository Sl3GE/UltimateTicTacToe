package UTTT.Board;

public abstract class Board {
    protected String boardType;
    protected int winner;

    public abstract void displayBoard();

    public abstract Board deepCopy();

    public abstract int findBoardWinner();

    public abstract boolean updateSlot(int[] move, int playerCode);

    public int getWinner() {
        return this.winner;
    }

    public void setWinner(int wpc) {
        this.winner = wpc;
    }
}
