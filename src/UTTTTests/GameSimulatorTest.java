package UTTTTests;

import UTTT.GameSimulator;
import UTTT.Player.AIPlayer.BasicMinMaxPlayer;
import UTTT.Player.Player;
import UTTT.UTTTGame;

import java.util.Date;

public class GameSimulatorTest {
    public static void main(String[] args) {
        Player p1 = new BasicMinMaxPlayer(3);
        Player p2 = new BasicMinMaxPlayer(3);
        Date start = new Date();
        for (int i = 0; i < 40; i++) {
            GameSimulator gameSimulator1 = new GameSimulator(p1, p2, new UTTTGame(p1, p2));
            GameSimulator gameSimulator2 = new GameSimulator(p1, p2, new UTTTGame(p1, p2));

            // First way is faster

            // First way:
            gameSimulator1.start();
            gameSimulator2.start();
            try {
                gameSimulator1.join();
                gameSimulator2.join();
            } catch (Exception e) {
                // bla bla
            }
            // Second way:
            gameSimulator1.start();
            try {
                gameSimulator1.join();
            } catch (Exception e) {
                // bla bla
            }
            gameSimulator2.start();
            try {
                gameSimulator2.join();
            } catch (Exception e) {
                // bla bla
            }
        }
        Date end = new Date();
        System.out.println("Time taken for simulation: "+ (end.getTime()-start.getTime()));
    }
}
