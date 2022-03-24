package UTTT.Player;

import UTTT.Board.Board;

import java.util.ArrayList;
import java.util.Scanner;

public class STDInputPlayer extends Player {

    public STDInputPlayer(int pc) {
        super(pc);
    }

    @Override
    public int[] getMove(Board board, ArrayList<int[]> moves) {
        System.out.println("What is your next move? (provide the integers for the slot numbers in an array like \"2\" or \"3,5\")");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            String[] splitInput = input.split(",");
            int inputSize = splitInput.length;
            int[] move = new int[inputSize];
            for (int i = 0; i < inputSize; i++) {
                move[i] = Integer.parseInt(splitInput[i]);
            }
            return move;
        } catch (Exception e) {
            return new int[]{0};
        }

    }
}
