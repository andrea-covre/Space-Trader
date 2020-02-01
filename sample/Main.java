package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    NewGame newGame = new NewGame();
    public void start(Stage stage) throws Exception {
        newGame.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
