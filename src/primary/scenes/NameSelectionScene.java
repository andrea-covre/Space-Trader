package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.IOException;


public class NameSelectionScene extends SceneLoader {

    @FXML
    private TextField field;


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
    public void handleContinue(MouseEvent mouseEvent) {
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
    public void handleBack(MouseEvent mouseEvent) {
        try {
            setStage(new WelcomeScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
