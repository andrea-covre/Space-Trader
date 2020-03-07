package primary.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import primary.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.util.List;

public abstract class SceneLoader {


    protected static Player player;


    //Travel
    protected static double fuelCostPerUnit = 1; //cost per unit of distance
    protected static int travelDiscountPerPilotLevel = 3; //in percentage
    protected static int costToSelectedLocation;
    protected static Difficulty setDifficulty;

    //Map info
    protected static Region currentLocation;
    protected static Region selectedLocation;

    //Ship info
    protected static Ship currentShip;

    //Map generation
    protected static boolean regionsGenerated = false;
    protected static List<Region> regions;
    //number of regions generated for each new map [max reasonable # = 20 because of spacing]
    protected static int minimunRegionSpacing = 300;
    //coordinate-wise: minimum distance required between planets,
    // valid for both X and Y [used during world generation]
    protected static int numberOfRegions = 10;

    //

    /**
     * Graphics
     */
    protected static final ImageView BACKGROUND = new ImageView(
            new Image(Main.getBackGround(), 960, 1280, false, false));

    protected static final ImageView MAP_BACKGROUND = new ImageView(
            new Image(Main.getMapBackGround(), 960, 1280, false, false));



    protected enum Difficulty {
        EASY, MEDIUM, HARD
    }

    public abstract Parent build() throws MalformedURLException;

    public void setStage(SceneLoader b) throws MalformedURLException {
        NewGame.getTheStage().getScene().setRoot(b.build());
    }

    public static void startStage() {
        NewGame.getTheStage().setScene(new Scene(new WelcomeScene().build()));
    }
}
