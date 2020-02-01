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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class represents the initial configuration for a new game
 */
public class NewGame extends Application {
    /**
     * Nodes that are important for scene changing:
     */
    //New Game Scene
    private static Button sceneButton1 = new Button("New Game");

    //Difficulty Scene
    private static Button easy = new Button("Easy");
    private static Button medium = new Button("Medium");
    private static Button hard = new Button("Hard");

    //Name Selection Scene
    private static Button sceneButton2 = new Button("Continue");
    private static Button backToScene1 = new Button("Back");
    private static TextField field = new TextField();

    /**
     * Skill Level Selection Scene
     */
    //Pilot
    private static Button pilotUp = new Button("⬆");
    private static Button pilotDown = new Button("⬇");

    //Fighter
    private static Button fighterUp = new Button("⬆");
    private static Button fighterDown = new Button("⬇");

    //Merchant
    private static Button merchantUp = new Button("⬆");
    private static Button merchantDown = new Button("⬇");

    //Engineer
    private static Button engineerUp = new Button("⬆");
    private static Button engineerDown = new Button("⬇");

    //Navigation
    private static Button startGame = new Button("Start");
    private static Button backToNameSelection = new Button("Back");

    /**
     * Variables that represent character sheet
     */
    private static String playerName;
    private static int difficulty;
    private static int credits;
    private static int skillPoints;

    /**
     * Skill levels
     */
    private int pilotSkill = 0;
    private int fighterSkill = 0;
    private int merchantSkill = 0;
    private int engineerSkill = 0;

    /**
     * Graphics
     */
    private static final ImageView BACKGROUND = new ImageView(
            new Image(Main.backGround, 960, 1280, false, false));

