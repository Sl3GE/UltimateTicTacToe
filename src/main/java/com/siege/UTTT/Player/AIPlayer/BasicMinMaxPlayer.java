package main.java.com.siege.UTTT.Player.AIPlayer;

import main.java.com.siege.UTTT.Board.Board;

public class BasicMinMaxPlayer extends MinMaxPlayer {
    public BasicMinMaxPlayer(int mmDepth) {
        super(mmDepth, "BasicMinMaxPlayer");
    }

    @Override
    public Double evaluateBoard(Board board, Boolean thisPlayersTurn)  {
        int winner = board.findBoardWinner();
        if (winner == this.playerCode)
            return infinity;
        if (winner != 0)
            return -infinity;
        return 0.0;
    }
}
