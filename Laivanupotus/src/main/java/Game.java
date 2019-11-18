
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
    private Player player1;
    private Player player2;
    private ArrayList<Integer> ships;
    private int score1;
    private int score2;
    private int winScore; 

    public Game(int size, ArrayList<Integer> ships) {
        this.ships = ships;
        this.player1 = new Human();
        this.player2 = new Machine();
        this.map1 = new int[size][size];
        this.map2 = new int[size][size];
        this.target1 = new int[size][size];
        this.target2 = new int[size][size];
        
        this.score1=0;
        this.score2=0;
        this.winScore = 0;
        for (Integer ship : ships) {
            this.winScore += ship;
        }
        
        for (int[] in : target1) {
            for (int i : in) {
                i=0;
            }
        }
        
        
        for (int[] in : target2) {
            for (int i : in) {
                i=0;
            }
        }
    }
    
    public void runGame() {
        this.setUpGame();
        this.playGame();
    }
    
    private boolean didItHit(int shot, int[][] map) {
        //check if the last shot hit the target
        int x = shot / map[0].length;
        int y = shot % map[0].length;
        
        return map[x][y] == 1;
        
    } 
    
    private void setUpGame() {
        this.map1 = player1.placeShips(map1, ships);
        this.map2 = player2.placeShips(map2, ships);
    }
    
    private void playGame() {
        while (Math.max(player1.getScore(), player2.getScore()) != this.winScore) {
            this.playTurn(player1,target1,map2);
            this.playTurn(player2,target2,map1);
        }
        
    }
    
    private void playTurn(Player player, int[][] target, int[][] enemy) {
        int n = player.nextMove(target);
        int x = n/target.length;  
        int y = n%target.length;
        
        target[x][y] = 1; //1 means that the tile is hit
        
        if (enemy[x][y] == 1) {
            enemy[x][y] = 2;
            target[x][y] = 2;
            
            player.plusScore();
            
            this.playTurn(player, target, enemy); // if you hit, you get to continue
        }
    }

   
}
