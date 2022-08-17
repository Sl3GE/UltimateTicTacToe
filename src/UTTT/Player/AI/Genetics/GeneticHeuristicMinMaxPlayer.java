package UTTT.Player.AI.Genetics;

import UTTT.Board.Board;
import UTTT.Player.AI.HeuristicMinMaxPlayer;

public class GeneticHeuristicMinMaxPlayer extends HeuristicMinMaxPlayer {

//    public static final int defaultPlayerComplexityDepth = 1;

    private GeneticCode geneticCode;


    public GeneticHeuristicMinMaxPlayer(int mmDepth) {
        super(mmDepth,"GeneticHeuristicMinMaxPlayer");
        GeneticCode gc = new GeneticCode();
        gc.generateNewGeneSet(7,-5,5);
        this.geneticCode = gc;
    }

    public GeneticHeuristicMinMaxPlayer(int mmDepth, GeneticCode geneticCode) {
        super(mmDepth,"GeneticHeuristicMinMaxPlayer");
        this.geneticCode = geneticCode;
    }

    @Override
    public Double evaluateBoard(Board board, Boolean thisPlayersTurn) {
        int winner = board.findBoardWinner();
        if (winner == this.playerCode)
            return infinity;
        if (winner != 0)
            return -infinity;

        Double mainBoardNextMoveWinCount = (geneticCode.getGene(1)*this.getMainBoardNextMoveWinCount(board, thisPlayersTurn)) - (geneticCode.getGene(2)*this.getMainBoardNextMoveWinCount(board, !thisPlayersTurn));
        Double innerBoardNextMoveWinCount = (geneticCode.getGene(3)*this.getInnerBoardNextMoveWinCount(board, thisPlayersTurn)) - (geneticCode.getGene(4)*this.getInnerBoardNextMoveWinCount(board, !thisPlayersTurn));
        return (geneticCode.getGene(0)*this.slotWinDifferential(board)) + (geneticCode.getGene(5)*mainBoardNextMoveWinCount) + (geneticCode.getGene(6)*innerBoardNextMoveWinCount);
    }
}
