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
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import primary.scenes.SceneLoader;

/**
 * This class represents the initial configuration for a new game
 */
public class NewGame extends Application {

    /**
     * Nodes that are important for scene changing:
     */
    private static Stage theStage;
    /**
     * Game Volume
     */
    protected static double gameVolume = 0.5;

    /**
     *
     * @param primaryStage the Stage of SpaceTrader
     * @throws Exception if a problem occurs setting the Scene
     */
    public void start(Stage primaryStage) throws Exception {
        theStage = primaryStage;
        Main.getBackgroundMusic().setCycleCount(MediaPlayer.INDEFINITE);
        Main.getBackgroundMusic().setVolume(gameVolume);
        Main.getBackgroundMusic().play();
        SceneLoader.startStage();
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

    public static void setTheStage(Stage theStage) {
        NewGame.theStage = theStage;
    }

    public static Stage getTheStage() {
        return theStage;
    }

}
