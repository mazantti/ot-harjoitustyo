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
        Game game = new Game(4,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 2, 1);
        
        int[][] map = game.getMap1();
        
        boolean test = map[0][0] == 1;
//        System.out.println(test);
        test = test && map[0][1] == 1;
//        System.out.println(test);
        test = test && map[0][2] == 1;
//        System.out.println(test);
        
        assertTrue(test);
    }
    
    @Test 
    public void placeShipDoesNotPlaceTooLongShips() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(4,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(0, 3, 1);
        
        assertTrue(game.getMap1()[0][2] == 0);
    }
    
    @Test 
    public void placeShipDoesntPlaceShipsThatArentStaight() {
        ArrayList<Integer> ships= new ArrayList();
        ships.add(3);
        Game game = new Game(4,ships);
        
        game.insertCommand(0, 0, 1);
        game.insertCommand(1, 2, 1);
        
        int[][] map = game.getMap1();
        
        boolean test = false;
        for (int[] is : map) {
            for (int i : is) {
                test = test || i == 1;
                System.out.println(test);
            }
        }
        
        assertFalse(test);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
