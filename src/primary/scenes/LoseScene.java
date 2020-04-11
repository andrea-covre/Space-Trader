package primary.scenes;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoseScene extends SceneLoader {

    @FXML
    private Text title1;
    @FXML
    private Text title2;
    @FXML
    private Text title3;
    @FXML
    private Text title4;
    @FXML
    private Text developer1;
    @FXML
    private Text developer2;
    @FXML
    private Text developer3;
    @FXML
    private Text developer4;
    @FXML
    private Text developer5;
    @FXML
    private Text subtitle1;
    @FXML
    private Text subtitle4;
    private ArrayList<Text> list = new ArrayList<>();
    private SequentialTransition seq = new SequentialTransition();

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File(
                    "src/resources/LoseScene.fxml"
            ).toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void initialize() {
        winCredits();
    }
    private void winCredits() {
        list.add(title1);
        list.add(subtitle1);
        animation(list);
        list.clear();
        list.add(title2);
        animation(list);
        list.clear();
        list.add(title3);
        list.add(developer1);
        list.add(developer2);
        list.add(developer3);
        list.add(developer4);
        list.add(developer5);
        animation(list);
        list.clear();
        list.add(title4);
        list.add(subtitle4);
        animation(list);
        seq.play();
        seq.statusProperty().addListener(new ChangeListener<Animation.Status>() {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> observableValue,
                                Animation.Status oldValue, Animation.Status newValue) {
                if (newValue == Animation.Status.STOPPED) {
                    setStage(new WelcomeScene());
                }
            }
        });
    }
    private void animation(ArrayList<Text> list) {
        for (int i = 0; i < list.size(); i++) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), list.get(i));
            fade.setFromValue(0.0);
            fade.setToValue(1.0);
            seq.getChildren().add(fade);
        }
        seq.getChildren().add(new PauseTransition(Duration.millis(2000)));
        for (int i = 0; i < list.size(); i++) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), list.get(i));
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            seq.getChildren().add(fade);
        }
    }
}
