package UTTT.Player;

import UTTT.Board.Board;

import java.util.ArrayList;

public class BasicPlayer extends Player {

    public BasicPlayer(int pc) {
        super(pc);
        this.playerType = "BasicPlayer";
    }

    @Override
    public int[] getMove(Board board) {
        ArrayList<int[]> moves = board.getAvailableMoves();
        if (moves.size() == 0)
            return null;
        return moves.get(0);
    }
}
