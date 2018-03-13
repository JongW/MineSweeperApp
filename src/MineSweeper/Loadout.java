package MineSweeper;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Loadout extends Application {

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(500, 300);
        GridPane grid = new GridPane();

        Button difficulty = new Button("Set difficulty");

        root.getChildren().add(grid);
        grid.add(difficulty,0,0);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

    }

}
