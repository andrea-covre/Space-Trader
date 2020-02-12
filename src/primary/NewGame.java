package primary; /**
 * Authors:
 * Thomas Crawford,
 * Calogero Cullotta
 * Andrea Covre
 * Joseph Dodd
 * Rares Cristian
 * Version: 1.1
 */

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import primary.scenes.SceneBuilder;

import java.util.List;

/**
 * This class represents the initial configuration for a new game
 */
public class NewGame extends Application {
    /**
     * Nodes that are important for scene changing:
     */
    public static Stage theStage;
    /**
     * Game Volume
     */
    protected static double GAMEVOLUME = 0.02;


    /**
     *
     * @param primaryStage the Stage of SpaceTrader
     * @throws Exception if a problem occurs setting the Scene
     */
    public void start(Stage primaryStage) throws Exception {
        theStage = primaryStage;
        Main.backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        Main.backgroundMusic.setVolume(GAMEVOLUME);
        Main.backgroundMusic.play();

//        primaryStage.setScene(new Scene(welcome()));
        SceneBuilder.startStage();
        primaryStage.setTitle("Space trader");
        primaryStage.setResizable(true);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }
}

//TODO: try to separate this big file in different classes
//TODO: need to delete the extra images and their loading (for already visited world and not)