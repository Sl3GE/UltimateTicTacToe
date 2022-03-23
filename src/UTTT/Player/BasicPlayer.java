package UTTT.Player;

import UTTT.Board.Board;

import java.util.ArrayList;

public class BasicPlayer extends Player {

    public BasicPlayer(int pc) {
        super(pc);
    }

    @Override
    public int[] getMove(Board board, ArrayList<int[]> moves) {
        return moves.get(0);
    }
}
