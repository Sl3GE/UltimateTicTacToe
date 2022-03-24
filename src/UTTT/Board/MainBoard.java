package UTTT.Board;

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

//    public NineSlotBoard[] getSlots() {
//        return this.slots;
//    }
//
//    public NineSlotBoard getSlot(int slot) {
//        return this.slots[slot];
//    }

    /**
     * Requires more work!!
     */
    @Override
    public void displayBoard() {
        System.out.println("Add a display!!");
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
        if (this.winner != 0 || moveLength > 2 || moveLength == 0)
            return false;
        if (moveLength == 2) {
            if (this.activeSlot != -1 && this.activeSlot != move[0])
                return false;
        }
        if (moveLength == 1 && this.activeSlot != -1)
            return false;
        boolean result = this.slots[this.activeSlot].updateSlot(new int[]{move[-1]},playerCode);
        if (result) {
            if (moveLength == 2)
                this.activeSlot = move[1];
            else
                this.activeSlot = move[0];
        }
        return result;
    }

    @Override
    public MainBoard deepCopy() {
        NineSlotBoard[] newSlots = new NineSlotBoard[9];
        for (int i = 0; i < 9; i++) {
            newSlots[i] = this.slots[i].deepCopy();
        }
        return new MainBoard(newSlots,this.winner);
    }
}
