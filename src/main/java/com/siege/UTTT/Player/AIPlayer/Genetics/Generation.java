package com.siege.UTTT.Player.AIPlayer.Genetics;

import java.util.ArrayList;
import java.util.HashMap;

public class Generation {

    private static int generationCount = 0;

    private int generationId;
    private ArrayList<GeneticHeuristicMinMaxPlayer> players;
    private int playerCount;
    private HashMap<Integer,Double> playerWinRate;

    public Generation() {
        this.generationId = ++generationCount;
        this.players = new ArrayList<>();
        this.playerCount = 0;
        this.playerWinRate = new HashMap<>();
    }

    public void addPlayer(GeneticHeuristicMinMaxPlayer geneticPlayer) {
        this.players.add(geneticPlayer);
        this.playerCount++;
}

    public static Generation createGeneration(int playerCount, int playerComplexityDepth) {
        int playerDepth = playerComplexityDepth > 0 ? playerComplexityDepth : GeneticHeuristicMinMaxPlayer.defaultPlayerComplexityDepth;
        Generation newGeneration = new Generation();
        for (int i = 0; i < playerCount; i++) {
            newGeneration.addPlayer(new GeneticHeuristicMinMaxPlayer(playerDepth));
        }

        return newGeneration;
    }
}
