
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
public class Game {
    private int[][] map1;
    private int[][] map2;
    private int[][] target1;
    private int[][] target2;
    private ArrayList<Integer> ships;

    public Game(int size, ArrayList<Integer> ships) {
        this.ships = ships;
        this.map1 = new int[size][size];
        this.map2 = new int[size][size];
        this.target1 = new int[size][size];
        this.target2 = new int[size][size];
    }
    
    
            
}
