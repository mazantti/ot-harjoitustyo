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
public class Game {

    private int[][] map1;
    private int[][] map2;
    private int[][] target1;
    private int[][] target2;
//    private Player player1;
    private Player player2;
    private ArrayList<Integer> ships;
    private int score1;
    private int score2;
    private int winScore;

    private RuleChecker ruleChecker;

    private int expectedSide, currentShip;
    private boolean setupPlayer2;

//    private int currentShip;
    private int[] currentShipPlace;

    public Game(int size, ArrayList<Integer> ships) {
        this.ships = ships;
//        this.player1 = new Human();
        this.player2 = new Machine();
        this.map1 = new int[size][size];
        this.map2 = new int[size][size];
        this.target1 = new int[size][size];
        this.target2 = new int[size][size];

        this.ruleChecker = new RuleChecker();

        this.expectedSide = 1;
        this.setupPlayer2 = true;

        this.currentShip = 0;
        this.currentShipPlace = new int[]{-1, -1};

        this.score1 = 0;
        this.score2 = 0;
        this.winScore = 0;
        for (Integer ship : ships) {
            this.winScore += ship;
        }

    }

//    public void runGame() {
//        this.setUpGame();
//        this.playGame();
//    }

//    private boolean didItHit(int shot, int[][] map) {
//        //check if the last shot hit the target
//        int x = shot / map[0].length;
//        int y = shot % map[0].length;
//
//        return map[x][y] == 1;
//
//    }

//    private void setUpGame() {
//        this.map1 = player1.placeShips(map1, ships);
//        this.map2 = player2.placeShips(map2, ships);
//    }

//    private void playGame() {
//        while (Math.max(player1.getScore(), player2.getScore()) != this.winScore) {
//            this.playTurn(player1, target1, map2);
//            this.playTurn(player2, target2, map1);
//
//// testing...
//            for (int[] is : target1) {
//                for (int i : is) {
//                    System.out.print(i);
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//            for (int[] is : target2) {
//                for (int i : is) {
//                    System.out.print(i);
//                }
//                System.out.println("");
//            }
//            System.out.println("");
//        }
//
//    }

//    private void playTurn(Player player, int[][] target, int[][] enemy) {
//        int[] move = player.nextMove(target);
//        int x = move[0];
//        int y = move[1];
//
//        target[x][y] = 1; //1 means that the tile is hit
//
//        if (enemy[x][y] == 1) {
//            enemy[x][y] = 2;
//            target[x][y] = 2;
//
//            player.plusScore();
//
//            this.playTurn(player, target, enemy); // if you hit, you get to continue
//        }
//    }

    public void insertCommand(int x, int y, int side) {

        if (this.isSideExpected(x, y, side)) {
//            System.out.println(x + " " + y + "  " + side);
            this.directCommand(x, y, side);
        }
    }

    private boolean isSideExpected(int x, int y, int side) {
        return this.expectedSide == side;
    }

    private void directCommand(int x, int y, int side) {
        if (side == 1) {
//            set the ships up

            if (this.currentShip < this.ships.size()) {
                int bow[] = new int[]{x, y};
                boolean isLegal = this.ruleChecker.isPlacementLegal(bow, this.ships.get(this.currentShip), map1);
                
                if (isLegal) { //doesn't allow ships of the length 2
                    this.placeShip(x, y);
                }
                

            } else {
                this.expectedSide = 2;
            }

            if (this.setupPlayer2) {
                map2 = player2.placeShips(map2, ships);
                setupPlayer2 = false;
//                System.out.println("p2 ok");
            }
        } else {
//            should be made its own method????
            target1[x][y] = 1; //1 means that the tile is hit

            if (map2[x][y] == 1) {
                map2[x][y] = 2;
                target1[x][y] = 2;

            }

            int[] move2 = player2.nextMove(target2);
            target2[move2[0]][move2[1]] = 1;

            if (map1[move2[0]][move2[1]] == 1) {
                map1[move2[0]][move2[1]] = 2;
                target2[move2[0]][move2[1]] = 2;
                
            }
        }
    }

    private void placeShip(int x, int y) {
//        System.out.println("placeShip is called");

        if (this.currentShipPlace[0] == -1) {
            this.currentShipPlace = new int[]{x, y};
            this.map1[x][y] = 4;
        } else {

            int length = this.ships.get(currentShip);

            if (this.currentShipPlace[0] == x) {
                if (Math.abs(this.currentShipPlace[1] - y) + 1 == length) {
                    int min = Math.min(this.currentShipPlace[1], y);
                    for (int i = min; i < min + length; i++) {
                        this.map1[x][i] = 1;
//                        System.out.print(x + "," + i + " ");
                    }
//                    System.out.println("");
                    this.currentShipPlace = new int[]{-1, -1};
                    this.currentShip++;
                }
            }

            if (this.currentShipPlace[1] == y) {
                if (Math.abs(this.currentShipPlace[0] - x) + 1 == length) {
                    int min = Math.min(this.currentShipPlace[0], x);
                    for (int i = min; i < min + length; i++) {
                        this.map1[i][y] = 1;
//                        System.out.print(i + "," + y + " ");
                    }
//                    System.out.println("");
                    this.currentShipPlace = new int[]{-1, -1};
                    this.currentShip++;
                }
            }

        }

    }

    public int[][] getMap1() {
        return map1;
    }

    public int[][] getTarget1() {
        return target1;
    }
    
    public String getTip() {
        return "do something";
    }

}
