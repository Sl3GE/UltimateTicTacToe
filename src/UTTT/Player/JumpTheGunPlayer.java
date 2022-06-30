package UTTT.Player;

import UTTT.Board.Board;

import java.util.ArrayList;

public class JumpTheGunPlayer extends Player {

    public JumpTheGunPlayer() {
        super("JumpTheGunPlayer");
    }

    @Override
    public int[] getMove(Board board) {
        ArrayList<int[]> moves = board.getAvailableMoves();
        if (moves.size() == 0)
            return null;
        return moves.get(0);
    }
}
