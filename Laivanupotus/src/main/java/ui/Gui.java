package ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.Game;

/**
 *
 * @author mazantti
 */
public class Gui extends Application {
    private Game game;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Integer> ships = new ArrayList();
        int size = 6;
        ships.add(3);
        
        
        this.game = new Game(size,ships);
        
        primaryStage.setTitle("Laivanupotus");
        HBox box = new HBox();
        box.setSpacing(20);
        
        GridPane gridPane1 = new GridPane(); //own ships
        GridPane gridPane2 = new GridPane(); //enemy ships
        
        Tile[][] buttons1= new Tile[size][size];
        Tile[][] buttons2= new Tile[size][size];
        
//        create the tiles
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons1[i][j] = new Tile(i,j,1);
                buttons2[i][j] = new Tile(i,j,2);
                
                
                gridPane1.add(buttons1[i][j], i, j);
                gridPane2.add(buttons2[i][j], i, j);
//                buttons1[i][j].setStyle("-fx-background-color: blue; ");
            }
        }
        
        
        
//        set the actions for tiles
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons1[i][j].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Tile button = (Tile) event.getSource();
//                        System.out.println(button.getX()+" "+button.getY());
                        game.insertCommand(button.getX(), button.getY(), button.getSide());
                        updateTiles(game.getMap1(),buttons1);

                    }
                });
                
                buttons2[i][j].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        
                        Tile tile = (Tile) event.getSource();
                        int x = tile.getX();
                        int y = tile.getY();
                        int side = tile.getSide();
//                        System.out.println(tile.getX() + " " + tile.getY());
                        game.insertCommand(x,y,side);
                        
                        updateTiles(game.getTarget1(), buttons2);
                        updateTiles(game.getMap1(),buttons1);
                        
                    }
                });
                
                
            }
        }
        
        box.getChildren().addAll(gridPane1,gridPane2);
        
//        Button button1 = new Button("Button 1");
//        Button button2 = new Button("Button 2");
//        Button button3 = new Button("Button 3");
//        Button button4 = new Button("Button 4");
//        Button button5 = new Button("Button 5");
//        Button button6 = new Button("Button 6");

        
        

//        gridPane.add(button1, 0, 0, 1, 1);
//        gridPane.add(button2, 1, 0, 1, 1);
//        gridPane.add(button3, 2, 0, 1, 1);
//        gridPane.add(button4, 0, 1, 1, 1);
//        gridPane.add(button5, 1, 1, 1, 1);
//        gridPane.add(button6, 2, 1, 1, 1);

//        Scene scene = new Scene(gridPane1, 240, 100);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
//        buttons1[0][0].fire();

    }
    
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    
    private void updateTiles(int[][] map, Tile[][] tiles) {
        String[] colors = new String[4];
        colors[0] = "blue";
        colors[1] = "red";
        colors[2] = "yellow";
        colors[3] = "white";
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                tiles[i][j].setStyle("-fx-background-color: " + colors[map[i][j]] + "; ");
            }
        }
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
