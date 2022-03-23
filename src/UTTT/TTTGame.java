package UTTT;

import UTTT.Board.Board;
import UTTT.Board.NineSlotBoard;
import UTTT.Player.Player;

import java.util.ArrayList;

public class TTTGame {
    protected Board board;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    public TTTGame(Player p1, Player p2) {
        this(p1,p2,new NineSlotBoard());
    }

    public TTTGame(Player p1, Player p2, Board b) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = p1;
        this.board = b;
    }

    private void makeMove(int[] move, int playerCode) {
        if (move.length == 1)
            ((NineSlotBoard) this.board).setSlot(move[0],playerCode);
    }

    private ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        int[] slots = ((NineSlotBoard) this.board).getSlots();
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == 0)
                moves.add(new int[]{i});
        }
        return moves;
    }

    public void display() {
        ((NineSlotBoard) this.board).displayBoard();
    }

    public void nextMove() {
        ArrayList<int[]> moves = this.getAvailableMoves();
        if (moves.size() > 0)
            this.makeMove(this.currentPlayer.getMove(this.board,moves), this.currentPlayer.getPlayerCode());
        if (this.currentPlayer == player1)
            this.currentPlayer = player2;
        else
            this.currentPlayer = player1;
    }

    public boolean isGameOver() {
        if (((NineSlotBoard) this.board).findBoardWinner() != 0)
            return true;
        for (int slot : ((NineSlotBoard) this.board).getSlots()) {
            if (slot == 0)
                return false;
        }
        return true;
    }
}
