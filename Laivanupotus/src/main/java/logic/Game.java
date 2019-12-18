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
    private Machine machine;
    private ArrayList<Integer> ships;
    private int score1;
    private int score2;
    private int winScore;
    
    private RuleChecker ruleChecker;
    
    private int expectedSide, currentShip;
    private boolean setupMachine;

//    private int currentShip;
    private int[] currentShipPlace;
    private boolean hit;
    private boolean sunk;
    private String tip;
    
    public Game(int size, ArrayList<Integer> ships) {
        this.ships = ships;
        this.machine = new Machine(size, ships);
        this.map1 = new int[size][size];
        this.map2 = new int[size][size];
        this.target1 = new int[size][size];
        
        this.ruleChecker = new RuleChecker();
        
        this.expectedSide = 1;
        this.setupMachine = true;
        
        this.currentShip = 0;
        this.currentShipPlace = new int[]{-1, -1};
        
        this.score1 = 0;
        this.score2 = 0;
        this.winScore = 0;
        for (Integer ship : ships) {
            this.winScore += ship;
        }
        tip = "Sijoita laiva. Laivan pituus on " + this.ships.get(0) + " ruutua.";
        
    }

    /**
     * Gives the next command from user.
     *
     * @param x coordinate
     * @param y coordinate
     * @param side which board was clicked
     */
    public void insertCommand(int x, int y, int side) {
        
        if (this.isSideExpected(x, y, side)) {
            
            this.directCommand(x, y, side);
        }
    }
    
    private boolean isSideExpected(int x, int y, int side) {
        return this.expectedSide == side;
    }
    
    private void directCommand(int x, int y, int side) {
        if (side == 1) {
            this.shipPlacingTurn(x, y);            
        } else {
            this.shootingTurn(x, y);
        }
        isGameOver();
    }
    
    private void shipPlacingTurn(int x, int y) {
        if (this.currentShip + 1 < this.ships.size()) {
            tip = "Sijoita laiva. Laivan pituus on " + this.ships.get(this.currentShip + 1) + " ruutua.";
        }
        
        if (this.currentShip < this.ships.size()) {
            
            int bow[] = new int[]{x, y};
            
            boolean isLegal = this.ruleChecker.isPlacementLegal(bow, this.ships.get(this.currentShip), map1);
            
            if (isLegal) {
                this.placeShip(x, y);
            }
            
        } else {
            this.expectedSide = 2;
        }
        
        if (this.setupMachine) {
            map2 = machine.placeShips(map2, ships);
            setupMachine = false;
            
        }
    }
    
    private void shootingTurn(int x, int y) {
                    target1[x][y] = 1;
            
            if (map2[x][y] == 1) {
                map2[x][y] = 2;
                target1[x][y] = 2;
                tip = "Osui.";
                score1++;
                int[][] wasteMap = new int[map2.length][map2.length];
                for (int i = 0; i < map2.length; i++) {
                    for (int j = 0; j < map2.length; j++) {
                        wasteMap[i][j] = map2[i][j];
                    }
                }
                if (this.ruleChecker.isShipSunk(x, y, map2)) {
                    tip = "Osui ja upposi";
                }
                
            } else {
                tip = "Ohi.";
            }
            
            int[] move2 = machine.nextMove(hit, sunk);
            
            if (map1[move2[0]][move2[1]] == 1) {
                map1[move2[0]][move2[1]] = 2;
                hit = true;
                score2++;
                
                sunk = this.ruleChecker.isShipSunk(move2[0], move2[1], map1);
                
            } else {
                hit = false;
                
            }
    }
    
    private void placeShip(int x, int y) {
        
        if (this.currentShipPlace[0] == -1) {
            this.currentShipPlace = new int[]{x, y};
            tip = "Sijoita laiva. Laivan pituus on " + this.ships.get(this.currentShip) + " ruutua.";
            this.map1[x][y] = 4;
        } else {
            
            int length = this.ships.get(currentShip);
            
            if (this.currentShipPlace[0] == x) {
                if (Math.abs(this.currentShipPlace[1] - y) + 1 == length) {
                    int min = Math.min(this.currentShipPlace[1], y);
                    for (int i = min; i < min + length; i++) {
                        this.map1[x][i] = 1;
                        
                    }
                    
                    this.currentShipPlace = new int[]{-1, -1};
                    this.currentShip++;
                }
            }
            
            if (this.currentShipPlace[1] == y) {
                if (Math.abs(this.currentShipPlace[0] - x) + 1 == length) {
                    int min = Math.min(this.currentShipPlace[0], x);
                    for (int i = min; i < min + length; i++) {
                        this.map1[i][y] = 1;
                        
                    }
                    
                    this.currentShipPlace = new int[]{-1, -1};
                    this.currentShip++;
                }
            }
            if (this.currentShip < this.ships.size()) {
                tip = "Sijoita laiva. Laivan pituus on " + this.ships.get(this.currentShip) + " ruutua.";
                
            } else {
                tip = "Valitse ruutu jota ammut";
                this.expectedSide = 2;
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
        return tip;
    }
    
    private void isGameOver() {
        if (score1 >= this.winScore) {
            tip = "Voitit pelin.";
            score2 = 0;
        }
        
        if (score2 >= this.winScore) {
            tip = "Hävisit pelin";
            score1 = 0;
        }
    }
    
}
