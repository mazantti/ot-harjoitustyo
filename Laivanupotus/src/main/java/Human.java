
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
    }

    @Override
    int nextMove(int[][] target) {
        for (int[] is : target) {
            for (int i : is) {
                System.out.println("" + i);
            }
        }
        System.out.println("choose your next move");

        return Integer.parseInt(scanner.nextLine());
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    int[][] placeShips(int[][] map, ArrayList<Integer> ships) {
        //Ships might not follow rules yet
        int location;
        String direction;
        int x;
        int y;
        for (Integer ship : ships) {

            for (int[] is : map) {
                for (int i : is) {
                    System.out.println(""+i);
                }
            }
            
            System.out.println("choose the location of your next ship");
            System.out.println("the length of the ship is " + ship);
            
            //check that the input is suitable!!!!!!!!!!!!!
            //location should be given as a pair of ints!!!!!!!!!!!!!!1
            location = Integer.parseInt(scanner.nextLine());
            
            System.out.println("choose the direction of the ship");
            System.out.println("type h or v");
            
            direction = scanner.nextLine();
            //check that the input is suitable!!!!!!!!!!!!!
            
            x = location / map[0].length;
            y = location % map[0].length;
            
            map[x][y] = 1;
            if (direction.equals("h")) {
                for (int i = 0; i < ship; i++) {
                    map[x + i + 1][y] = 1;
                }
            } else {
                for (int i = 0; i < ship; i++) {
                    map[x][y+i+1]=1;
                }
            }
        }
        return map;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
