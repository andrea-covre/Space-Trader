package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private void handleEasy(MouseEvent mouseEvent) {
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
    private void handleMedium(MouseEvent mouseEvent) {
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
    private void handleHard(MouseEvent mouseEvent) {
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
