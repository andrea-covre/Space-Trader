package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import primary.Trader;


import java.io.File;
import java.io.IOException;


public class NameSelectionScene extends SceneLoader {

    @FXML
    private TextField field;

    @FXML
    public void initialize() {
        player.setPlayerName(null);
    }
    @Override
    public Parent build() {
        FXMLLoader p =  new FXMLLoader();
        p.setController(this);
        try {
            return p.load(new File("src/resources/NameSelectionScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    private void handleContinue(MouseEvent mouseEvent) {
            try {
                if (field.getText() == null || field.getText().trim().isEmpty()) {
                    field.clear();
                    field.setPromptText("Enter a valid name");
                } else {
                    player.setPlayerName(field.getText().trim());
                    field.clear();
                    setStage(new SkillsLevelSelectionScene());
                }
            } catch (Throwable f) {
                f.printStackTrace();
            }
    }
    @FXML
    private void handleBack(MouseEvent mouseEvent) {
        try {
            setStage(new DifficultyScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
