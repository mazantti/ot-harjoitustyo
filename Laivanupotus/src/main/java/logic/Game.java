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
    private Player player1;
    private Player player2;
    private ArrayList<Integer> ships;
    private int score1;
    private int score2;
    private int winScore;

    private boolean setupPlayer2;

    public Game(int size, ArrayList<Integer> ships) {
        this.ships = ships;
        this.player1 = new Human();
        this.player2 = new Machine();
        this.map1 = new int[size][size];
        this.map2 = new int[size][size];
        this.target1 = new int[size][size];
        this.target2 = new int[size][size];

        this.setupPlayer2 = true;

        this.score1 = 0;
        this.score2 = 0;
        this.winScore = 0;
        for (Integer ship : ships) {
            this.winScore += ship;
        }

        for (int[] in : target1) {
            for (int i : in) {
                i = 0;
            }
        }

        for (int[] in : target2) {
            for (int i : in) {
                i = 0;
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
            this.playTurn(player1, target1, map2);
            this.playTurn(player2, target2, map1);

// testing...
            for (int[] is : target1) {
                for (int i : is) {
                    System.out.print(i);
                }
                System.out.println("");
            }
            System.out.println("");
            for (int[] is : target2) {
                for (int i : is) {
                    System.out.print(i);
                }
                System.out.println("");
            }
            System.out.println("");
        }

    }

    private void playTurn(Player player, int[][] target, int[][] enemy) {
        int[] move = player.nextMove(target);
        int x = move[0];
        int y = move[1];

        target[x][y] = 1; //1 means that the tile is hit

        if (enemy[x][y] == 1) {
            enemy[x][y] = 2;
            target[x][y] = 2;

            player.plusScore();

            this.playTurn(player, target, enemy); // if you hit, you get to continue
        }
    }

    public void insertCommand(int x, int y, int side) {

        if (this.isCommandExpected(x, y, side)) {
//            System.out.println(x + " " + y + "  " + side);
            this.directCommand(x, y, side);
        }
    }

    private boolean isCommandExpected(int x, int y, int side) {
        return true;
    }

    private void directCommand(int x, int y, int side) {
        if (side == 1) {
            this.map1[x][y] = 1;
            if (this.setupPlayer2) {
                map2 = player2.placeShips(map2, ships);
                setupPlayer2 = false;
                System.out.println("p2 ok");
            }
        } else {

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
                System.out.println("p2 hit");
            }
        }
    }

    public int[][] getMap1() {
        return map1;
    }

    public int[][] getTarget1() {
        return target1;
    }

}
