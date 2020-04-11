package primary.scenes;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import primary.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public abstract Parent build();

    public void setStage(SceneLoader b) {
        NewGame.getTheStage().getScene().setRoot(b.build());
    }

    public static void startStage() {
        NewGame.getTheStage().setScene(new Scene(new WelcomeScene().build()));
    }

    protected HBox generateStatsBar() {

        HBox statsBar = new HBox();

        Text creditsInfo = new Text("Credits: " + player.getCredits());
        creditsInfo.setId("statsBar");

        Text spacing1 = new Text(" | ");
        spacing1.setId("statsBar");

        Text pilotInfo = new Text("Pilot: " + player.getPilotSkill().getValue());
        pilotInfo.setId("statsBar");

        Text fighterInfo = new Text("Fighter: " + player.getFighterSkill().getValue());
        fighterInfo.setId("statsBar");

        Text merchantInfo = new Text("Merchant: " + player.getMerchantSkill().getValue());
        merchantInfo.setId("statsBar");

        Text engineerInfo = new Text("Engineer: " + player.getEngineerSkill().getValue());
        engineerInfo.setId("statsBar");

        Text spacing2 = new Text(" | ");
        spacing2.setId("statsBar");

        Text shipName = new Text("Ship: " + currentShip.getName());
        shipName.setId("statsBar");

        Text shipFuel = new Text("Fuel: " + currentShip.getFuel() + "/"
                + currentShip.getFuelCapacity());
        shipFuel.setId("statsBar");

        Text shipHealth = new Text("HP: " + currentShip.getHp() + "/"
                + currentShip.getMaxHp());
        shipHealth.setId("statsBar");

        Text shipAttack = new Text("Attack: " + currentShip.getAttack());
        shipAttack.setId("statsBar");

        Text shipCapacity = new Text("Capacity: " + currentShip.getItems().size()
                + "/" + currentShip.getCargo());
        shipCapacity.setId("statsBar");

        Text shipUpgrades = new Text("Upgrades: " + currentShip.getUpgrades().size() + "/"
                + currentShip.getUpgradeSlots());
        shipUpgrades.setId("statsBar");

        statsBar.getChildren().addAll(creditsInfo, spacing1, pilotInfo,
                fighterInfo, merchantInfo, engineerInfo,
                spacing2, shipName, shipFuel, shipHealth, shipAttack, shipCapacity, shipUpgrades);

        statsBar.setAlignment(Pos.CENTER);
        statsBar.setSpacing(10);
        return statsBar;

    }





}
