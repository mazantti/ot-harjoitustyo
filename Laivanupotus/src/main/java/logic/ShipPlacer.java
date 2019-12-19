/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mazantti
 */
public class ShipPlacer {

    private Random random;
    private RuleChecker ruleChecker;
    private int maxLoopNumber;

    public ShipPlacer(RuleChecker ruleChecker) {
        this.random = new Random();
        this.ruleChecker = ruleChecker;
        this.maxLoopNumber = 0;
    }

    int[][] placeShips(int[][] map, ArrayList<Integer> ships) {
        
        for (Integer ship : ships) {
            map = this.placeShip(map, ship);
        }
        
        if (map[0][0] == -1) {
            int[][] map2 = new int[map.length][map.length];
            return this.placeShips(map2, ships);
        }

        return map;
    }

    private int[][] placeShip(int[][] map, int length) {
        int x, y;
        int[] bow;
        int n = 0;

        while (true) {
            if (n > map.length * map.length) {
                int[][] map2 = new int[map.length][map.length];
                map2[0][0] = -1;
                return map2;
            }
            x = random.nextInt(map.length);
            y = random.nextInt(map.length);
            bow = new int[]{x, y};

            if (this.ruleChecker.isPlacementLegal(bow, length, map)) {
                break;
            }
            n++;
        }

        while (true) {
            int dir = random.nextInt(4);

            int[] rear = new int[]{(dir % 2) * (dir - 2), ((dir + 1) % 2) * (dir - 1)};

            rear[0] = bow[0] + length * rear[0];
            rear[1] = bow[1] + length * rear[1];

            if (this.ruleChecker.isDirectionLegal(bow, rear, map)) {
                return this.placeShip(map, bow, rear, length);
            }
        }

    }

    private int[][] placeShip(int[][] map, int[] bow, int[] rear, int length) {
        int x1 = Math.min(bow[0], rear[0]);
        int y1 = Math.min(bow[1], rear[1]);
        int x2 = Math.max(bow[0], rear[0]);

        if (x1 == x2) {
            for (int i = 0; i < length; i++) {
                map[x1][y1 + i] = 1;
            }
        } else {
            for (int i = 0; i < length; i++) {
                map[x1 + i][y1] = 1;
            }
        }

        return map;
    }

}
