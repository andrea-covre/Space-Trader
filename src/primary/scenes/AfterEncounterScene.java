package primary.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import primary.Region;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class AfterEncounterScene extends SceneLoader {

    @FXML
    public StackPane backgroundPane;

    @FXML
    public Text title;
    @FXML
    public Text message;
    @FXML
    public Button contButton;
    private BackgroundImage back;
    public static String inputs[] = new String[2];




    @FXML
    public void initialize() throws Exception {
        try {
            back = new BackgroundImage(
                    new Image(new File("src/resources/images/map_background.jpg").toURI().toURL().toString()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        backgroundPane.setBackground(new Background(back));
        generateScene();
    }

    private void generateScene() {
        title.setText(inputs[0]);
        message.setText(inputs[1]);
    }

    @Override
    public Parent build() {
        FXMLLoader p =  new FXMLLoader();
        p.setController(this);
        try {
            return p.load(new File("src/resources/AfterEncounterScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void contAction(ActionEvent actionEvent) {
        setStage(new MapScene());
    }


}
