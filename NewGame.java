package sample;

/**
 * Authors: Thomas Crawford
 * Version: 1.0
 */

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * This class represents the initial configuration for a new game
 */
public class NewGame extends Application {
    /**
     * Nodes that are important for scene changing
     */
    private static Button sceneButton1 = new Button("New Game");
    private static Button easy = new Button("Easy");
    private static Button medium = new Button("Medium");
    private static Button hard = new Button("Hard");
    private static Button sceneButton2 = new Button("Continue");
    private static Button Back_to_Scene_1 = new Button("Back");
    private static TextField field = new TextField();
    /**
     * Variables that represent character sheet
     */
    private static String playerName;
    private static int Credits;

    /**
     * New Game/Welcome Scene
     * @return Welcome Pane
     * @throws FileNotFoundException
     */
    private static Pane Welcome() throws FileNotFoundException {
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Text title = new Text("SPACE TRADER");
        StackPane stackpane = new StackPane();
        FileInputStream inputStream = new FileInputStream("/Users/tjcrawford/Downloads/ST_Image2.jpg");
        Image image = new Image(inputStream, 100, 100, false, false);
        ImageView iView = new ImageView(image);
        iView.fitWidthProperty().bind(pane.widthProperty());
        iView.fitHeightProperty().bind(pane.heightProperty());
        hbox.setAlignment(Pos.CENTER);
        sceneButton1.setFont(Font.font ("Comic Sans MS", 60));
        sceneButton1.setStyle("-fx-background-color: transparent");
        sceneButton1.setTextFill(Color.RED);
        hbox2.setAlignment(Pos.CENTER);
        title.setFont(Font.font ("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);
        hbox2.getChildren().add(title);
        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(iView, hbox2, hbox);
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                hbox.getChildren().add(sceneButton1);
                hbox2.setAlignment(Pos.TOP_CENTER);
            }
        });
        new Thread(sleeper).start();

        return pane;
    }

    /**
     * Difficulty Scene
     * @return Difficulty Pane
     * @throws FileNotFoundException
     */
    private static Pane Difficulty() throws FileNotFoundException {
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Text title = new Text("Pick a Difficulty");
        StackPane stackpane = new StackPane();
        FileInputStream inputStream = new FileInputStream("/Users/tjcrawford/Downloads/ST_Image2.jpg");
        Image image = new Image(inputStream, 100, 100, false, false);
        ImageView iView = new ImageView(image);
        iView.fitWidthProperty().bind(pane.widthProperty());
        iView.fitHeightProperty().bind(pane.heightProperty());
        title.setFont(Font.font ("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);
        easy.setFont(Font.font ("Comic Sans MS", 60));
        easy.setStyle("-fx-background-color: transparent");
        easy.setTextFill(Color.RED);
        medium.setFont(Font.font ("Comic Sans MS", 60));
        medium.setStyle("-fx-background-color: transparent");
        medium.setTextFill(Color.RED);
        hard.setFont(Font.font ("Comic Sans MS", 60));
        hard.setStyle("-fx-background-color: transparent");
        hard.setTextFill(Color.RED);
        hbox.getChildren().addAll(easy,medium,hard);
        hbox.setAlignment(Pos.CENTER);
        hbox2.getChildren().add(title);
        hbox2.setAlignment(Pos.TOP_CENTER);
        stackpane.getChildren().addAll(iView,hbox2,hbox);
        pane.setCenter(stackpane);
        return pane;
    }

    /**
     * Name sce
     * @return
     * @throws FileNotFoundException
     */
    private static Pane Name() throws FileNotFoundException {
        BorderPane pane = new BorderPane();
        StackPane stackPane = new StackPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        FileInputStream inputStream = new FileInputStream("/Users/tjcrawford/Downloads/ST_Image2.jpg");
        Image image = new Image(inputStream, 100, 100, false, false);
        ImageView iView = new ImageView(image);
        iView.fitWidthProperty().bind(pane.widthProperty());
        iView.fitHeightProperty().bind(pane.heightProperty());
        Text title = new Text("What is your name Trader?");
        title.setFont(Font.font ("Comic Sans MS", 80));
        title.setFill(Color.YELLOW);
        field.setPrefHeight(90);
        field.setPrefWidth(20);
        field.setPadding(new Insets(30,30,30,30));
        field.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        field.setFont(Font.font ("Comic Sans MS", 80));
        field.setAlignment(Pos.CENTER);
        sceneButton2.setFont(Font.font ("Comic Sans MS", 60));
        sceneButton2.setStyle("-fx-background-color: transparent");
        sceneButton2.setTextFill(Color.RED);
        Back_to_Scene_1.setFont(Font.font ("Comic Sans MS", 60));
        Back_to_Scene_1.setStyle("-fx-background-color: transparent");
        Back_to_Scene_1.setTextFill(Color.RED);
        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(iView, hbox, vbox);
        pane.setCenter(stackPane);
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                hbox.setAlignment(Pos.TOP_CENTER);
                vbox.getChildren().addAll(field, sceneButton2,Back_to_Scene_1);
            }
        });
        new Thread(sleeper).start();
        return pane;
    }

    public void start(Stage primaryStage) throws Exception{
        sceneButton1.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(Difficulty()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        easy.setOnAction(e -> {
            try {
                Credits = 5000;
                primaryStage.setScene(new Scene(Name()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        medium.setOnAction(e -> {
            try {
                Credits = 3000;
                primaryStage.setScene(new Scene(Name()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        hard.setOnAction(e -> {
            try {
                Credits = 1000;
                primaryStage.setScene(new Scene(Name()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        Back_to_Scene_1.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(Difficulty()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        sceneButton2.setOnAction(e -> {
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
        });
        primaryStage.setScene(new Scene(Welcome()));
        primaryStage.setTitle("Space trader");
        primaryStage.setResizable(false);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }
}
