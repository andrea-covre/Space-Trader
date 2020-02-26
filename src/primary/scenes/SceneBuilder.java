package primary.scenes;

import primary.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class SceneBuilder {
    //Travel
    protected static double fuelCostPerUnit = 1; //cost per unit of distance
    protected static int travelDiscountPerPilotLevel = 3; //in percentage
    protected static int costToSelectedLocation;

    //Map info
    protected static Region currentLocation;
    protected static Region selectedLocation;

    //Map generation
    protected static boolean regionsGenerated = false;
    protected static List<Region> regions;
    //number of regions generated for each new map [max reasonable # = 20 because of spacing]
    protected static int minimunRegionSpacing = 300;
    //coordinate-wise: minimum distance required between planets,
    // valid for both X and Y [used during world generation]
    protected static int numberOfRegions = 10;

    /**
     * Graphics
     */
    protected static final ImageView BACKGROUND = new ImageView(
            new Image(Main.getBackGround(), 960, 1280, false, false));

    protected static final ImageView MAP_BACKGROUND = new ImageView(
            new Image(Main.getMapBackGround(), 960, 1280, false, false));


    protected enum Difficulty {
        EASY, MEDIUM, HARD;
    };
    protected static String playerName;
    protected static Difficulty setDifficulty;
    protected static int credits;
    protected static int skillPoints;
    /**
     * primary.Skill levels
     */

    protected static Skill pilotSkill = new Skill(0);
    protected static Skill fighterSkill = new Skill(0);
    protected static Skill merchantSkill = new Skill(0);
    protected static Skill engineerSkill = new Skill(0);
    public abstract Parent build();

    public void setStage(SceneBuilder b) {
        NewGame.getTheStage().getScene().setRoot(b.build());
    }

    public static void startStage() {

        NewGame.getTheStage().setScene(new Scene(new WelcomeScene().build()));
    }



}
