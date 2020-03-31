package primary.scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import skills.EngineerSkill;
import skills.FighterSkill;
import skills.MerchantSkill;
import skills.PilotSkill;

import java.io.File;
import java.io.IOException;

public class SkillsLevelSelectionScene extends SceneLoader {
    @FXML
    Button pilotUp;
    @FXML
    Button pilotDown;
    @FXML
    Button fighterUp;
    @FXML
    Button fighterDown;
    @FXML
    Button merchantUp;
    @FXML
    Button merchantDown;
    @FXML
    Button engineerUp;
    @FXML
    Button engineerDown;
    @FXML
    Text skillPointsDisplay;
    @FXML
    Text pilotLevelDisplay;
    @FXML
    Text fighterLevelDisplay;
    @FXML
    Text merchantLevelDisplay;
    @FXML
    Text engineerLevelDisplay;

    @FXML
    public void initialize() {
        skillPointsDisplay.setText("Skill points: " + player.getSkillPoints());
        pilotLevelDisplay.setText("" + player.getPilotSkill().getValue());
        fighterLevelDisplay.setText("" + player.getFighterSkill().getValue());
        merchantLevelDisplay.setText("" + player.getMerchantSkill().getValue());
        engineerLevelDisplay.setText("" + player.getEngineerSkill().getValue());
    }

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File(
                    "src/resources/SkillsLevelSelectionScene.fxml"
            ).toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void handlePilotUp(MouseEvent mouseEvent) {
        new PilotSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handlePilotDown(MouseEvent mouseEvent) {
        new PilotSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleFighterUp(MouseEvent mouseEvent) {
        new FighterSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleFighterDown(MouseEvent mouseEvent) {
        new FighterSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleMerchantUp(MouseEvent mouseEvent) {
        new MerchantSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleMerchantDown(MouseEvent mouseEvent) {
        new MerchantSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleEngineerUp(MouseEvent mouseEvent) {
        new EngineerSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleEngineerDown(MouseEvent mouseEvent) {
        new EngineerSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    public void handleBack(MouseEvent mouseEvent) {
        player.resetSkill();
        try {
            setStage(new NameSelectionScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }

    @FXML
    public void handleFinish(MouseEvent mouseEvent) {
        try {
            setStage(new CharacterSheetScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
