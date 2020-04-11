package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import primary.Item;
import primary.Region;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SPEncounterScene extends SceneLoader {

    @FXML
    private StackPane backgroundPane;
    @FXML
    private Text demandedItems;
    private Random randObj;
    private BackgroundImage back;
    {
        try {
            back = new BackgroundImage(

                    new Image(new File("src/resources/images/map_background.jpg")
                            .toURI().toURL().toString()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            System.out.println("malformued URL expetion at mapscene line 41");
        }
    }
    private List<Item> removedItems;
    private Region intendedNextRegion;


    @FXML
    public void initialize() {
        backgroundPane.setBackground(new Background(back));
        removedItems = new ArrayList<>(3);
        randObj = new Random();
        intendedNextRegion = selectedLocation;
        generateScene();

    }


    @Override
    public Parent build() {
        FXMLLoader p =  new FXMLLoader();
        p.setController(this);
        try {
            return p.load(new File("src/resources/SPEncounterScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void generateScene() {
        demandedItems.setText("Demanded Items:\n" + getItemsDemanded());
    }

    private String getItemsDemanded() {
        try {
            List<Item> refList = currentShip.getItems();
            StringBuilder ret = new StringBuilder();
            if (refList.size() == 0) {

                throw new Exception("SPEncounter should not happen if player has no inventory :(");
            }
            for (int i = 0; i <= setDifficulty.ordinal() && refList.size() != 0; i++) {
                // will remove more if space and difficult
                if (randObj.nextDouble() > 0.5 || removedItems.size() == 0) {
                    //coin flips for chances, more difficulty is more chances
                    removedItems.add(refList.remove(randObj.nextInt(refList.size())));
                    ret.append(removedItems.get(i).getName()).append("\n");
                }
            }
            return ret.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error!";
    }


    @FXML
    public void runAction() {
        // loose fuel
        if (currentShip.getFuel()
                - (100 * randObj.nextDouble()
                * (setDifficulty.ordinal() + 1)) < 0) {
            // amount of fuel lost will be slightly random
            currentShip.setFuel(0);
        } else {
            currentShip.setFuel(
                    (int) (currentShip.getFuel()
                            - (100 * randObj.nextDouble() * (setDifficulty.ordinal() + 1))));
        }

        if (player.getPilotSkill().skillCheck(setDifficulty.ordinal())) {
            for (Item i: removedItems) {
                player.getShip().getItems().add(i); // give back items
            }
            returnToRegion("ESCAPED!", "Your Supreme pilot uses his undeniable style, and "
                    + "dope manuvers to escape the police, although you burnt some "
                    + "fuel in the process and you were not"
                    + " able to make it to " + intendedNextRegion.getName());
        } else {
            failedResistence();
        }

    }

    private void failedResistence() {
        int extraFine = 1000 * setDifficulty.ordinal() + (int) (randObj.nextDouble() * 500);
        int extraDamage = (int) (player.getShip().getHp()
                * (1 - 0.1 * setDifficulty.ordinal()));
        player.setCredits(player.getCredits() - extraFine); // additional fine
        player.getShip().setHp(extraDamage); // health will be decreased a certain percent
        returnToRegion("DEFEAT!", "Despite your best efforts, "
                + "the police manage to subdue both you and your crew costing an additional: "
                + extraFine + " credits in fines. Your ship is also damaged down to: "
                + extraDamage + " HP, you also were not able to make it to "
                + intendedNextRegion.getName());
    }

    @FXML
    public void fightAction() {
        if (player.getFighterSkill().skillCheck(setDifficulty.ordinal())) {
            for (Item i: removedItems) {
                player.getShip().getItems().add(i); // give back items
            }
            // amount of fuel lost will be slightly random
            procedeToRegion("VICTORY!", "you and your crew valiantly defend the ship,"
                    + " and manage to fight off the police in the resulting battle.");
        } else {
            failedResistence();
        }

    }

    @FXML
    public void forfeitAction() {
        procedeToRegion("Forfeit!", "your crew hands over the cargo, and the police "
                + "leave you all the lesser ... Coward "); // stuff is already removed
    }


    private void procedeToRegion(String title, String message) {
        selectedLocation.getRegionMarket().generateMarket(selectedLocation);
        currentLocation = selectedLocation;
        currentLocation.setBeenVisited(true);
        AfterEncounterScene.setInput(title, 0);
        AfterEncounterScene.setInput(message, 1);
        setStage(new AfterEncounterScene());
    }
    private void returnToRegion(String title, String message) {

        // need to refund fuel
        currentShip.setFuel(currentShip.getFuel() + costToSelectedLocation);
        selectedLocation = currentLocation;
        AfterEncounterScene.setInput(title, 0);
        AfterEncounterScene.setInput(message, 1);
        setStage(new AfterEncounterScene());
    }
}
