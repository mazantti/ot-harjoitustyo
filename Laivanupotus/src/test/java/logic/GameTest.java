/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mazantti
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test 
    public void placeShipPlacesAShip() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 2, 1);
        
        int[][] map = game.getMap1();
        
        boolean test = map[0][0] == 1;
        test = test && map[0][1] == 1;
        test = test && map[0][2] == 1;
       
        assertTrue(test);
    }
    
    @Test 
    public void placeShipPlacesAShipInDifferentDirection() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(2, 0, 1);
        
        int[][] map = game.getMap1();
        
        boolean test = map[0][0] == 1;
        test = test && map[1][0] == 1;
        test = test && map[2][0] == 1;
       
        assertTrue(test);
    }
    
    @Test 
    public void placeShipDoesNotPlaceTooLongShips() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 3, 1);
        
        assertTrue(game.getMap1()[0][2] == 0);
    }
    
    @Test 
    public void placeShipDoesntPlaceShipsThatArentStaight() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(1, 2, 1);
        
        int[][] map = game.getMap1();
        
        boolean test = false;
        for (int[] is : map) {
            for (int i : is) {
                test = test || i == 1;
            }
        }
        
        assertFalse(test);
    }
    
    @Test
    public void itIsPossibleToShootEnemyShips() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 2, 1);
        
        game.insertCommand(0, 0, 2);
        
        assertTrue(game.getTarget()[0][0] != 0);
    }
    
    @Test
    public void machineShootsBackProperly() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 2, 1);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game.insertCommand(i, j, 2);
            }
        }
        int[][] map = game.getMap1();
        boolean test = map[0][0] == 2;
        test = test && map[0][1] == 2;
        test = test && map[0][2] == 2;
        
        assertTrue(test);
    }
    
    @Test
    public void playerCanHitMachine() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(10,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 2, 1);
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game.insertCommand(i, j, 2);
            }
        }
        int[][] target = game.getTarget();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sum += target[i][j];
            }
        }
        assertTrue(sum == 103);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
