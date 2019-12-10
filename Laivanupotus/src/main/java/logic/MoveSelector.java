/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;

/**
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

    public MoveSelector(int[][] target, RuleChecker ruleChecker) {
        this.target = target;
        this.ruleChecker = ruleChecker;
        this.chances = new int[target.length][target.length];
    }

    public MoveSelector(int[][] target, RuleChecker ruleChecker, ArrayList<Integer> ships) {
        this.target = target;
        this.ruleChecker = ruleChecker;
        this.ships = ships;
        this.chances = new int[target.length][target.length];
        this.updateChances();
        this.shipFound = false;
        this.dirFound = false;
        this.lastMove = new int[]{-1, -1};

        this.currentShipLength = 0;
        test = 0;

        this.direction = 0;
    }

    private void updateChances() {
        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                chances[i][j] = this.calculateChance(i, j);
            }
        }

        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                System.out.print(chances[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private int calculateChance(int x, int y) {

        int chance = 0;
        if (target[x][y] >= 1) {
            System.out.println("target[" + x + "][" + y + "] >= 1");
            return chance;

        }

        for (int length : ships) {
            for (int i = 0; i < length; i++) {

                int[] bow = new int[]{x - i, y};
                int[] rear = new int[]{x - i + length, y};
                if (this.ruleChecker.isDirectionLegal(bow, rear, target)) {
                    chance++;
                    if (x == 3 && y == 4) {
                        System.out.println("here");
                    }
                }

                bow = new int[]{x, y - i};
                rear = new int[]{x, y - i + length};
                if (this.ruleChecker.isDirectionLegal(bow, rear, target)) {
                    chance++;

                }

            }
        }

        return chance;
    }

    private void updateChances(int x, int y) {

        int area = 0;
        for (int length : ships) {
            area = Math.max(area, length);
        }
        System.out.println(area);

        for (int i = 1; i < area; i++) {
            if (x - i >= 0) { // i = 0 ???????????????????????
                chances[x - i][y] = this.calculateChance(x - i, y);
                System.out.println(x - i);
            }
            if (y - i >= 0) {
                chances[x][y - i] = this.calculateChance(x, y - i);
//                System.out.println(y-i);
            }
            if (x + i < this.chances.length) {
                chances[x + i][y] = this.calculateChance(x + i, y);
            }
            if (y + i < this.chances.length) {
                chances[x][y + i] = this.calculateChance(x, y + i);
            }
        }
    }

    public MoveSelector(int[][] target) {
        this.target = target;
    }

//    public int[] nextMove(boolean hit, boolean sunk) {
//        if (this.shipFound && !sunk) {
//            return this.destroyShip(hit);
//        }
//
//        if (this.shipFound && sunk) {
//            this.shipFound = false;
//            return nextMove();
//        }
//
//        if (hit && !sunk) {
//            this.shipFound = true;
//            this.target[this.lastMove[0]][this.lastMove[1]] = 2;
//            return this.destroyShip(hit);
//        }
//
//        return nextMove();
//    }
    private void updateTarget() {
        System.out.println("updateTarget");
        int x, y;

        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target.length; j++) {
                if (target[i][j] == 2) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {

                    x = i + (k % 2) * (k - 2);
                    y = j + ((k + 1) % 2) * (k - 1);

                    if (i + x < 0 || i + x >= target.length) {
                        continue;
                    }

                    if (j + y < 0 || j + y >= target.length) {
                        continue;
                    }

                    if (target[i + x][j + y] == 2) {
                        target[i][j] = 1;
                    }
                }
            }
        }
    }

    public int[] nextMove(boolean hit, boolean sunk) {
        if (hit) {
            this.currentShipLength++;
            target[this.lastMove[0]][this.lastMove[1]] = 1;
        }

//        System.out.print("hit:" + hit + " sunk:" + sunk + "  ");
        if (sunk) {
            int n = 0;
            for (int length : ships) {
                if (length == this.currentShipLength) {
                    break;
                }
                n++;
            }

            if (ships.size() > n) {
                ships.remove(n);
            }

            this.currentShipLength = 0;

            updateTarget();
            updateChances();
            this.shipFound = false;
            this.dirFound = false;

            return selectMove();
        }

        if (shipFound && !dirFound) {
            if (hit) {
                dirFound = true;
                lockDirection();
                System.out.println("lock");
            }
        }

        if (hit && !shipFound) {
            upChances();
            this.shipFound = true;
        }

        if (dirFound) {
            chooseDirection(hit);
        }

        return selectMove();
    }

    private void chooseDirection(boolean hit) {
        int x = this.lastMove[0];
        int y = this.lastMove[1];
        int x2, y2;

        if (!hit) {
            direction = (direction + 2) % 4;
            x2 = (direction % 2) * (direction - 2);
            y2 = ((direction + 1) % 2) * (direction - 1);
        }
        x2 = (direction % 2) * (direction - 2);
        y2 = ((direction + 1) % 2) * (direction - 1);
        if (x + x2 >= 0 && x + x2 < target.length) {
            if (y + y2 >= 0 && y + y2 < target.length) {

                chances[x + x2][y + y2] += 100;
            }
        }

    }

    public void lockDirection() {
        System.out.println("lockDirection");
        int x2, y2;
        int x = this.lastMove[0];
        int y = this.lastMove[1];
        int dir = 0;
        for (int i = 0; i < 4; i++) {
            x2 = (i % 2) * (i - 2);
            y2 = ((i + 1) % 2) * (i - 1);
            if (x + x2 >= 0 && x + x2 < target.length) {
                if (y + y2 >= 0 && y + y2 < target.length) {
                    if (chances[x + x2][y + y2] == 0) {
                        x = x + x2;
                        y = y + y2;
                        dir = (i + 2) % 4;
                        direction = dir;
                        break;
                    }
                }
            }

        }

        for (int i = 0; i < 4; i++) {
            x2 = (i % 2) * (i - 2);
            y2 = ((i + 1) % 2) * (i - 1);

            if (dir % 2 != i % 2) {
                if (x + x2 >= 0 && x + x2 < target.length) {
                    if (y + y2 >= 0 && y + y2 < target.length) {
                        chances[x + x2][y + y2] = 0;
                    }
                }
            }

        }

    }

    public void upChances() {
        System.out.println("upChances()");
        int x2, y2;
        int x = this.lastMove[0];
        int y = this.lastMove[1];

        for (int i = 0; i < 4; i++) {
            x2 = (i % 2) * (i - 2);
            y2 = ((i + 1) % 2) * (i - 1);

            if (x + x2 >= 0 && x + x2 < target.length) {
                if (y + y2 >= 0 && y + y2 < target.length) {
                    if (chances[x + x2][y + y2] != 0) {
                        chances[x + x2][y + y2] += 100;
                        System.out.println("+100");
                    }
                }
            }
        }
    }

    public int[] nextMove2() {
        int[] move = new int[2];
        int x = 0;
        int y = 0;

        for (int[] is : target) {
            for (int i : is) {

                if (i == 0) {
                    move = new int[]{x, y};
                    target[x][y] = 1;
                    return move;
                }
                y = (y + 1) % target.length;
            }
            x = (x + 1) % target.length;
        }
        return new int[]{0, 0};
    }

    public int[] selectMove() {
        int x = 0;
        int y = 0;
        int priority = -1;

        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                if (chances[i][j] > priority) {
                    priority = chances[i][j];
                    x = i;
                    y = j;
                }
            }
        }

        for (int i = 0; i < chances.length; i++) {
            for (int j = 0; j < chances.length; j++) {
                System.out.print(chances[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");

//        System.out.print(ships.size() + "  ");
        target[x][y] = 2;
//        System.out.print(this.chances[x][y] + "  ");
        this.updateChances(x, y);
        this.chances[x][y] = 0;

        System.out.println(x + "," + y);
        int[] move = new int[]{x, y};
//        int[] move = new int[] {test, test};
//        test++;
        this.lastMove = move;
        return move;
    }

}
