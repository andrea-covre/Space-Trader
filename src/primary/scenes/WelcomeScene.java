package primary.scenes;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;


public class WelcomeScene extends SceneLoader {
    @FXML
    private Text title;
    @FXML
    private Button newGame;
    @FXML
    private Text titleMiddle;

    @FXML
    public void initialize() {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> {
            titleMiddle.setVisible(false);
            title.setVisible(true);
            newGame.setVisible(true);
            newGame.setDisable(false);
        });
        new Thread(sleeper).start();
    }
    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File(
                    "src/resources/WelcomeScene.fxml"
            ).toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    private void handleNewGame() {
        try {
            player = new Player(null, 0, 0);
            setStage(new DifficultyScene());
        } catch (Throwable f) {
            f.printStackTrace();
        }
    }
}
