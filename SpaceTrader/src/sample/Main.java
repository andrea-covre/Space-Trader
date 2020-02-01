package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {
    /**
     * Graphics resorces
     */
    protected static FileInputStream backGround;

    NewGame newGame = new NewGame();
    public void start(Stage stage) throws Exception {
        newGame.start(stage);
    }

    public static void main(String[] args) throws FileNotFoundException {
        loadGraphics();
        launch(args);
    }

    /**
     * Loads all the graphics needed for the NewGame class
     * @throws FileNotFoundException if one of the images is not found
     */
    public static void loadGraphics() throws FileNotFoundException {
        backGround = new FileInputStream("resources/menu_background.jpg");
    }
}
