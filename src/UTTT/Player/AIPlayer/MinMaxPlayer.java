package UTTT.Player.AIPlayer;

import UTTT.Board.Board;
import UTTT.Player.Player;

import java.util.ArrayList;

abstract class MinMaxPlayer extends Player {
    protected static Double infinity = 1000.0;

    private int minMaxDepth;

    public MinMaxPlayer(int mmDepth, String playerType) {
        super(playerType);
        this.minMaxDepth = mmDepth > 0 ? mmDepth : 1;
    }

    @Override
    public int[] getMove(Board board) {
        return this.max(board.deepCopy(),this.minMaxDepth).getMove();
    }

    private MoveValueCombo max(Board board, int depth) {
        ArrayList<int[]> moves = board.getAvailableMoves();
        int moveCount = moves.size();
        MoveValueCombo maxMVC = new MoveValueCombo(null,null);
        for (int i = 0; i < moveCount; i++) {
            Board newBoard = board.deepCopy();
            int[] move = moves.get(i);
            newBoard.updateSlot(move,this.getPlayerCode());
            MoveValueCombo mvc = new MoveValueCombo(move,0.0);
            if (depth == 1) {
                mvc.setValue(evaluateBoard(newBoard, true));
            } else {
                mvc.setValue(this.min(newBoard, depth - 1).getValue());
            }
            if (mvc.getValue() == infinity) {
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
        int moveCount = moves.size();
        MoveValueCombo minMVC = new MoveValueCombo(null,null);
        for (int i = 0; i < moveCount; i++) {
            Board newBoard = board.deepCopy();
            int[] move = moves.get(i);
            newBoard.updateSlot(move,this.getPlayerCode());
            MoveValueCombo mvc = new MoveValueCombo(move,0.0);
            if (depth == 1) {
                mvc.setValue(evaluateBoard(newBoard, false));
            } else {
                mvc.setValue(this.max(newBoard, depth - 1).getValue());
            }
            if (mvc.getValue() == -infinity) {
                return mvc;
            }
            if (minMVC.isGreaterThan(mvc))
                minMVC = mvc;
        }
        return minMVC;
    }

    public abstract Double evaluateBoard(Board board, Boolean thisPlayersTurn);
}
