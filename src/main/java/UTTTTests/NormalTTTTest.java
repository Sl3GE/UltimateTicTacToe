package UTTTTests;

import UTTT.Player.AIPlayer.BasicMinMaxPlayer;
import UTTT.Player.Player;
import UTTT.Player.RandomPlayer;
import UTTT.Player.STDInputPlayer;
import UTTT.TTTGame;

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
