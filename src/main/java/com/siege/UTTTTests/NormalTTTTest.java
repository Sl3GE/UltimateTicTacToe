package main.java.com.siege.UTTTTests;

import main.java.com.siege.UTTT.Player.AIPlayer.BasicMinMaxPlayer;
import main.java.com.siege.UTTT.Player.Player;
import main.java.com.siege.UTTT.Player.RandomPlayer;
import main.java.com.siege.UTTT.TTTGame;

public class NormalTTTTest {
    public static void main(String[] args) {
        Player p1 = new BasicMinMaxPlayer(5);
        Player p2 = new RandomPlayer();
        TTTGame game = new TTTGame(p1, p2);
        boolean gameOver = false;
        while (!gameOver) {
            game.nextMove();
            game.display();
            System.out.println("");
            gameOver = game.isGameOver();
        }
        game.display();
        game.displayWinner();
    }
}
