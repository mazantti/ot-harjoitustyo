package logic;


import java.util.ArrayList;
import ui.Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mazantti
 */
public class Laivanupotus {
    public static void main(String[] args) {
        
        Gui.main(args);
        
        ArrayList<Integer> ships = new ArrayList();
        ships.add(3);
        Game game = new Game(4,ships);
        game.runGame();

//        int[][] taulu = new int[3][3];
//        int n = 0;
//        for (int[] is : taulu) {
//            for (int i : is) {
//                i=n;
//                System.out.print(n);
//                n++;
//                System.out.println(" " + n);
//            }
//        }
//        n=0;
//        for (int i = 0; i < taulu.length; i++) {
//            for (int j = 0; j < taulu.length; j++) {
//                taulu[i][j] = n;
//                n++;
//            }
//        }
//        
//        for (int[] is : taulu) {
//            for (int i : is) {
//                System.out.print(""+i);
//            }
//            System.out.println("");
//        }

    }
}
