package primary.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.File;
import java.io.IOException;


public class NameSelectionScene extends SceneLoader {

    public Text title;
    @FXML
    private TextField field;

    @FXML
    public void initialize() {
        player.setPlayerName(null);
    }
    @Override
    public Parent build() {
        p.setController(this);
        try {
            return p.load(new File("src/resources/NameSelectionScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleContinue() {
        if (player == null) {
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
    }

        @FXML
    public void handleBack(){
        try {
            setStage(new DifficultyScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
