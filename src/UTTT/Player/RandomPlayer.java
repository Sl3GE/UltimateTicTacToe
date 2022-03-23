package UTTT.Player;

import UTTT.Board.Board;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player {

    public RandomPlayer(int pc) {
        super(pc);
    }

    public int[] getMove(Board board, ArrayList<int[]> moves) {
        if (moves.size() == 0)
            return null;
        Random random = new Random();
        return moves.get(random.nextInt(moves.size()));
    }
}
