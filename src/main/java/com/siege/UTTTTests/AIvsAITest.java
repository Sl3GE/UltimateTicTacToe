package com.siege.UTTTTests;

import com.siege.UTTT.Player.AIPlayer.BasicMinMaxPlayer;
import com.siege.UTTT.Player.AIPlayer.HeuristicMinMaxPlayer;
import com.siege.UTTT.Player.AIPlayer.Genetics.GeneticHeuristicMinMaxPlayer;
import com.siege.UTTT.Player.Player;
import com.siege.UTTT.UTTTGame;

import java.util.Date;

public class AIvsAITest {

    public static void main(String[] args) {
        Player p1 = new GeneticHeuristicMinMaxPlayer(3);
        Player p2 = new HeuristicMinMaxPlayer(3);
        int p1C = 0;
        int p2C = 0;
        Date start = new Date();
        for (int i = 1; i < 2; i++) {
            UTTTGame game = new UTTTGame(p1, p2);
            boolean gameOver = false;
            while (!gameOver) {
                game.display();
                game.nextMove();
                gameOver = game.isGameOver();
            }
            System.out.println("Game "+i);
            game.display();
            game.displayWinner();
            Player winner = game.getWinner();
            if (winner == p1) {
                p1C++;
            } else if (winner == p2) {
                p2C++;
            }
        }
        Date end = new Date();
        System.out.println("Player 1 wins: "+p1C);
        System.out.println("Player 2 wins: "+p2C);
        System.out.println("Time taken for simulation: "+ (end.getTime()-start.getTime()));
    }
}
