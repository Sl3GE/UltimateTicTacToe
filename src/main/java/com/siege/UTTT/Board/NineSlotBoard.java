package com.siege.UTTT.Board;

import java.util.ArrayList;

public class NineSlotBoard extends Board {
    private int[] slots;

    public NineSlotBoard() {
        this(new int[]{0,0,0,0,0,0,0,0,0},0);
    }

    public NineSlotBoard(int[] s, int w) {
        this.slots = s;
        this.winner = w;
        this.boardType = "NineSlotBoard";
    }

    @Override
    public void displayBoard() {
        String result = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result += this.slots[(i*3) + j] + "|";
            }
            result += this.slots[2 + (i*3)] + "\n-+-+-\n";
        }
        for (int j = 0; j < 2; j++) {
            result += this.slots[6 + j] + "|";
        }
        result += this.slots[8];
        System.out.println(result);
    }

    public int[] getSlots() {
        return this.slots;
    }

    public ArrayList<int[]> getAvailableMoves() {
        if (this.winner != 0)
            return new ArrayList<>();
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 0; i < this.slots.length; i++) {
            if (this.slots[i] == 0)
                moves.add(new int[]{i});
        }
        return moves;
    }

    @Override
    public boolean updateSlot(int[] move, int playerCode) {
        if (this.winner != 0 || move.length != 1 || this.slots[move[0]] != 0)
            return false;
        this.slots[move[0]] = playerCode;
        int w = findBoardWinner();
        if (w != 0)
            this.winner = w;
        return true;
    }

    @Override
    public int findBoardWinner() {
        if (this.winner != 0)
            return this.winner;
        for (int i = 0; i < 3; i++) {
            int mult = i*3;
            if (this.slots[mult] != 0 && this.slots[mult] == this.slots[mult+1] && this.slots[mult+1] == this.slots[mult+2]) {
                this.winner = slots[mult];
                return this.winner;
            }
            if (this.slots[i] != 0 && slots[i] == this.slots[i+3] && this.slots[i+3] == this.slots[i+6]) {
                this.winner = slots[i];
                return this.winner;
            }
        }
        if (this.slots[4] != 0) {
            if ((this.slots[0] == this.slots[4] && this.slots[4] == this.slots[8]) || (this.slots[2] == this.slots[4] && this.slots[4] == this.slots[6])) {
                this.winner = slots[4];
                return this.winner;
            }
        }
        return 0;
    }

    @Override
    public boolean isBoardComplete() {
        if (this.findBoardWinner() != 0)
            return true;
        for (int slot : this.slots) {
            if (slot == 0)
                return false;
        }
        return true;
    }

    @Override
    public NineSlotBoard deepCopy() {
        return new NineSlotBoard(this.slots.clone(),this.winner);
    }
}
