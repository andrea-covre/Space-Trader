package primary.scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import skills.EngineerSkill;
import skills.FighterSkill;
import skills.MerchantSkill;
import skills.PilotSkill;

import java.io.File;
import java.io.IOException;

public class SkillsLevelSelectionScene extends SceneLoader {
    @FXML
    private Text skillPointsDisplay;
    @FXML
    private Text pilotLevelDisplay;
    @FXML
    private Text fighterLevelDisplay;
    @FXML
    private Text merchantLevelDisplay;
    @FXML
    private Text engineerLevelDisplay;

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
    private void handlePilotUp() {
        new PilotSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handlePilotDown() {
        new PilotSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleFighterUp() {
        new FighterSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleFighterDown() {
        new FighterSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleMerchantUp() {
        new MerchantSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleMerchantDown() {
        new MerchantSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleEngineerUp() {
        new EngineerSkill(1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleEngineerDown() {
        new EngineerSkill(-1).upgrade(player);
        setStage(this);
    }

    @FXML
    private void handleBack() {
        player.resetSkill();
        try {
            setStage(new NameSelectionScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }

    @FXML
    private void handleFinish() {
        try {
            setStage(new CharacterSheetScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
