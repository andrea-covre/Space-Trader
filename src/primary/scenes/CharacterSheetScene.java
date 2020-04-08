package primary.scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import skills.PilotSkill;

import java.io.File;
import java.io.IOException;

public class CharacterSheetScene extends SceneLoader {
    @FXML
    private Text pilotSkill;
    @FXML
    private Text fighterSkill;
    @FXML
    private Text merchantSkill;
    @FXML
    private Text engineerSkill;
    @FXML
    private Text credits;
    @FXML
    private Text difficultyText;
    @FXML
    private Text title;

    @FXML
    public void initialize() {
        pilotSkill.setText("" + player.getPilotSkill().getValue());
        fighterSkill.setText("" + player.getFighterSkill().getValue());
        merchantSkill.setText("" + player.getMerchantSkill().getValue());
        engineerSkill.setText("" + player.getEngineerSkill().getValue());
        title.setText("Welcome, " + player.getPlayerName());
        credits.setText("" + player.getCredits());
        if (setDifficulty == Difficulty.EASY) {
            difficultyText.setText("Player on Easy");
        } else if (setDifficulty == Difficulty.MEDIUM) {
            difficultyText.setText("Playing on Medium");
        } else {
            difficultyText.setText("Playing on Hard");
        }
    }

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File(
                    "src/resources/CharacterSheetScene.fxml"
            ).toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void handleStartGame(MouseEvent mouseEvent) {
        try {
            setStage(new MapScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }

    @FXML
    private void handleBack(MouseEvent mouseEvent) {
        player.resetSkill();
        try {
            setStage(new SkillsLevelSelectionScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
