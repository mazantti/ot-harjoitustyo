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
public class MoveSelectorTest {
    
    public MoveSelectorTest() {
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
    public void selectorProperlySinksAShip() { 
        RuleChecker rc = new RuleChecker();
        int[][] target = new int[10][10];
        ArrayList<Integer> ships = new ArrayList();
        ships.add(3);
        MoveSelector ms = new MoveSelector(target, rc, ships);
        
        ms.nextMove(true, false);
        ms.nextMove(false, false);
        int[] move = ms.nextMove(true, false);
        int test = Math.max(move[0], move[1]);
        
        assertTrue(test == 2);
        
    }
    
    @Test 
    public void updateTargetTest() {
        RuleChecker rc = new RuleChecker();
        int[][] target = new int[10][10];
        
        for (int i = 0; i < 3; i++) {
            target[3][3 + i] = 1;
        }
        ArrayList<Integer> ships = new ArrayList();
        ships.add(3);
        MoveSelector ms = new MoveSelector(target, rc, ships);
        ms.nextMove(true, true);
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sum += target[i][j];
            }
        }
        
        assertTrue(sum == 36);
        
    }
    
    @Test
    public void updateChancesTest() {
        RuleChecker rc = new RuleChecker();
        int[][] target = new int[10][10];
        
        for (int i = 0; i < 3; i++) {
            target[3][3 + i] = 1;
        }
        ArrayList<Integer> ships = new ArrayList();
        ships.add(3);
        MoveSelector ms = new MoveSelector(target, rc, ships);
        ms.nextMove(true, true);
        int sum = 0;
        int[][] chances = ms.getChances();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (chances[i][j] == 0) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
        assertTrue(sum == 20);
    }
     
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
