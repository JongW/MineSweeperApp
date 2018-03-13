package MineSweeper;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MineSweeper extends Application {

    private int W = 600;
    private int H = 400;

    private int countTileX = W/20;
    private int countTileY = H/20;

    //variables to clear at the start of every game
    private Boolean game = true;
    private boolean firstClick = true;

    private Tile[][] grid = new Tile[countTileX][countTileY];
    
//    private int bombCount;

    private Stage primaryStage;

    private Parent createMineSweeper(){

        GridPane root = new GridPane();

//        Text text = new Text("hi");
//        root.add(text,0,0);

        Button newGame = new Button("New Game");
        newGame.setOnAction(Action -> restart());

        root.setHalignment(newGame, HPos.CENTER);
        root.add(newGame,0,0);

        Pane game = new Pane();
        game.setPrefSize(W,H);

        for(int i = 0; i < countTileX; i++) {
            for(int j = 0; j < countTileY; j++) {
                Tile tile = new Tile(i,j,Math.random() < 0.2);
                grid[i][j] = tile;

                tile.setOnMouseClicked(action -> openTile(tile));
                game.getChildren().add(tile);
            }
        }

        setBombCountText();

        root.add(game,0,4);
        return root;
    }

    private void setBombCountText(){
        for(int i = 0; i < countTileX; i++) {
            for(int j = 0; j < countTileY; j++) {
                Tile toCheck = grid[i][j];
                if(!toCheck.hasBomb()){
//                    bombCount++;
                    toCheck.setText(String.valueOf(countBomb(toCheck)));
                }
            }
        }
    }

    private void revealBomb(int x, int y){
        for(int i = 0; i < countTileX; i++) {
            for(int j = 0; j < countTileY; j++) {
                Tile tile = grid[i][j];
                //dont set the initial bomb u triggerd transparent as well
                if (tile.hasBomb() && i != x && j != y){
                    Text text = tile.getText();
                    tile.getTileBorder().setFill(null);
                    text.setVisible(true);
                }
            }
        }
    }

    private void openTile(Tile tile) {

        if(firstClick){
            firstClick = false;
            if(tile.hasBomb()){
                tile.setBomb(false);
            }
            setBombCountText();
        }

        tile.openTile();
        this.game = tile.getGame();

        //check if the game is over
        if (!game) {
            System.out.println("GAME IS OVER");
            revealBomb(tile.getX(),tile.getY());
        }

        if (tile.getString().isEmpty()) {
            List<Tile> tiles = getNeighbours(tile);
            for (Tile gameTile : tiles) {
                if(!gameTile.getOpen())
                    openTile(gameTile);
            }
        }
    }

    public List<Tile> getNeighbours(Tile tile){
        List<Tile> neighbours = new ArrayList<>();

        //xxx
        //xox
        //xxx

        int[] points = new int[] {
            -1,-1,
            -1,0,
            -1,1,
            0,-1,
            0,1,
            1,-1,
            1,0,
            1,1
        };

        for(int i = 0 ; i < points.length; i++) {

            int dx = points[i];
            int dy = points[++i];

            int newX = tile.getX() + dx;
            int newY = tile.getY() + dy;

            if (newX < countTileX && newX >= 0
                    && newY < countTileY && newY >=0) {
                neighbours.add(grid[newX][newY]);
            }
        }
        return neighbours;
    }

    private int countBomb(Tile tile){
        int count = 0;

        List<Tile> neighbours = getNeighbours(tile);

        for(Tile neighbour: neighbours) {
            if (neighbour.hasBomb()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        primaryStage.setTitle("MineSweeper");
        primaryStage.setScene(new Scene(createMineSweeper()));
        primaryStage.show();
    }

    void restart() {
        cleanup();
        start(primaryStage);
    }

    void cleanup() {
        game = true;
        firstClick = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
