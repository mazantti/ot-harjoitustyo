/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author mazantti
 */
public class RuleChecker {

    public RuleChecker() {
    }

    public boolean isPlacementLegal(int[] bow, int length, int[][] map) {
        int rears[][] = new int[4][2];
        rears[0][0] = bow[0] + length - 1;
        rears[0][1] = bow[1];

        rears[1][0] = bow[0];
        rears[1][1] = bow[1] + length - 1;

        rears[2][0] = bow[0];
        rears[2][1] = bow[1] - length + 1;

        rears[3][0] = bow[0] - length + 1;
        rears[3][1] = bow[1];

        boolean legality = false;

        for (int i = 0; i < 4; i++) {
            legality = legality || isDirectionLegal(bow, rears[i], map);
        }

        return legality;
    }

    public boolean isDirectionLegal(int[] bow, int[] rear, int[][] map) {
        int x = Math.min(bow[0], rear[0]);
        int y = Math.min(bow[1], rear[1]);

        int lengthX = Math.abs(bow[0] - rear[0]);
        int lengthY = Math.abs(bow[1] - rear[1]);

        for (int i = x - 1; i <= x + lengthX + 1; i++) {
            for (int j = y - 1; j <= y + lengthY + 1; j++) {
                if (i < 0 || i >= map.length) {
                    continue;
                }

                if (j < 0 || j >= map.length) {
                    continue;
                }

                if (map[i][j] == 1) {
//                    System.out.println("returned false");
                    return false;
                    
                }
            }
        }
//        System.out.println("returned true");
        return true;
    }

    public boolean isShipSunk(int x, int y, int[][] map) {
//        System.out.println(x + "," + y);
        
        int[][] map2 = map;
        
        if (x < 0 || x >= map.length) {
            return true;
        }
        
        if (y < 0 || y >= map.length) {
            return true;
        }
        
        if (map[x][y] == 1) {
            return false;
        }

        if (map[x][y] == 0) {
            return true;
        }
        
        if (map[x][y] == -1) {
            return true;
        } 
        
        map2[x][y] = -1;
        
        return isShipSunk(x + 1, y, map2) && isShipSunk(x, y + 1, map2) && isShipSunk(x - 1, y, map2) && isShipSunk(x, y - 1, map2);
    }

}
