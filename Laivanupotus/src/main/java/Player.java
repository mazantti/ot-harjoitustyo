
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
public abstract class Player {
    private String name; //isn't used yet
    int score;
    
    public int getScore() {
        return this.score;
    }
    
    public void plusScore() {
        this.score++;
    }
    
    public String getName() {
        return this.name;
    }
    
    abstract int nextMove(int[][] target);
    abstract int[][] placeShips(int[][] map, ArrayList<Integer> ships);
}
