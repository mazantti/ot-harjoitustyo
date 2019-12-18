package logic;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mazantti
 */
public class Machine {

    private Random random;
    private RuleChecker ruleChecker;
    private MoveSelector moveSelector;
    private ShipPlacer shipPlacer;

    public Machine(int size, ArrayList<Integer> ships) {
        
        this.random = new Random();
        this.ruleChecker = new RuleChecker();
        this.shipPlacer = new ShipPlacer(this.ruleChecker);
        int[][] target = new int[size][size];
        
        this.moveSelector = new MoveSelector(target, this.ruleChecker, ships);
        
    }

   
    int[] nextMove(boolean hit, boolean sunk) {
        return this.moveSelector.nextMove(hit, sunk);
    }

    
    int[][] placeShips(int[][] map, ArrayList<Integer> ships) {
        return this.shipPlacer.placeShips(map, ships);
    }

}
