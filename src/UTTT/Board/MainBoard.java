package UTTT.Board;

import java.util.ArrayList;

public class MainBoard extends Board {
    private NineSlotBoard[] slots;
    private int activeSlot;

    public MainBoard() {
        this.slots = new NineSlotBoard[9];
        for (int i = 0; i < 9; i++) {
            this.slots[i] = new NineSlotBoard();
        }
        this.winner = 0;
        this.boardType = "MainBoard";
    }

    public MainBoard(NineSlotBoard[] s, int w) {
        this.slots = s;
        this.winner = w;
        this.boardType = "MainBoard";
    }

    @Override
    public void displayBoard() {
        for (int i = 0; i < 2; i++) {
            printRowOfBoards(i);
            System.out.println("=======================");
        }
        printRowOfBoards(2);
    }
    private void printRowOfBoards(int i) {
        int[] board1Slots = this.slots[i*3].getSlots();
        int[] board2Slots = this.slots[i*3 + 1].getSlots();
        int[] board3Slots = this.slots[i*3 + 2].getSlots();
        for (int k = 0; k < 2; k++) {
            printSingleRowOfRowOfBoards(k,board1Slots,board2Slots,board3Slots);
            System.out.println("-+-+- || -+-+- || -+-+-");
        }
        printSingleRowOfRowOfBoards(2,board1Slots,board2Slots,board3Slots);
    }
    private void printSingleRowOfRowOfBoards(int k, int[] b1, int[] b2, int[] b3) {
        String result = "";
        for (int j = 0; j < 2; j++) {
            result += b1[(k * 3) + j] + "|";
        }
        result += b1[2 + (k*3)] + " || ";
        for (int j = 0; j < 2; j++) {
            result += b2[(k * 3) + j] + "|";
        }
        result += b2[2 + (k*3)] + " || ";
        for (int j = 0; j < 2; j++) {
            result += b3[(k * 3) + j] + "|";
        }
        result += b3[2 + (k*3)];
        System.out.println(result);
    }

    public NineSlotBoard[] getSlots() {
        return slots;
    }

    public void setActiveSlot(int slot) {
        this.activeSlot = slot;
    }

    /**
     * Requires more work!!
     */
    @Override
    public int findBoardWinner() {
        if (this.winner != 0)
            return this.winner;
        // Add caching system!!!!!!!!!!!!!!!!!!!!!!!!
        for (int i = 0; i < 3; i++) {
            int mult = i*3;
            if (this.slots[mult].findBoardWinner() != 0 && this.slots[mult].findBoardWinner() == this.slots[mult+1].findBoardWinner() && this.slots[mult+1].findBoardWinner() == this.slots[mult+2].findBoardWinner())
                return slots[mult].findBoardWinner();
            if (this.slots[i].findBoardWinner() != 0 && slots[i].findBoardWinner() == this.slots[i+3].findBoardWinner() && this.slots[i+3].findBoardWinner() == this.slots[i+6].findBoardWinner())
                return slots[i].findBoardWinner();
        }
        if (this.slots[4].findBoardWinner() != 0) {
            if (this.slots[0].findBoardWinner() == this.slots[4].findBoardWinner() && this.slots[4].findBoardWinner() == this.slots[8].findBoardWinner())
                return slots[0].findBoardWinner();
            if (this.slots[2].findBoardWinner() == this.slots[4].findBoardWinner() && this.slots[4].findBoardWinner() == this.slots[6].findBoardWinner())
                return this.slots[0].findBoardWinner();
        }
        return 0;
    }

    @Override
    public boolean updateSlot(int[] move, int playerCode) {
        int moveLength = move.length;
        int targetSlot = 0;
        if (this.winner != 0 || moveLength > 2 || moveLength == 0)
            return false;
        if (moveLength == 2) {
            if (this.activeSlot != -1 && this.activeSlot != move[0])
                return false;
            else
                targetSlot = move[0];
        }
        if (moveLength == 1) {
            if (this.activeSlot == -1)
                return false;
            else
                targetSlot = this.activeSlot;
        }
        boolean result = this.slots[targetSlot].updateSlot(new int[]{move[moveLength-1]},playerCode);
        if (result) {
            if (moveLength == 2)
                this.activeSlot = move[1];
            else
                this.activeSlot = move[0];
            if (this.slots[this.activeSlot].isBoardComplete())
                this.activeSlot = -1;
        }
        return result;
    }

    @Override
    public boolean isBoardComplete() {
        if (this.findBoardWinner() != 0)
            return true;
        for (NineSlotBoard slot : this.slots) {
            if (!slot.isBoardComplete())
                return false;
        }
        return true;
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        if (this.winner != 0)
            return new ArrayList<>();
        if (this.activeSlot != -1) {
            ArrayList<int[]> slotMoves = this.slots[this.activeSlot].getAvailableMoves();
            for (int i = 0; i < slotMoves.size(); i++) {
                slotMoves.set(i, new int[]{this.activeSlot, slotMoves.get(i)[0]});
            }
            return slotMoves;
        } else {
            ArrayList<int[]> slotMoves = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                ArrayList<int[]> tempSlotMoves = this.slots[i].getAvailableMoves();
                for (int j = 0; j < tempSlotMoves.size(); j++) {
                    tempSlotMoves.set(j, new int[]{i, tempSlotMoves.get(j)[0]});
                }
                slotMoves.addAll(tempSlotMoves);
            }
            return slotMoves;
        }
    }

    @Override
    public MainBoard deepCopy() {
        NineSlotBoard[] newSlots = new NineSlotBoard[9];
        for (int i = 0; i < 9; i++) {
            newSlots[i] = this.slots[i].deepCopy();
        }
        MainBoard newBoard = new MainBoard(newSlots,this.winner);
        newBoard.setActiveSlot(this.activeSlot);
        return newBoard;
    }
}
