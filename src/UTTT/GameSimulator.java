package UTTT;

import UTTT.Player.Player;

public class GameSimulator extends Thread {

    private Player player1;
    private Player player2;
    private TTTGame tttGame;
    // Players that don't require user input are preferred
    public GameSimulator(Player p1, Player p2, TTTGame game) {
        player1 = p1;
        player2 = p2;
        tttGame = game;
    }

    @Override
    public void run() {
        int p1C = 0;
        int p2C = 0;
//        for (int i = 1; i < 51; i++) {
            boolean gameOver = false;
            while (!gameOver) {
                tttGame.nextMove();
                gameOver = tttGame.isGameOver();
            }
            Player winner = tttGame.getWinner();
            if (winner == player1) {
                p1C++;
            } else if (winner == player2) {
                p2C++;
            }
//        }
        System.out.println("Player 1 wins: "+p1C);
        System.out.println("Player 2 wins: "+p2C);
    }
}
