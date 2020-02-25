package primary.scenes;

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

public class DifficultyScene extends SceneLoader {
    //Difficulty Scene
    private Button easy = new Button("Easy");
    private Button medium = new Button("Medium");
    private Button hard = new Button("Hard");

    @Override
    public Parent build() {
        return difficulty();
    }


    private Pane difficulty() {
        /*
         * Base layout
         */
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Text title = new Text("Pick a Difficulty");
        StackPane stackpane = new StackPane();

        /*
         *
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /*
         * Text
         */
        title.setFont(Font.font("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);
        easy.setFont(Font.font("Comic Sans MS", 60));
        easy.setStyle("-fx-background-color: transparent");
        easy.setTextFill(Color.RED);
        medium.setFont(Font.font("Comic Sans MS", 60));
        medium.setStyle("-fx-background-color: transparent");
        medium.setTextFill(Color.RED);
        hard.setFont(Font.font("Comic Sans MS", 60));
        hard.setStyle("-fx-background-color: transparent");
        hard.setTextFill(Color.RED);
        hbox.getChildren().addAll(easy, medium, hard);
        hbox.setAlignment(Pos.CENTER);
        hbox2.getChildren().add(title);
        hbox2.setAlignment(Pos.TOP_CENTER);

        /*
         * Difficulty
         */
        easy.setOnAction(e -> {
            try {
                setDifficulty = Difficulty.EASY;
                credits = 5000;
                skillPoints = 8;
                setStage(new NameSelectionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        medium.setOnAction(e -> {
            try {
                setDifficulty = Difficulty.MEDIUM;
                credits = 3000;
                skillPoints = 5;
                setStage(new NameSelectionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        hard.setOnAction(e -> {
            try {
                setDifficulty = Difficulty.HARD;
                credits = 1000;
                skillPoints = 3;
                setStage(new NameSelectionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(BACKGROUND, hbox2, hbox);
        return pane;
    }
}
