package UTTT.Player;

import UTTT.Board.Board;

import java.util.ArrayList;

public abstract class Player {

    protected int playerCode;
    protected String name;

    public Player(int pc) {
        this.playerCode = pc;
    }

    public int getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(int playerCode) {
        this.playerCode = playerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract  int[] getMove(Board board, ArrayList<int[]> moves);
}
