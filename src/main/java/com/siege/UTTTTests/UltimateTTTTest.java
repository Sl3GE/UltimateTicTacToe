package com.siege.UTTTTests;

import com.siege.UTTT.Player.AIPlayer.BasicMinMaxPlayer;
import com.siege.UTTT.Player.Player;
import com.siege.UTTT.Player.RandomPlayer;
import com.siege.UTTT.UTTTGame;

public class UltimateTTTTest {
    public static void main(String[] args) {
        Player p1 = new BasicMinMaxPlayer(7);
        Player p2 = new RandomPlayer();
//        Player p2 = new STDInputPlayer();
        UTTTGame game = new UTTTGame(p1,p2);
//        game.display();
        boolean gameOver = false;
        int count = 0;
        while (!gameOver) {
            System.out.println(count);
            game.nextMove();
            if (count % 10 == 0) {
                game.display();
                System.out.println("");
            }
            gameOver = game.isGameOver();
            count++;
        }
        game.display();
        game.displayWinner();
    }
}
