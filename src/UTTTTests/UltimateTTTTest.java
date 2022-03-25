package UTTTTests;

import UTTT.Player.BasicPlayer;
import UTTT.Player.Player;
import UTTT.Player.RandomPlayer;
import UTTT.Player.STDInputPlayer;
import UTTT.UTTTGame;

public class UltimateTTTTest {
    public static void main(String[] args) {
        Player p1 = new BasicPlayer(1);
        Player p2 = new RandomPlayer(2);
//        Player p2 = new STDInputPlayer(2);
        UTTTGame game = new UTTTGame(p1,p2);
//        game.display();
        boolean gameOver = false;
        int count = 0;
        while (!gameOver) {
            game.nextMove();
            if (count % 10 == 0) {
                game.display();
                System.out.println("");
            }
            gameOver = game.isGameOver();
        }
        game.display();
        game.displayWinner();
    }
}
