package logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import logic.Machine;
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
public class MachineTest {
    
    public MachineTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void placeShipsReturnsATableOfOnesAndZeros() {
        
        int[][] map = new int[5][5];
        ArrayList<Integer> ships = new ArrayList();
        ships.add(1);
        Machine m = new Machine(5, ships);
        int[][] map2 = m.placeShips(map, ships);
        
        boolean truth = true;
        for (int[] is : map2) {
            for (int i : is) {
                if (i==0 || i==1) {
                    continue;
                }
                
                truth=false;
            }
        }
        
        assertTrue(truth);
    }
    
    @Test
    public void nextMoveIsWithinTheBounds() {
        
        ArrayList<Integer> ships = new ArrayList();
        ships.add(1);
        Machine m = new Machine(5, ships);
        int[] move = m.nextMove(false, false);
        assertTrue(move[0]<5 && move[1]<5);
        
    }
}
