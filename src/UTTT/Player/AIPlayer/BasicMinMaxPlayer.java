package UTTT.Player.AIPlayer;

import UTTT.Board.Board;

public class BasicMinMaxPlayer extends MinMaxPlayer {
    public BasicMinMaxPlayer(int pc, int mmDepth) {
        super(pc, mmDepth);
    }

    @Override
    public Double evaluateBoard(Board board)  {
        int winner = board.findBoardWinner();
        if (winner == this.playerCode)
            return infinity;
        if (winner != 0)
            return -infinity;
        return 0.0;
    }
}
