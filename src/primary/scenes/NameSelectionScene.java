package primary.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;


public class NameSelectionScene extends SceneLoader {

    public Text title;
    @FXML
    private TextField field;
    public void initialize() {
        title.setText("What is your name Trader?");
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

    @FXML
    public void handleContinue() {
        if (player == null) {
            try {
                if (field.getText() == null || field.getText().trim().isEmpty()) {
                    field.clear();
                    field.setPromptText("Enter a valid name");
                } else {
                    player = new Player(field.getText().trim(), 0, 0);
                    field.clear();
                    field.setPromptText("Works. Name is: " + player.getPlayerName());
                }
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else {
            try {
                setStage(new DifficultyScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        }
    }
    @FXML
    public void handleBack() {
        try {
            setStage(new WelcomeScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
