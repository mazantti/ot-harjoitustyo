
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mazantti
 */
public class Laivanupotus {
    public static void main(String[] args) {
        ArrayList<Integer> ships = new ArrayList();
        ships.add(3);
        Game game = new Game(4,ships);
        game.runGame();
    }
}
