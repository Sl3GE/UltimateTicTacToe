package com.siege.UTTT.Player.AIPlayer;

public class MoveValueCombo {
    private int[] move;
    private Double value;

    public MoveValueCombo(int[] m, Double v) {
        this.move = m;
        this.value = v;
    }

    public int[] getMove() {
        return move;
    }

    public void setMove(int[] newMove) {
        this.move = newMove;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isGreaterThan(MoveValueCombo mvc2) {
        if (mvc2.getValue() == null) {
            return true;
        }
        if (this.getValue() == null) {
            return false;
        }
        return this.getValue() >= mvc2.getValue();
    }

    public boolean isLessThan(MoveValueCombo mvc2) {
        if (mvc2.getValue() == null) {
            return true;
        }
        if (this.getValue() == null) {
            return false;
        }
        return this.getValue() <= mvc2.getValue();
    }
}
