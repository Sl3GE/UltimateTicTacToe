package UTTTTests;

import UTTT.Player.AIPlayer.MinMaxPlayer;
import UTTT.Player.Player;
import UTTT.Player.RandomPlayer;
import UTTT.Player.STDInputPlayer;
import UTTT.TTTGame;

public class NormalTTTTest {
    public static void main(String[] args) {
//        Player p1 = new BasicPlayer(1);
        Player p1 = new MinMaxPlayer(1,5);
        Player p2 = new RandomPlayer(2);
        TTTGame game = new TTTGame(p1,p2);
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
