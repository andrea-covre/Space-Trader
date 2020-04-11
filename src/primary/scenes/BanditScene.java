package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import primary.Item;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

public class BanditScene extends SceneLoader {
    private BackgroundImage back;
    private boolean intendedDestination;

    @FXML
    private StackPane main;
    @FXML
    private Text npcText;
    @FXML
    private Text title;
    @FXML
    private Button payButton;
    @FXML
    private Button fleeButton;
    @FXML
    private Button fightButton;

    @Override
    public Parent build() {
        FXMLLoader p =  new FXMLLoader();
        p.setController(this);
        try {
            return p.load(new File("src/resources/BanditScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
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
    public void initialize() {
        main.setBackground(new Background(back));
    }
    @FXML
    private void handlePay() {
        if (player.getCredits() < 2000) {
            if (currentShip.getItems().size() == 0) {
                title.setText("You couldn't pay the bandit and your ship took damage");
                npcText.setText("Bandit: You don't have money or items? I'm damaging your ship.");
                currentShip.setHp(currentShip.getHp() - 3);
            } else {
                title.setText("You couldn't pay the bandit and lost your items");
                npcText.setText("Bandit: That's not enough money, I'm taking your items");
                currentShip.setItems(new ArrayList<Item>());
            }
        } else {
            title.setText("You paid the bandit 2000 credits");
            npcText.setText("Bandit: Thank you for donating to the charity. Haha.");
            player.setCredits(player.getCredits() - 2000);
        }
        payButton.setVisible(false);
        payButton.setDisable(true);
        fightButton.setVisible(false);
        fightButton.setDisable(true);
        fleeButton.setTranslateX(250);
        fleeButton.setText("Continue");
        intendedDestination = true;
    }
    @FXML
    private void handleFlee() {
        Random r = new Random();
        if (fleeButton.getText().equals("Continue")) {
            try {
                npcText.setText("Bandit: RUN YO POCKETS!!!");
                fleeButton.setText("Flee");
                title.setText("A Bandit Has Appeared!");
                fleeButton.setTranslateX(350);
                payButton.setVisible(true);
                payButton.setDisable(false);
                fightButton.setVisible(true);
                fightButton.setDisable(false);
                goToRegion();
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else {
            currentShip.setFuel(currentShip.getFuel() - r.nextInt(100
                    * (setDifficulty.ordinal() + 1)));
            if (player.getPilotSkill().skillCheck(setDifficulty.ordinal())) {
                title.setText("You successfully fled from the bandit");
                npcText.setText("Bandit: HEY GET BACK HERE!!!");
            } else {
                title.setText("You failed to flee and lost your credits and your ship took damage");
                npcText.setText("Bandit: Where do you think your going? You'll pay for that.");
                player.setCredits(0);
                currentShip.setHp(currentShip.getHp() - 3);
            }
            payButton.setVisible(false);
            payButton.setDisable(true);
            fightButton.setVisible(false);
            fightButton.setDisable(true);
            fleeButton.setTranslateX(250);
            fleeButton.setText("Continue");
            intendedDestination = false;
        }
    }

    private void goToRegion() {
        if (intendedDestination) {
            selectedLocation.getRegionMarket().generateMarket(selectedLocation);
            currentLocation = selectedLocation;
            currentLocation.setBeenVisited(true);
            if (currentShip.getHp() <= 0) {
                regionsGenerated = false;
                setStage(new LoseScene());
            } else {
                setStage(new RegionScene());
            }
        } else {
            if (currentShip.getHp() <= 0) {
                regionsGenerated = false;
                setStage(new LoseScene());
            } else {
                currentShip.setFuel(currentShip.getFuel() + costToSelectedLocation);
                selectedLocation = currentLocation;
                setStage(new RegionScene());
            }
        }
    }

    @FXML
    private void handleFight() {
        Random r = new Random();
        if (player.getFighterSkill().skillCheck(setDifficulty.ordinal())) {
            int random2 = r.nextInt(1000);
            title.setText("You decided to fight the bandit and won " + random2 + " credits");
            npcText.setText("Bandit: Woah, chill out man. Here take this.");
            player.setCredits(player.getCredits() + random2);
            intendedDestination = true;
        } else {
            title.setText("You failed to beat the bandit and lost your credits "
                    + "and your ship took damage");
            npcText.setText("Bandit: Your so weak. You'll pay for that.");
            player.setCredits(0);
            currentShip.setHp(currentShip.getHp() - 3);
            intendedDestination = false;
        }
        payButton.setVisible(false);
        payButton.setDisable(true);
        fightButton.setVisible(false);
        fightButton.setDisable(true);
        fleeButton.setTranslateX(250);
        fleeButton.setText("Continue");
    }
}
