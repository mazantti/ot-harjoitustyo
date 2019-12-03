package logic;


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
public class Machine extends Player {

    public Machine() {
        this.score = 0;
    }

    
    @Override
    int[] nextMove(int[][] target) {
        //shoot stupidly at the next unshot tile
        int[] move = new int[2];
        int x = 0;
        int y = 0;
        
        for (int[] is : target) {
            for (int i : is) {
                
                if (i == 0) {
                    move = new int[] {x, y};

                    return move;
                }
                y = (y + 1) % target.length;
            }
            x = (x + 1) % target.length;
        }
        return new int[] {0, 0};
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int[][] placeShips(int[][] map, ArrayList<Integer> ships) {
        

        for (int i = 0; i < ships.get(0); i++) {
            map[0][i] = 1;
        }
        return map;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
