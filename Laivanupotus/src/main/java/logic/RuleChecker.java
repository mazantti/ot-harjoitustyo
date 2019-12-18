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

    /**
     * checks if the suggested placement for the ship is legal
     * @param bow
     * @param length
     * @param map
     * @return true if the placement is legal
     */
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
    
    /**
     * checks if the suggested direction for the ship is legal
     * @param bow
     * @param rear
     * @param map
     * @return 
     */
    public boolean isDirectionLegal(int[] bow, int[] rear, int[][] map) {
        int x = Math.min(bow[0], rear[0]);
        int y = Math.min(bow[1], rear[1]);

        int lengthX = Math.abs(bow[0] - rear[0]);
        int lengthY = Math.abs(bow[1] - rear[1]);
        
        if (rear[0] < 0 || rear[0] >= map.length) {
            return false;
        }
        
        if (rear[1] < 0 || rear[1] >= map.length) {
            return false;
        }
        
        if (bow[0] < 0 || bow[0] >= map.length) {
            return false;
        }
        
        if (bow[1] < 0 || bow[1] >= map.length) {
            return false;
        }

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
        return this.areTilesFree(bow, rear, map);
    }
    
    private boolean areTilesFree(int[] bow, int[] rear, int[][] map) {
        int x = Math.min(bow[0], rear[0]);
        int y = Math.min(bow[1], rear[1]);
        
        int lengthX = Math.abs(bow[0] - rear[0]);
        int lengthY = Math.abs(bow[1] - rear[1]);
        
        for (int i = x; i <= x + lengthX; i++) {
            for (int j = y; j <= y + lengthY; j++) {
                if (map[i][j] == 2) {
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * checks whether the ship is sunk
     * @param x
     * @param y
     * @param map
     * @return 
     */
    public boolean isShipSunk(int x, int y, int[][] map) {
        int[][] map2 = new int[map.length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map2[i][j] = map[i][j];
            }
        }
        return isShipSunk2(x, y, map2);
    }
    
    private boolean isShipSunk2(int x, int y, int[][] map) {
        
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
        
        map[x][y] = -1;
        return isShipSunk2(x + 1, y, map) && isShipSunk2(x, y + 1, map) && isShipSunk2(x - 1, y, map) && isShipSunk2(x, y - 1, map);
    }
    
    

}
