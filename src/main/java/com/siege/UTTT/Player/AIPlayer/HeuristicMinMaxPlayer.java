package com.siege.UTTT.Player.AIPlayer;

import com.siege.UTTT.Board.Board;
import com.siege.UTTT.Board.MainBoard;
import com.siege.UTTT.Board.NineSlotBoard;

public class HeuristicMinMaxPlayer extends MinMaxPlayer {
    public HeuristicMinMaxPlayer(int mmDepth) {
        super(mmDepth, "HeuristicMinMaxPlayer");
    }

    @Override
    public Double evaluateBoard(Board board, Boolean thisPlayersTurn) {
        int winner = board.findBoardWinner();
        if (winner == this.playerCode)
            return infinity;
        if (winner != 0)
            return -infinity;

        /**
         * Possible ideas for future:
         * 1. (Necessary) add co-efficients
         * 3. (Should) genetic evolution to determine good co-efficients
         * 2. (hard maybe) neural-network combined with genetic evolution to find best formula
         */
        Double mainBoardNextMoveWinCount = this.getMainBoardNextMoveWinCount(board, thisPlayersTurn) - (2 * this.getMainBoardNextMoveWinCount(board, !thisPlayersTurn));
        Double innerBoardNextMoveWinCount = this.getInnerBoardNextMoveWinCount(board, thisPlayersTurn) - this.getInnerBoardNextMoveWinCount(board, !thisPlayersTurn);
        return (3*this.slotWinDifferential(board)) + (2 * mainBoardNextMoveWinCount) + innerBoardNextMoveWinCount;
    }

    private Double slotWinDifferential(Board board) {
        Double differential = 0.0;
        if (board.getBoardType().equals("NineSlotBoard")) {
            for (int slot : ((NineSlotBoard) board).getSlots()) {
                if (slot == this.playerCode) {
                    differential += 1;
                } else if (slot != 0) {
                    differential -= 1;
                }
            }
        } else {
            for (NineSlotBoard slot : ((MainBoard) board).getSlots()) {
                int slotWinner = slot.findBoardWinner(); // Currently, does not evaluate the board if no winner is present. Change to ".findBoardWinner()" to evaluate it, but cost in computation increases.
                if (slotWinner == this.playerCode) {
                    differential += 1;
                } else if (slotWinner != 0) {
                    differential -= 1;
                }
            }
        }
        return differential;
    }

    /* Heuristic Ideas
        1. count how many "3 in a row"s are possibly left for each player.
        2. count how many pairs of twos with a third empty slot exist for each player.
        3. some kind of heuristic to control the active slot for your next move (based on multi-board depth understanding)
     */

    private Double getInnerBoardNextMoveWinCount(Board board, Boolean thisPlayersTurn) {
        Double count = 0.0;
        for (NineSlotBoard nineSlotBoard : ((MainBoard) board).getSlots()) {
            count += this.getMainBoardNextMoveWinCount(nineSlotBoard,thisPlayersTurn);
        }
        return count;
    }

    private Double getMainBoardNextMoveWinCount(Board board, Boolean thisPlayersTurn) {
        int[] slots;
        if (board.getBoardType().equals("NineSlotBoard")) {
            slots = ((NineSlotBoard) board).getSlots();
        } else {
            NineSlotBoard[] boardSlots = ((MainBoard) board).getSlots();
            slots = new int[]{0,0,0,0,0,0,0,0,0};
            for (int i = 0; i < 9; i++) {
                slots[i] = boardSlots[i].findBoardWinner();
            }
        }
        Double count = 0.0;
        for (int i = 0; i < 3; i++) {
            // Checking Rows
            int mult = i*3;
            int tempCountThisPlayer = 0;
            int tempCountOtherPlayer = 0;
            for (int j = 0; j < 3; j++) {
                int slot = slots[mult+j];
                if (slot == this.playerCode)
                    tempCountThisPlayer++;
                if (slot != 0)
                    tempCountOtherPlayer++;
            }
            if (thisPlayersTurn && tempCountThisPlayer == 2 && tempCountOtherPlayer == 0)
                count++;
            else if (!thisPlayersTurn && tempCountThisPlayer == 0 && tempCountOtherPlayer == 2)
                count--;
            // Checking Columns
            tempCountThisPlayer = 0;
            tempCountOtherPlayer = 0;
            for (int j = 0; j < 9; j=j+3) {
                int slot = slots[i+j];
                if (slot == this.playerCode)
                    tempCountThisPlayer++;
                else if (slot != 0)
                    tempCountOtherPlayer++;
            }
            if (thisPlayersTurn && tempCountThisPlayer == 2 && tempCountOtherPlayer == 0)
                count++;
            else if (!thisPlayersTurn && tempCountThisPlayer == 0 && tempCountOtherPlayer == 2)
                count--;
        }
        // Checking Diagonals
        int tempCountThisPlayer = 0;
        int tempCountOtherPlayer = 0;
        for (int j = 0; j < 12; j=j+4) {
            int slot = slots[j];
            if (slot == this.playerCode)
                tempCountThisPlayer++;
            else if (slot != 0)
                tempCountOtherPlayer++;
        }
        if (thisPlayersTurn && tempCountThisPlayer == 2 && tempCountOtherPlayer == 0)
            count++;
        else if (!thisPlayersTurn && tempCountThisPlayer == 0 && tempCountOtherPlayer == 2)
            count--;
        tempCountThisPlayer = 0;
        tempCountOtherPlayer = 0;
        for (int j = 3; j < 9; j=j+2) {
            int slot = slots[j];
            if (slot == this.playerCode)
                tempCountThisPlayer++;
            else if (slot != 0)
                tempCountOtherPlayer++;
        }
        if (thisPlayersTurn && tempCountThisPlayer == 2 && tempCountOtherPlayer == 0)
            count++;
        else if (!thisPlayersTurn && tempCountThisPlayer == 0 && tempCountOtherPlayer == 2)
            count--;

        return count;
    }
}
