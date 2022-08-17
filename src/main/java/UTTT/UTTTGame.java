package UTTT;

import UTTT.Board.MainBoard;
import UTTT.Player.Player;

public class UTTTGame extends TTTGame {

    public UTTTGame(Player p1, Player p2) {
        super(p1,p2, new MainBoard());
    }
}
