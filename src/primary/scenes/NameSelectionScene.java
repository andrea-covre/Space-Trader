package primary.scenes;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class NameSelectionScene extends SceneLoader {
    private  Button sceneButton1 = new Button("New Game");
    private  Button sceneButton2 = new Button("Continue");
    private  Button backToScene1 = new Button("Back");

    @Override
    public Parent build() {
        return nameSelection();
    }


    private Pane nameSelection() {
        TextField field = new TextField();

        sceneButton1.setOnAction(e -> {
            try {
                setStage(new DifficultyScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        /*
         * Base layout
         */
        BorderPane pane = new BorderPane();
        StackPane stackPane = new StackPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        /*
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /*
         * Text
         */
        Text title = new Text("What is your name Trader?");
        title.setFont(Font.font("Comic Sans MS", 80));
        title.setFill(Color.YELLOW);

        /*
         * Text field to input the avatar's name
         */
        field.setPrefHeight(90);
        field.setPrefWidth(20);
        field.setPadding(new Insets(30, 30, 30, 30));
        field.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        field.setFont(Font.font("Comic Sans MS", 80));
        field.setAlignment(Pos.CENTER);

        /*
         * Buttons style
         */
        sceneButton2.setFont(Font.font("Comic Sans MS", 60));
        sceneButton2.setStyle("-fx-background-color: transparent");
        sceneButton2.setTextFill(Color.RED);
        backToScene1.setFont(Font.font("Comic Sans MS", 60));
        backToScene1.setStyle("-fx-background-color: transparent");
        backToScene1.setTextFill(Color.RED);

        /*
         * Name Selection
         */

        sceneButton2.setOnAction(e -> {
            if (playerName == null) {
                try {
                    if (field.getText() == null || field.getText().trim().isEmpty()) {
                        field.clear();
                        field.setPromptText("Enter a valid name");
                    } else {
                        playerName = field.getText().trim();
                        field.clear();
                        field.setPromptText("Works. Name is: " + playerName);
                    }
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            } else {
                try {
                    setStage(new SkillsLevelSelectionScene());
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(BACKGROUND, hbox, vbox);
        pane.setCenter(stackPane);
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
            hbox.setAlignment(Pos.TOP_CENTER);
            vbox.getChildren().addAll(field, sceneButton2, backToScene1);
        });
        new Thread(sleeper).start();
        return pane;
    }

}
