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
    protected int moveCount;

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
        this.board.updateSlot(move,playerCode);
        this.moveCount += 1;
    }

    public void display() {
        System.out.println("Move: " + moveCount);
        System.out.println("Player with next move:");
        this.currentPlayer.display();
        System.out.println("Board:");
        this.board.displayBoard();
    }

    public void nextMove() {
        int[] move = this.currentPlayer.getMove(this.board);
        if (move != null)
            this.makeMove(move, this.currentPlayer.getPlayerCode());
        if (this.currentPlayer == player1)
            this.currentPlayer = player2;
        else
            this.currentPlayer = player1;
    }

    public boolean isGameOver() {
        return this.board.isBoardComplete();
    }

    public void displayWinner() {
        int wpc = this.board.findBoardWinner();
        if (wpc == 0)
            return;
        System.out.println("\nWinner of the game is:");
        if (wpc == this.player1.getPlayerCode())
            this.player1.display();
        else
            this.player2.display();
    }
}
