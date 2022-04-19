package UTTTTests;

import UTTT.Player.AIPlayer.BasicMinMaxPlayer;
import UTTT.Player.AIPlayer.HeuristicMinMaxPlayer;
import UTTT.Player.Player;
import UTTT.Player.RandomPlayer;
import UTTT.UTTTGame;

public class AIvsAITest {

    public static void main(String[] args) {
        Player p1 = new BasicMinMaxPlayer(1,5);
//        Player p2 = new HeuristicMinMaxPlayer(1,5);
        Player p2 = new RandomPlayer(2);
        int p1C = 0;
        int p2C = 0;
        for (int i = 1; i < 101; i++) {
            UTTTGame game = new UTTTGame(p1, p2);
            boolean gameOver = false;
//        int count = 0;
            while (!gameOver) {
//            System.out.println(count);
                game.nextMove();
//            if (count % 10 == 0) {
//                game.display();
//                System.out.println("");
//            }
                gameOver = game.isGameOver();
//            count++;
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
        System.out.println("Player 1 wins: "+p1C);
        System.out.println("Player 2 wins: "+p2C);
    }
}
