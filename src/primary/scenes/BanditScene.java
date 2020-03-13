package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

public class BanditScene extends SceneLoader {
    private BackgroundImage back;

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
    @FXML
    public void initialize() {
        {
            try {
                back = new BackgroundImage(
                        new Image(new File("src/resources/images/map_background.jpg").toURI().toURL().toString(),
                                1500, 1500, false, false),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                main.setBackground(new Background(back));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void handlePay(MouseEvent mouseEvent) {
        if (player.getCredits() < 2000) {
            if (player.getShip().getItems() == null) {
                title.setText("You couldn't pay the bandit and your ship took damage");
                npcText.setText("Bandit: You don't have money or items? I'm damaging your ship.");
                player.getShip().setHp(player.getShip().getHp() - 3);
            } else {
                title.setText("You couldn't pay the bandit and lost your items");
                npcText.setText("Bandit: That's not enough money, I'm taking your items");
                player.getShip().setItems(null);
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
    }
    @FXML
    public void handleFlee(MouseEvent mouseEvent) {
        if (fleeButton.getText() == "Continue") {
            try {
                npcText.setText("Bandit: RUN YO POCKETS!!!");
                fleeButton.setText("Flee");
                title.setText("A Bandit Has Appeared!");
                fleeButton.setTranslateX(350);
                payButton.setVisible(true);
                payButton.setDisable(false);
                fightButton.setVisible(true);
                fightButton.setDisable(false);
                setStage(new MapScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else {
            Random r = new Random();
            int random = r.nextInt(100);
            if (random < (20 + (5 * player.getPilotSkill().getValue()))) {
                title.setText("You successfully fled from the bandit");
                npcText.setText("Bandit: HEY GET BACK HERE!!!");
            } else {
                title.setText("You failed to flee and lost your credits and your ship took damage");
                npcText.setText("Bandit: Where do you think your going? You'll pay for that.");
                player.setCredits(0);
                player.getShip().setHp(player.getShip().getHp() - 3);
            }
            payButton.setVisible(false);
            payButton.setDisable(true);
            fightButton.setVisible(false);
            fightButton.setDisable(true);
            fleeButton.setTranslateX(250);
            fleeButton.setText("Continue");
        }
    }
    @FXML
    public void handleFight(MouseEvent mouseEvent) {
        Random r = new Random();
        int random = r.nextInt(100);
        if (random < (10 + (5 * player.getFighterSkill().getValue()))) {
            int random2 = r.nextInt(1000);
            random2 = (random2/100) * 100;
            title.setText("You decided to fight the bandit and won " + random2 + " credits");
            npcText.setText("Bandit: Woah, chill out man. Here take this.");
            player.setCredits(player.getCredits() + random2);
        } else {
            title.setText("You failed to beat the bandit and lost your credits and your ship took damage");
            npcText.setText("Bandit: Your so weak. You'll pay for that.");
            player.setCredits(0);
            player.getShip().setHp(player.getShip().getHp() - 3);
        }
        payButton.setVisible(false);
        payButton.setDisable(true);
        fightButton.setVisible(false);
        fightButton.setDisable(true);
        fleeButton.setTranslateX(250);
        fleeButton.setText("Continue");
    }
}
