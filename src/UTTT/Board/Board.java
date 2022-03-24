package UTTT.Board;

import java.util.ArrayList;

public abstract class Board {
    protected String boardType;
    protected int winner;

    public abstract void displayBoard();

    public abstract Board deepCopy();

    public abstract int findBoardWinner();

    public abstract boolean updateSlot(int[] move, int playerCode);

    public abstract boolean isBoardComplete();

    public abstract ArrayList<int[]> getAvailableMoves();

    public int getWinner() {
        return this.winner;
    }

    public void setWinner(int wpc) {
        this.winner = wpc;
    }
}
