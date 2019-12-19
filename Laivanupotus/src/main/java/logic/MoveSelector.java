/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *Selects the moves for the class Machine
 * 
 * @author mazantti
 */
public class MoveSelector {

    private int[][] target;
    private int[][] chances;
    private RuleChecker ruleChecker;
    private ArrayList<Integer> ships;
    private boolean shipFound, dirFound;
    private int[] lastMove;
    private int currentShipLength;
    private int test;
    private int direction;
    private Random random;
    private int[] bow, rear;
    private boolean firstMove;

    public MoveSelector(int[][] target, RuleChecker ruleChecker, ArrayList<Integer> ships) {
        this.target = target;
        this.ruleChecker = ruleChecker;
        this.ships = ships;
        this.chances = new int[target.length][target.length];
        this.updateChances();
        this.shipFound = false;
        this.dirFound = false;
        this.lastMove = new int[]{0, 0};
        this.firstMove = true;

        this.currentShipLength = 0;
        test = 0;

        this.direction = 0;
        this.random = new Random();
        this.bow = new int[]{0, 0};
        this.rear = new int[]{0, 0};
    }
    
    private boolean isTileLegal(int x, int y) {
        boolean xWithinBounds = 0 <= x && x < target.length;
        boolean yWithinBounds = 0 <= y && y < target.length;

        return xWithinBounds && yWithinBounds;
    }

    private void updateChances() {
        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                chances[i][j] = this.calculateChance(i, j);
            }
        }
    }

    private int calculateChance(int x, int y) {

        int chance = 0;
        if (target[x][y] >= 1) {

            return chance;

        }

        if (ships.isEmpty()) {
            return 0;
        }

        for (int length : ships) {
            for (int i = 0; i < length; i++) {

                int[] bow2 = new int[]{x - i, y};
                int[] rear2 = new int[]{x - i + length - 1, y};
                if (this.ruleChecker.isDirectionLegal(bow2, rear2, target)) {
                    chance++;

                }

                bow2 = new int[]{x, y - i};
                rear2 = new int[]{x, y - i + length - 1};
                if (this.ruleChecker.isDirectionLegal(bow2, rear2, target)) {
                    chance++;

                }

            }
        }

        return chance;
    }

    /**
     * method chooses the next move for Machine given the information about the last move
     * @param hit did last move hit
     * @param sunk did last move sink a ship
     * @return 
     */
    public int[] nextMove(boolean hit, boolean sunk) {

        int[] move = new int[2];
        if (!hit && !shipFound) {
            move = selectMove();
            lastMove = move;
            return move;
        }

        if (hit) {
            target[lastMove[0]][lastMove[1]] = 1;
            this.currentShipLength++;
        } else if (!firstMove) {
            target[lastMove[0]][lastMove[1]] = 2;
        }
        firstMove = false;

        if (sunk) {
            
            shipFound = false;
            dirFound = false;

            removeShip();
            updateTarget();
            updateChances();

            move = selectMove();

        } else if (hit && !this.shipFound) {
            bow = this.lastMove;
            rear = this.lastMove;
            shipFound = true;

            move = findDir();

        } else if (!dirFound) {
            if (hit) {
                dirFound = true;
                rear = lastMove;
                direction = (direction + 3) % 4;
                move = sinkShip(hit);
                return move;
            } else {
                move = findDir();
            }

        } else {
            move = sinkShip(hit);
        }

        lastMove = move;
        return move;
    }

    private void removeShip() {
        if (ships.isEmpty()) {
            return;
        }
        int n = 0;
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i) == this.currentShipLength) {
                break;
            } else {
                n++;
            }
        }

        if (n < ships.size()) {
            ships.remove(n);
        }
        
        this.currentShipLength = 0;
    }

    private int[] findDir() { 
        int[] move = new int[2];

        while (true) {
            int x2 = (direction % 2) * (direction - 2);
            int y2 = ((direction + 1) % 2) * (direction - 1);

            int x = this.bow[0];
            int y = this.bow[1];

            if (this.isTileLegal(x + x2, y + y2)) {
                if (target[x + x2][y + y2] == 0) {
                    move[0] = x + x2;
                    move[1] = y + y2;
                    break;
                }

            }

            direction = (direction + 1) % 4;

        }
        direction = (direction + 1) % 4; 
        lastMove = move;
        return move;

    }

    private int[] sinkShip(boolean hit) {
        int[] move = new int[2];
        int x2 = (direction % 2) * (direction - 2);
        int y2 = ((direction + 1) % 2) * (direction - 1);
        if (hit) {

            int x = this.lastMove[0];
            int y = this.lastMove[1];

            if (this.isTileLegal(x + x2, y + y2) && target[x + x2][y + y2] == 0) {

                move[0] = x + x2;
                move[1] = y + y2;


            } else {
                move[0] = bow[0] - x2;
                move[1] = bow[1] - y2;
                direction = (direction + 2) % 4;

            }
        } else {

            move[0] = bow[0] - x2;
            move[1] = bow[1] - y2;
            direction = (direction + 2) % 4;
        }

        lastMove = move;
        return move;
    }

    private void updateTarget() {
    

        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target.length; j++) {
                if (target[i][j] == 1) { 
                    continue;
                }

                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (this.isTileLegal(i + k, j + l)) {
                            if (target[i + k][j + l] == 1) {
                                target[i][j] = 2;
                            }
                        }
                    }
                }
            }
        }

    }
    
    /**
     * This method is used for testing...
     * @return chances
     */

    public int[][] getChances() {
        return chances;
    }

    private int[] selectMove() {
        int x = 0;
        int y = 0;

        int sum = 0;

        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                sum += chances[i][j];
            }
        }
        if (sum == 0) {
            return new int[]{0, 0};
        }

        int n = random.nextInt(sum);
        sum = 0;
        boolean assist = false;
        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                sum += chances[i][j];
                if (sum > n) {
                    assist = true;
                    x = i;
                    y = j;
                    break;
                }
            }
            if (assist) {
                break;
            }
        }
        target[x][y] = 2;

        this.updateChances();
        this.chances[x][y] = 0;

        int[] move = new int[]{x, y};

        this.lastMove = move;
        return move;
    }
    

}
