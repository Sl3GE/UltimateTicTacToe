package UTTTTests;

import UTTT.Player.AIPlayer.MinMaxPlayer;
import UTTT.Player.BasicPlayer;
import UTTT.Player.Player;
import UTTT.Player.RandomPlayer;
import UTTT.Player.STDInputPlayer;
import UTTT.UTTTGame;

public class UltimateTTTTest {
//    public static void main(String[] args) {
////        Player p1 = new BasicPlayer(1);
//        Player p1 = new MinMaxPlayer(1,7);
//        Player p2 = new RandomPlayer(2);
////        Player p2 = new STDInputPlayer(2);
//        UTTTGame game = new UTTTGame(p1,p2);
////        game.display();
//        boolean gameOver = false;
//        int count = 0;
//        while (!gameOver) {
//            System.out.println(count);
//            game.nextMove();
//            if (count % 10 == 0) {
//                game.display();
//                System.out.println("");
//            }
//            gameOver = game.isGameOver();
//            count++;
//        }
//        game.display();
//        game.displayWinner();
//    }

    public static void main(String[] args) {
        Player p1 = new MinMaxPlayer(1,4);
        Player p2 = new RandomPlayer(2);
        int p1C = 0;
        int p2C = 0;
        for (int i = 0; i < 20; i++) {
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
            System.out.println("Game "+(i+1));
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
