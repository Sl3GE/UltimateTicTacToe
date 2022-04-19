package UTTT.Player.AIPlayer;

import UTTT.Board.Board;
import UTTT.Board.MainBoard;
import UTTT.Board.NineSlotBoard;

public class HeuristicMinMaxPlayer extends MinMaxPlayer {
    public HeuristicMinMaxPlayer(int pc, int mmDepth) {
        super(pc, mmDepth);
    }

    @Override
    public Double evaluateBoard(Board board) {
        int winner = board.findBoardWinner();
        if (winner == this.playerCode)
            return infinity;
        if (winner != 0)
            return -infinity;
        return this.slotWinDifferential(board);
    }

    private Double slotWinDifferential(Board board) {
        Double differential = 0.0;
        for (NineSlotBoard slot : ((MainBoard) board).getSlots()) {
            int slotWinner = slot.getWinner(); // Currently, does not evaluate the board if not winner is present. Change to ".findBoardWinner()" to evaluate it, but cost in computation increases.
            if (slotWinner == this.playerCode) {
                differential += 1;
            } else if (slotWinner != 0) {
                differential -= 1;
            }
        }
        return differential;
    }

    /* Heuristic Ideas
        1. count how many "3 in a row"s are possible left for each player.
        2. count how many pairs of twos with a third empty slot exist for each player.
        3. some kind of heuristic to control the active slot for your next move (based on multi-board depth understanding)
     */
}