    /**
     * New Game/Welcome Scene
     * @return Welcome Pane
     */
    private static Pane welcome()  {
        /**
         * Base layout
         */
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Text title = new Text("SPACE TRADER");
        StackPane stackpane = new StackPane();

        /**
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
         * Buttons
         */
        hbox.setAlignment(Pos.CENTER);
        sceneButton1.setFont(Font.font("Comic Sans MS", 60));
        sceneButton1.setStyle("-fx-background-color: transparent");
        sceneButton1.setTextFill(Color.RED);
        hbox2.setAlignment(Pos.CENTER);

        /**
         * Text
         */
        title.setFont(Font.font("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);
        hbox2.getChildren().add(title);

        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(BACKGROUND, hbox2, hbox);
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
     */
    private static Pane difficulty() {
        /**
         * Base layout
         */
        BorderPane pane = new BorderPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        Text title = new Text("Pick a Difficulty");
        StackPane stackpane = new StackPane();

        /**
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
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

        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(BACKGROUND, hbox2, hbox);
        return pane;
    }

    /**
     * nameSelection Scene where the player inputs their avatar's name
     * @return the name selection pane
     */
    private static Pane nameSelection() {
        /**
         * Base layout
         */
        BorderPane pane = new BorderPane();
        StackPane stackPane = new StackPane();
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        /**
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
         * Text
         */
        Text title = new Text("What is your name Trader?");
        title.setFont(Font.font("Comic Sans MS", 80));
        title.setFill(Color.YELLOW);

        /**
         * Text field to input the avatar's name
         */
        field.setPrefHeight(90);
        field.setPrefWidth(20);
        field.setPadding(new Insets(30, 30, 30, 30));
        field.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        field.setFont(Font.font("Comic Sans MS", 80));
        field.setAlignment(Pos.CENTER);

        /**
         * Buttons style
         */
        sceneButton2.setFont(Font.font("Comic Sans MS", 60));
        sceneButton2.setStyle("-fx-background-color: transparent");
        sceneButton2.setTextFill(Color.RED);
        backToScene1.setFont(Font.font("Comic Sans MS", 60));
        backToScene1.setStyle("-fx-background-color: transparent");
        backToScene1.setTextFill(Color.RED);

        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(BACKGROUND, hbox, vbox);
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
                vbox.getChildren().addAll(field, sceneButton2, backToScene1);
            }
        });
        new Thread(sleeper).start();
        return pane;
    }

    /**
     * Scene where the player chooses his skills's initial level
     * @return the pane where the player sets the skills initial level
     */
    private Pane skillsLevelSelection() {
        /**
         * Graphic constants
         */
        int skillNameFontSize = 50;
        int skillLevelFontSize = 35;
        int arrowsSize = 70;
        String arrowsFont = "Verdana";

        /**
         * Skill Nodes
         */
        //Nodes to display skill name and setting the style
        Text pilotTitle = new Text("Pilot");
        pilotTitle.setFont(Font.font("Comic Sans MS", skillNameFontSize));
        pilotTitle.setFill(Color.YELLOW);

        Text fighterTitle = new Text("Fighter");
        fighterTitle.setFont(Font.font("Comic Sans MS", skillNameFontSize));
        fighterTitle.setFill(Color.YELLOW);

        Text merchantTitle = new Text("Merchant");
        merchantTitle.setFont(Font.font("Comic Sans MS", skillNameFontSize));
        merchantTitle.setFill(Color.YELLOW);

        Text engineerTitle = new Text("Engineer");
        engineerTitle.setFont(Font.font("Comic Sans MS", skillNameFontSize));
        engineerTitle.setFill(Color.YELLOW);


        //Nodes to display skill level and setting the style
        Text pilotLevelDisplay = new Text(Integer.toString(pilotSkill));
        pilotLevelDisplay.setFont(Font.font("Comic Sans MS", skillLevelFontSize));
        pilotLevelDisplay.setFill(Color.YELLOW);

        Text fighterLevelDisplay = new Text(Integer.toString(fighterSkill));
        fighterLevelDisplay.setFont(Font.font("Comic Sans MS", skillLevelFontSize));
        fighterLevelDisplay.setFill(Color.YELLOW);

        Text merchantLevelDisplay = new Text(Integer.toString(merchantSkill));
        merchantLevelDisplay.setFont(Font.font("Comic Sans MS", skillLevelFontSize));
        merchantLevelDisplay.setFill(Color.YELLOW);

        Text engineerLevelDisplay = new Text(Integer.toString(engineerSkill));
        engineerLevelDisplay.setFont(Font.font("Comic Sans MS", skillLevelFontSize));
        engineerLevelDisplay.setFill(Color.YELLOW);

        /**
         * Nodes to display skill points available
         */
        Text skillPointsDisplay = new Text("Skill points: " + Integer.toString(skillPoints));
        skillPointsDisplay.setFont(Font.font("Comic Sans MS", skillLevelFontSize));
        skillPointsDisplay.setFill(Color.YELLOW);

        /**
         * Style of navigation buttons
         */
        //Start Game
        startGame.setFont(Font.font("Comic Sans MS", skillLevelFontSize + 40));
        startGame.setStyle("-fx-background-color: transparent");
        startGame.setTextFill(Color.YELLOW);

        //Back
        backToNameSelection.setFont(Font.font("Comic Sans MS", skillLevelFontSize));
        backToNameSelection.setStyle("-fx-background-color: transparent");
        backToNameSelection.setTextFill(Color.YELLOW);

        /**
         * Base Layout
         */
        BorderPane pane = new BorderPane();

        /**
         * Skill layouts
         */
        //Pilot layout
        HBox pilotLevelHbox = new HBox();
        pilotLevelHbox.setAlignment(Pos.CENTER);
        pilotLevelHbox.getChildren().addAll(pilotUp, pilotLevelDisplay, pilotDown);

        VBox pilotLevelVBox = new VBox();
        pilotLevelVBox.setAlignment(Pos.CENTER);
        pilotLevelVBox.getChildren().addAll(pilotTitle, pilotLevelHbox);

        //Fighter layout
        HBox fighterLevelHbox = new HBox();
        fighterLevelHbox.setAlignment(Pos.CENTER);
        fighterLevelHbox.getChildren().addAll(fighterUp, fighterLevelDisplay, fighterDown);

        VBox fighterLevelVBox = new VBox();
        fighterLevelVBox.setAlignment(Pos.CENTER);
        fighterLevelVBox.getChildren().addAll(fighterTitle, fighterLevelHbox);

        //Merchant layout
        HBox merchantLevelHbox = new HBox();
        merchantLevelHbox.setAlignment(Pos.CENTER);
        merchantLevelHbox.getChildren().addAll(merchantUp, merchantLevelDisplay, merchantDown);

        VBox merchantLevelVBox = new VBox();
        merchantLevelVBox.setAlignment(Pos.CENTER);
        merchantLevelVBox.getChildren().addAll(merchantTitle, merchantLevelHbox);

        //Engineer layout
        HBox engineerLevelHbox = new HBox();
        engineerLevelHbox.setAlignment(Pos.CENTER);
        engineerLevelHbox.getChildren().addAll(engineerUp, engineerLevelDisplay, engineerDown);

        VBox engineerLevelVBox = new VBox();
        engineerLevelVBox.setAlignment(Pos.CENTER);
        engineerLevelVBox.getChildren().addAll(engineerTitle, engineerLevelHbox);

        //Condesing all the "skillVBoxes" in a TilePane
        TilePane skillsTiles = new TilePane();
        skillsTiles.setAlignment(Pos.CENTER);
        skillsTiles.getChildren().addAll(pilotLevelVBox, fighterLevelVBox, merchantLevelVBox, engineerLevelVBox);
        skillsTiles.setHgap(60);

        //Putting skillTiles into a VBOX
        VBox skillsInterface = new VBox();
        skillsInterface.getChildren().addAll(skillsTiles, skillPointsDisplay, new Text(""),
                startGame, backToNameSelection);
        skillsInterface.setAlignment(Pos.CENTER);

        //Merging structure with the background
        pane.getChildren().add(BACKGROUND);
        pane.setCenter(skillsInterface);


        /**
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
         * Text
         */
        //Scene title
        Text title = new Text("Skills");
        title.setFont(Font.font("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);

        /**
         * Arrows' Style
         */
        pilotUp.setFont(Font.font(arrowsFont, arrowsSize));
        pilotUp.setStyle("-fx-background-color: transparent");
        pilotUp.setTextFill(Color.GREEN);

        pilotDown.setFont(Font.font(arrowsFont, arrowsSize));
        pilotDown.setStyle("-fx-background-color: transparent");
        pilotDown.setTextFill(Color.RED);

        fighterUp.setFont(Font.font(arrowsFont, arrowsSize));
        fighterUp.setStyle("-fx-background-color: transparent");
        fighterUp.setTextFill(Color.GREEN);

        fighterDown.setFont(Font.font(arrowsFont, arrowsSize));
        fighterDown.setStyle("-fx-background-color: transparent");
        fighterDown.setTextFill(Color.RED);

        merchantUp.setFont(Font.font(arrowsFont, arrowsSize));
        merchantUp.setStyle("-fx-background-color: transparent");
        merchantUp.setTextFill(Color.GREEN);

        merchantDown.setFont(Font.font(arrowsFont, arrowsSize));
        merchantDown.setStyle("-fx-background-color: transparent");
        merchantDown.setTextFill(Color.RED);

        engineerUp.setFont(Font.font(arrowsFont, arrowsSize));
        engineerUp.setStyle("-fx-background-color: transparent");
        engineerUp.setTextFill(Color.GREEN);

        engineerDown.setFont(Font.font(arrowsFont, arrowsSize));
        engineerDown.setStyle("-fx-background-color: transparent");
        engineerDown.setTextFill(Color.RED);


        return pane;
    }

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        sceneButton1.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(difficulty()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        /**
         * Difficulty
         */
        easy.setOnAction(e -> {
            try {
                credits = 5000;
                skillPoints = 8;
                primaryStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        medium.setOnAction(e -> {
            try {
                credits = 3000;
                skillPoints = 5;
                primaryStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        hard.setOnAction(e -> {
            try {
                credits = 1000;
                skillPoints = 3;
                primaryStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        /**
         * Name Selection
         */
        backToScene1.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(difficulty()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
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
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        /**
         * Skills Scene
         */

        //pilot
        pilotUp.setOnAction(e -> {
            if (skillPoints > 0) {
                skillPoints--;
                pilotSkill++;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        pilotDown.setOnAction(e -> {
            if (pilotSkill > 0) {
                skillPoints++;
                pilotSkill--;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        //Fighter
        fighterUp.setOnAction(e -> {
            if (skillPoints > 0) {
                skillPoints--;
                fighterSkill++;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        fighterDown.setOnAction(e -> {
            if (fighterSkill > 0) {
                skillPoints++;
                fighterSkill--;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        //Merchant
        merchantUp.setOnAction(e -> {
            if (skillPoints > 0) {
                skillPoints--;
                merchantSkill++;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        merchantDown.setOnAction(e -> {
            if (merchantSkill > 0) {
                skillPoints++;
                merchantSkill--;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        //Engineer
        engineerUp.setOnAction(e -> {
            if (skillPoints > 0) {
                skillPoints--;
                engineerSkill++;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        engineerDown.setOnAction(e -> {
            if (engineerSkill > 0) {
                skillPoints++;
                engineerSkill--;
                try {
                    primaryStage.setScene(new Scene(skillsLevelSelection()));
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            }
        });

        //Back button
        backToNameSelection.setOnAction(e -> {
            skillPoints = skillPoints + pilotSkill + merchantSkill + fighterSkill + engineerSkill;
            pilotSkill = 0;
            merchantSkill = 0;
            fighterSkill = 0;
            engineerSkill = 0;
            try {
                field.setPromptText(playerName);
                primaryStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }

        });




        primaryStage.setScene(new Scene(welcome()));
        primaryStage.setTitle("Space trader");
        primaryStage.setResizable(true);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.show();
    }
}
