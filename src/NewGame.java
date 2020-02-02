/**
 * Authors:
 * Thomas Crawford,
 * Calogero Cullotta
 * Andrea Covre
 * Joseph Dodd
 * Rares Cristian
 * Version: 1.1
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
    protected static Stage theStage;
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
    private enum Difficulty {
        EASY, MEDIUM, HARD;
    };
    private static String playerName;
    private static Difficulty difficulty;
    private static int credits;
    protected static int skillPoints;

    /**
     * Skill levels
     */

    private static Skill pilotSkill = new Skill(0);
    private static Skill fighterSkill = new Skill(0);
    private static Skill merchantSkill = new Skill(0);
    private static Skill engineerSkill = new Skill(0);

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
        sceneButton1.setOnAction(e -> {
            try {
                theStage.setScene(new Scene(difficulty()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        backToScene1.setOnAction(e -> {
            try {
                theStage.setScene(new Scene(difficulty()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

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
    protected static Pane difficulty() {
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

        /**
         * Difficulty
         */
        easy.setOnAction(e -> {
            try {
                difficulty = Difficulty.EASY;
                credits = 5000;
                skillPoints = 8;
                theStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        medium.setOnAction(e -> {
            try {
                difficulty = Difficulty.MEDIUM;
                credits = 3000;
                skillPoints = 5;
                theStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        hard.setOnAction(e -> {
            try {
                difficulty = Difficulty.HARD;
                credits = 1000;
                skillPoints = 3;
                theStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
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

        /**
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
                    theStage.setScene(new Scene(skillsLevelSelection()));
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
    static Pane skillsLevelSelection() {

        // setting actions for all skill leveling buttons
        pilotUp.setOnAction(new IncrSkill(pilotSkill, 1));
        pilotDown.setOnAction(new IncrSkill(pilotSkill, -1));
        fighterUp.setOnAction(new IncrSkill(fighterSkill, 1));
        fighterDown.setOnAction(new IncrSkill(fighterSkill, -1));
        merchantUp.setOnAction(new IncrSkill(merchantSkill, 1));
        merchantDown.setOnAction(new IncrSkill(merchantSkill, -1));
        engineerUp.setOnAction(new IncrSkill(engineerSkill, 1));
        engineerDown.setOnAction(new IncrSkill(engineerSkill, -1));

        //setting back button to return to name selections
        backToNameSelection.setOnAction(e -> {
            skillPoints = skillPoints + pilotSkill.getValue() + merchantSkill.getValue()
                    + fighterSkill.getValue() + engineerSkill.getValue();
            pilotSkill = new Skill(0);
            merchantSkill = new Skill(0);
            fighterSkill = new Skill(0);
            engineerSkill = new Skill(0);
            try {
                field.setPromptText(playerName);
                theStage.setScene(new Scene(nameSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        /**
         * Skill Nodes
         */
        //Nodes to display skill name
        Text pilotTitle = new Text("Pilot");
        pilotTitle.setId("skillNameFont");
        Text fighterTitle = new Text("Fighter");
        fighterTitle.setId("skillNameFont");
        Text merchantTitle = new Text("Merchant");
        merchantTitle.setId("skillNameFont");
        Text engineerTitle = new Text("Engineer");
        engineerTitle.setId("skillNameFont");

        //Nodes to display skill level
        Text pilotLevelDisplay = new Text(pilotSkill.toString());
        pilotLevelDisplay.setId("skillLevelFont");
        Text fighterLevelDisplay = new Text(fighterSkill.toString());
        fighterLevelDisplay.setId("skillLevelFont");
        Text merchantLevelDisplay = new Text(merchantSkill.toString());
        merchantLevelDisplay.setId("skillLevelFont");
        Text engineerLevelDisplay = new Text(engineerSkill.toString());
        engineerLevelDisplay.setId("skillLevelFont");

        /**
         * Nodes to display skill points available
         */
        Text skillPointsDisplay = new Text("Skill points: " + Integer.toString(skillPoints));
        skillPointsDisplay.setId("skillLevelFont");

        /**
         * Style of navigation buttons
         */
        //Start Game
        startGame.setId("skillSelectionStartFont");
        //Back
        backToNameSelection.setId("skillLevelFont");

        // Base layout
        BorderPane pane = new BorderPane();

        // background
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        //Scene title
        Text title = new Text("Skills");
        title.setId("title");

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
        skillsTiles.getChildren().addAll(pilotLevelVBox, fighterLevelVBox, merchantLevelVBox,
                engineerLevelVBox);
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
         * Arrows' Style
         */
        pilotUp.setId("arrowsUp");
        pilotDown.setId("arrowsDown");
        fighterUp.setId("arrowsUp");
        fighterDown.setId("arrowsDown");
        merchantUp.setId("arrowsUp");
        merchantDown.setId("arrowsDown");
        engineerUp.setId("arrowsUp");
        engineerDown.setId("arrowsDown");

        pane.getStylesheets().add("css/Styles.css");
        return pane;
    }


    /**
     * Character Sheet Scene
     * @return CharacterSheet Pane
     */
    private static Pane characterSheet() {
        /**
         * Base layout
         */
        BorderPane pane = new BorderPane();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        VBox vboxSkills = new VBox();
        VBox vboxCredits = new VBox();
        StackPane stackpane = new StackPane();
   

        /**
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
         * Title
         */
        Text title = new Text("Welcome, " + playerName);
        title.setFont(Font.font("Comic Sans MS", 100));
        title.setFill(Color.YELLOW);

        /* 
         * Skills
         */
        Text skill = new Text("Your Skill");
        skill.setFont(Font.font("Comic Sans MS", 75));
        skill.setFill(Color.RED);

        Text pilot = new Text("Pilot: \t\t" + pilotSkill);
        pilot.setFont(Font.font("Comic Sans MS", 50));
        pilot.setFill(Color.YELLOW);

        Text fighter = new Text("Fighter: \t\t" + fighterSkill);
        fighter.setFont(Font.font("Comic Sans MS", 50));
        fighter.setFill(Color.YELLOW);

        Text merchant = new Text("Fighter: \t\t" + merchantSkill);
        merchant.setFont(Font.font("Comic Sans MS", 50));
        merchant.setFill(Color.YELLOW);

        Text engineer = new Text("Engineer: \t" + engineerSkill);
        engineer.setFont(Font.font("Comic Sans MS", 50));
        engineer.setFill(Color.YELLOW);

        vboxSkills.getChildren().addAll(skill, pilot, fighter, merchant, engineer);
        vboxSkills.setAlignment(Pos.TOP_CENTER);

        /* 
         * Credits
         */
        Text creditText = new Text("Your Credits");
        creditText.setFont(Font.font("Comic Sans MS", 75));
        creditText.setFill(Color.RED);

        Text creditAmount = new Text(new Integer(credits).toString());
        creditAmount.setFont(Font.font("Comic Sans MS", 50));
        creditAmount.setFill(Color.YELLOW);

        vboxCredits.getChildren().addAll(creditText, creditAmount);
        vboxCredits.setAlignment(Pos.TOP_CENTER);
        vboxCredits.setSpacing(100);


        /*
         * Difficulty
         */
        String difficultyText = "";
        if (difficulty == Difficulty.EASY) {
            difficultyText = "Easy";
        } else if (difficulty == Difficulty.MEDIUM) {
            difficultyText = "Medium";
        } else {
            difficultyText = "Hard";
        } 
        Text difTitle = new Text("Playing On " + difficultyText + " Mode");
        difTitle.setFont(Font.font("Comic Sans MS", 75));
        difTitle.setFill(Color.YELLOW);

        hbox.getChildren().addAll(vboxSkills, vboxCredits);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(100);

        vbox.getChildren().addAll(title, hbox, difTitle);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50.0); 

        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(BACKGROUND, vbox);
        return pane;
    }


    /**
     *
     * @param primaryStage the Stage of SpaceTrader
     * @throws Exception if a problem occurs setting the Scene
     */
    public void start(Stage primaryStage) throws Exception {
        theStage = primaryStage;

        startGame.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(characterSheet()));
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
