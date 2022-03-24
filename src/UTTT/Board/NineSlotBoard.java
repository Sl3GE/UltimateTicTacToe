package UTTT.Board;

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

    public boolean setSlot(int slot, int p) {
        if (this.winner != 0 || this.slots[slot] != 0)
            return false;
        this.slots[slot] = p;
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
            if (this.slots[mult] != 0 && this.slots[mult] == this.slots[mult+1] && this.slots[mult+1] == this.slots[mult+2])
                return slots[mult];
            if (this.slots[i] != 0 && slots[i] == this.slots[i+3] && this.slots[i+3] == this.slots[i+6])
                return slots[i];
        }
        if (this.slots[4] != 0) {
            if (this.slots[0] == this.slots[4] && this.slots[4] == this.slots[8])
                return slots[0];
            if (this.slots[2] == this.slots[4] && this.slots[4] == this.slots[6])
                return this.slots[0];
        }
        return 0;
    }

    @Override
    public NineSlotBoard deepCopy() {
        return new NineSlotBoard(this.slots.clone(),this.winner);
    }
}
