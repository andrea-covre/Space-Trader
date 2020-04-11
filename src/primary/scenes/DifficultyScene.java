package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class DifficultyScene extends SceneLoader {
    @FXML
    private Text title;

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File(
                    "src/resources/DifficultyScene.fxml"
            ).toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    private void handleEasy() {
        try {
            setDifficulty = Difficulty.EASY;
            player.setCredits(10000);
            player.setSkillPoints(8);
            setStage(new NameSelectionScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }

    }
    @FXML
    private void handleMedium() {
        try {
            setDifficulty = Difficulty.MEDIUM;
            player.setCredits(6000);
            player.setSkillPoints(5);
            setStage(new NameSelectionScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
    @FXML
    private void handleHard() {
        try {
            setDifficulty = Difficulty.HARD;
            player.setCredits(4000);
            player.setSkillPoints(3);
            setStage(new NameSelectionScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
