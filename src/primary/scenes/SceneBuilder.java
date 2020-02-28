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

import java.util.List;

public abstract class SceneBuilder {

    //Travel
    protected static double fuelCostPerUnit = 1; //cost per unit of distance
    protected static int travelDiscountPerPilotLevel = 3; //in percentage
    protected static int costToSelectedLocation;

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

    protected HBox generateStatsBar() {
        //Creating stats bar

        HBox statsBar = new HBox();

        Text creditsInfo = new Text("Credits: " + credits);
        creditsInfo.setId("statsBar");
        creditsInfo.setFill(Color.YELLOW);

        Text spacing1 = new Text("|");
        spacing1.setId("statsBar");
        spacing1.setFill(Color.YELLOW);

        Text pilotInfo = new Text("Pilot: " + pilotSkill.getValue());
        pilotInfo.setId("statsBar");
        pilotInfo.setFill(Color.YELLOW);

        Text fighterInfo = new Text("Fighter: " + fighterSkill.getValue());
        fighterInfo.setId("statsBar");
        fighterInfo.setFill(Color.YELLOW);

        Text  merchantInfo = new Text("Merchant: " + merchantSkill.getValue());
        merchantInfo.setId("statsBar");
        merchantInfo.setFill(Color.YELLOW);

        Text engineerInfo = new Text("Engineer: " + engineerSkill.getValue());
        engineerInfo.setId("statsBar");
        engineerInfo.setFill(Color.YELLOW);

        Text spacing2 = new Text("|");
        spacing2.setId("statsBar");
        spacing2.setFill(Color.YELLOW);

        Text shipName = new Text("Ship: " + currentShip.getName());
        shipName.setId("statsBar");
        shipName.setFill(Color.YELLOW);

        Text shipHealth = new Text("HP: " + currentShip.getHp() + "/" + currentShip.getMaxHp());
        shipHealth.setId("statsBar");
        shipHealth.setFill(Color.YELLOW);

        Text shipAttack = new Text("Attack: " + currentShip.getAttack());
        shipAttack.setId("statsBar");
        shipAttack.setFill(Color.YELLOW);

        Text shipCapacity = new Text("Capacity: " + currentShip.getItems().size()
                + "/" + currentShip.getCargo());
        shipCapacity.setId("statsBar");
        shipCapacity.setFill(Color.YELLOW);

        Text upgrades = new Text("Upgrades: " + currentShip.getUpgrades().size() + "/"
                + currentShip.getUpgradeSlots());
        upgrades.setId("statsBar");
        upgrades.setFill(Color.YELLOW);



        statsBar.setSpacing(30);
        statsBar.setAlignment(Pos.CENTER);
        statsBar.getChildren()
                .addAll(creditsInfo, spacing1, pilotInfo,
                        fighterInfo, merchantInfo, engineerInfo, spacing2, shipName,
                        shipHealth, shipAttack, shipCapacity, upgrades);

        return statsBar;
    }



}
