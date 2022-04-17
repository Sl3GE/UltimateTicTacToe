package UTTT.Board;

import java.util.ArrayList;

public abstract class Board {
    protected String boardType;
    protected int winner;

    public abstract void displayBoard();

    public abstract Board deepCopy();

    /**
     *
     * @return int value representing the playerCode of the winner of the board. If there is no winner, it returns "0".
     */
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
