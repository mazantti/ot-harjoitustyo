
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mazantti
 */
public class Human extends Player {

    Scanner scanner;

    public Human() {
        this.scanner = new Scanner(System.in);
        this.score = 0;
    }

    @Override
    int nextMove(int[][] target) {
        for (int[] is : target) {
            for (int i : is) {
                System.out.print("" + i);
            }
            System.out.println("");
        }
        System.out.println("choose your next move");

        return Integer.parseInt(scanner.nextLine());
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int[][] placeShips(int[][] map, ArrayList<Integer> ships) {
        //Ships might not follow rules yet
       
        String direction;
        int x;
        int y;
        for (Integer ship : ships) {

            for (int[] is : map) {
                for (int i : is) {
                    System.out.print(""+i);
                }
                System.out.println("");
            }
            
            System.out.println("choose the location of your next ship");
            System.out.println("the length of the ship is " + ship);
            System.out.println("give the x-coordinate");
            //checking that the input is suitable is not too relevant, since this is not the final method of choosing the next move

            x = Integer.parseInt(scanner.nextLine());
            
            System.out.println("give the y-cordinate");
            
            y = Integer.parseInt(scanner.nextLine());
            
            System.out.println("choose the direction of the ship");
            
            while (true) {
            System.out.println("type h or v (for horizontal/vertical)");
            
            direction = scanner.nextLine();
            
            if (direction.equals("h")) break;
            if (direction.equals("v")) break;
            }
            
            
            map[x][y] = 1;
            if (direction.equals("h")) {
                for (int i = 0; i < ship; i++) {
                    map[x + i][y] = 1;
                }
            } else {
                for (int i = 0; i < ship; i++) {
                    map[x][y + i]=1;
                }
            }
        }
        return map;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void placeShip(int length) {
        
    }
    
    private boolean isPlacementLegal(int x, int y) {
        return true;
    }
}
