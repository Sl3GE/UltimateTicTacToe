package UTTT.Player;

import UTTT.Board.Board;

import java.util.Objects;

public abstract class Player {

    protected int playerCode;
    protected String name;
    protected String playerType;

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

    public abstract  int[] getMove(Board board);

    public void display() {
        if (this.name != null) {
            System.out.println("Name: " + this.name);
        }
        System.out.println("Player Code: " + this.playerCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerCode == player.playerCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerCode);
    }
}
