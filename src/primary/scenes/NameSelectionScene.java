package primary.scenes;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import java.io.File;
import java.io.IOException;


public class NameSelectionScene extends SceneLoader {

    private BackgroundImage back;

    @FXML
    private StackPane main;
    @FXML
    private TextField field;
    @FXML
    private Text title;

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
    public void initialize() {
        {
            try {

                back = new BackgroundImage(
                        new Image(new File("src/resources/images/menu_background.jpg").toURI().toURL().toString(),
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
