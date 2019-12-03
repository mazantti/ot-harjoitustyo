package logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class RuleCheckerTest {
    
    public RuleCheckerTest() {
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
    public void isPlacementLegalNoticesAnIllegalPlacement() {
        RuleChecker r = new RuleChecker();
        
        int[][] map = new int[5][5];
        map[1][1] = 1;
        
        assertFalse(r.isPlacementLegal(new int[] {2, 2}, 2, map));
    }
    
    @Test
    public void isPlacementLegalNoticesALegalPlacement() {
        RuleChecker r = new RuleChecker();
        
        int[][] map = new int[5][5];
        
        assertTrue(r.isPlacementLegal(new int[] {2, 2}, 2, map));
    }
    
    @Test
    public void isDirectionLegalNoticesAnIllegalDirection() {
        RuleChecker r = new RuleChecker();
        
        int[][] map = new int[5][5];
        
        map[1][2] = 1;
        
        assertFalse(r.isDirectionLegal(new int[] {0, 0}, new int[] {0, 4}, map));
    }
    
    
    @Test
    public void isDirectionLegalNoticesALegalDirection() {
        RuleChecker r = new RuleChecker();
        
        int[][] map = new int[5][5];
        
        assertTrue(r.isDirectionLegal(new int[] {0, 0}, new int[] {0, 4}, map));
    }
    
    @Test 
    public void isShipSunkReturnsTrueWhenSupposed() {
        RuleChecker r = new RuleChecker();
        
        int[][] map = new int[5][5];
        map[1][1] = 2;
        map[1][2] = 2;
        map[1][3] = 2;
        
        assertTrue(r.isShipSunk(1, 2, map));
        
    }
    
    @Test 
    public void isShipSunkReturnsFalseWhenSupposed() {
        RuleChecker r = new RuleChecker();
        
        int[][] map = new int[5][5];
        map[1][1] = 1;
        map[1][2] = 2;
        map[1][3] = 2;
        
        assertFalse(r.isShipSunk(1, 2, map));
        
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
