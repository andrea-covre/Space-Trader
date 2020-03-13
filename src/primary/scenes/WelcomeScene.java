package primary.scenes;

import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class WelcomeScene extends SceneLoader {
    private  Button sceneButton1 = new Button("New Game");
    private  Button backToScene1 = new Button("Back");
    @Override
    public Parent build() {
        return welcome();
    }

    private Pane welcome()  {
        /*
         * Base layout
         */
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Text title = new Text("SPACE TRADER");
        StackPane stackpane = new StackPane();

        /*
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /*
         * Buttons
         */
        hbox.setAlignment(Pos.CENTER);
        sceneButton1.setFont(Font.font("Comic Sans MS", 60));
        sceneButton1.setStyle("-fx-background-color: transparent");
        sceneButton1.setTextFill(Color.RED);
        hbox2.setAlignment(Pos.CENTER);

        sceneButton1.setOnAction(e -> {
            try {
                setStage(new NameSelectionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        backToScene1.setOnAction(e -> {
            try {
                setStage(new NameSelectionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        /*
         * Text
         */
        title.setFont(Font.font("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);
        hbox2.getChildren().add(title);

        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(BACKGROUND, hbox2, hbox);
        Task<Void> sleeper = new Task<Void>() {
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
            hbox.getChildren().add(sceneButton1);
            hbox2.setAlignment(Pos.TOP_CENTER);
        });
        new Thread(sleeper).start();

        return pane;
    }


}
