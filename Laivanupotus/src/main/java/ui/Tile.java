/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.control.Button;

/**
 *
 * @author mazantti
 */
public class Tile extends Button {
    private int x;
    private int y;
    private int side;
    
    public Tile(int x, int y, int side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getSide() {
        return side;
    }
    
}
