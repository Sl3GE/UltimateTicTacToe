package UTTT.Player.AIPlayer;

import UTTT.Board.Board;
import UTTT.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;

abstract class MinMaxPlayer extends Player {
    protected static Double infinity = 1000.0;

    private final int minMaxDepth;

    public MinMaxPlayer(int mmDepth, String playerType) {
        super(playerType);
        this.minMaxDepth = mmDepth > 0 ? mmDepth : 1;
    }

    @Override
    public int[] getMove(Board board) {
        int[] move = this.max(board.deepCopy(),this.minMaxDepth).getMove();
        if (move == null) {
            System.out.println(Arrays.toString(new Throwable().getStackTrace()));
            System.exit(1);
        }
        return move;
    }

    private MoveValueCombo max(Board board, int depth) {
        ArrayList<int[]> moves = board.getAvailableMoves();
        MoveValueCombo maxMVC = new MoveValueCombo(null,null);
        for (int[] move : moves) {
            Board newBoard = board.deepCopy();
            newBoard.updateSlot(move, this.getPlayerCode());
            MoveValueCombo mvc = new MoveValueCombo(move, 0.0);
            if (depth == 1) {
                mvc.setValue(evaluateBoard(newBoard, true));
            } else {
                mvc.setValue(this.min(newBoard, depth - 1).getValue());
            }
            Double moveValue = mvc.getValue();
            if (moveValue != null && moveValue.equals(infinity)) {
                return mvc;
            }
            if (mvc.isGreaterThan(maxMVC))
                maxMVC = mvc;
        }
        return maxMVC;
    }

    // TODO: Add Alpha Beta Pruning
    private MoveValueCombo min(Board board, int depth) {
        ArrayList<int[]> moves = board.getAvailableMoves();
        MoveValueCombo minMVC = new MoveValueCombo(null,null);
        for (int[] move : moves) {
            Board newBoard = board.deepCopy();
            newBoard.updateSlot(move, this.getPlayerCode());
            MoveValueCombo mvc = new MoveValueCombo(move, 0.0);
            if (depth == 1) {
                mvc.setValue(evaluateBoard(newBoard, false));
            } else {
                mvc.setValue(this.max(newBoard, depth - 1).getValue());
            }
            Double moveValue = mvc.getValue();
            if (moveValue != null && moveValue.equals(-infinity)) {
                return mvc;
            }
            if (mvc.isLessThan(minMVC))
                minMVC = mvc;
        }
        return minMVC;
    }

    public abstract Double evaluateBoard(Board board, Boolean thisPlayersTurn);
}
