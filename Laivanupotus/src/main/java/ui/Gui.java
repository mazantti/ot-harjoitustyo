package ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
//        int size = 6;
//        ships.add(3);
//        ships.add(4);
        
        int size = 10;
        ships.add(5);
        ships.add(4);
        ships.add(3);
        ships.add(3);
        ships.add(2);
        ships.add(2);

        this.game = new Game(size,ships);
        
        primaryStage.setTitle("Laivanupotus");
        HBox box = new HBox();
        VBox bigbox = new VBox();
        box.setSpacing(20);
        
        Text text = new Text();
        text.setText(this.game.getTip());
        
        GridPane gridPane1 = new GridPane(); //own ships
        GridPane gridPane2 = new GridPane(); //enemy ships
        
        Tile[][] buttons1= new Tile[size][size];
        Tile[][] buttons2= new Tile[size][size];
        
//        create the tiles
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons1[i][j] = new Tile(i,j,1);
                buttons2[i][j] = new Tile(i,j,2);
                
                buttons1[i][j].setStyle("-fx-background-color: blue; ");
                buttons2[i][j].setStyle("-fx-background-color: blue; ");
                
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
                        updateTip(text);

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
                        
                        updateTiles(game.getTarget(), buttons2);
                        updateTiles(game.getMap1(),buttons1);
                        updateTip(text);
                        
                    }
                });
                
                
            }
        }
        
        box.getChildren().addAll(gridPane1,gridPane2);
        bigbox.getChildren().addAll(text,box);
        
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
        Scene scene = new Scene(bigbox);
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
        String[] colors = new String[5];
        colors[0] = "blue";
        colors[1] = "yellow";
        colors[2] = "red";
        colors[3] = "white";
        colors[4] = colors[1];
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                tiles[i][j].setStyle("-fx-background-color: " + colors[map[i][j]] + "; ");
            }
        }
    }
    
    private void updateTip(Text text) {
        text.setText(game.getTip());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
