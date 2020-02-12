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
<<<<<<< HEAD
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.MediaPlayer;
=======
import javafx.scene.layout.*;
>>>>>>> 57c3c35be20aec7a5a2463ff69aad05886ab66cc
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents the initial configuration for a new game
 */
public class NewGame extends Application {
    /**
     * Nodes that are important for scene changing:
     */
    public static Stage theStage;
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
<<<<<<< HEAD
        EASY, MEDIUM, HARD;
    };
    protected static String playerName;
    protected static Difficulty difficulty;
    protected static int credits;
=======
        EASY, MEDIUM, HARD
    }
    private static String playerName;
    private static Difficulty difficulty;
    private static int credits;
>>>>>>> 57c3c35be20aec7a5a2463ff69aad05886ab66cc
    protected static int skillPoints;

    private static Button startGameCSheet = new Button("Start");

    /**
     * Skill levels
     */
<<<<<<< HEAD

    protected static Skill pilotSkill = new Skill(0);
    protected static Skill fighterSkill = new Skill(0);
    protected static Skill merchantSkill = new Skill(0);
    protected static Skill engineerSkill = new Skill(0);
=======
    private static Skill pilotSkill = new Skill(0);
    private static Skill fighterSkill = new Skill(0);
    private static Skill merchantSkill = new Skill(0);
    private static Skill engineerSkill = new Skill(0);
>>>>>>> 57c3c35be20aec7a5a2463ff69aad05886ab66cc

    /**
     * Graphics
     */
    private static final ImageView BACKGROUND = new ImageView(
            new Image(Main.backGround, 960, 1280, false, false));

    private static final ImageView MAP_BACKGROUND = new ImageView(
            new Image(Main.mapBackGround, 960, 1280, false, false));

    /**
     * Map
     */
    //Map info
    protected static Region currentLocation;
    protected static Region selectedLocation;

    //Travel
    private static double fuelCostPerUnit = 1; //cost per unit of distance
    private static int travelDiscountPerPilotLevel = 3; //in percentage
    private static int costToSelectedLocation;

    //Map Buttons
    private static Button travelToLocation = new Button("Travel to");
    private static Button closeInfoPanel = new Button("Close");

    //Map generation
    private static boolean regionsGenerated = false;
    private static List<Region> regions;
    private static int numberOfRegions = 10;    //number of regions generated for each new map [max reasonable # = 20 because of spacing]
    private static int minimunRegionSpacing = 300; //coordinate-wise: minimum distance required between planets, valid for both X and Y [used during world generation]

    //Graphics
    private static int borderSpacing = 240;     //space to prevent planets from being too close to the screen borders [for layout generation ONLY]
    private static int infoPaneSpacing = 85;    //spacing between icon and info pane
    private static double infoPaneSpacingLeftFactor = 2.4;  //increase spacing if pane will be shown on the left of the planet icon
    private static double infoPaneSpacingNameFactor = 2;    //add extra spacing to the left based on the length of the planet's name
    private static int xScaling = 2500; //scaling factors to resize regions coordinates in displayable screen coordinates
    private static int yScaling = 2000;

    /**
     * Region Scene
     */
    private static Button regionBackButton = new Button("Back to Map");

    /**
     * Game Volume
     */
    protected static double GAMEVOLUME = 0.02;

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
        title.setFont(Font.font("Comic Sans MS", 70));
        title.setFill(Color.YELLOW);

        /* 
         * Skills
         */
        Text skill = new Text("Your Skill");
        skill.setFont(Font.font("Comic Sans MS", 70));
        skill.setFill(Color.RED);

        Text pilot = new Text("Pilot: \t\t" + pilotSkill);
        pilot.setFont(Font.font("Comic Sans MS", 40));
        pilot.setFill(Color.YELLOW);

        Text fighter = new Text("Fighter: \t\t" + fighterSkill);
        fighter.setFont(Font.font("Comic Sans MS", 40));
        fighter.setFill(Color.YELLOW);

        Text merchant = new Text("Fighter: \t\t" + merchantSkill);
        merchant.setFont(Font.font("Comic Sans MS", 40));
        merchant.setFill(Color.YELLOW);

        Text engineer = new Text("Engineer: \t" + engineerSkill);
        engineer.setFont(Font.font("Comic Sans MS", 40));
        engineer.setFill(Color.YELLOW);

        vboxSkills.getChildren().addAll(skill, pilot, fighter, merchant, engineer);
        vboxSkills.setAlignment(Pos.TOP_CENTER);

        /* 
         * Credits
         */
        Text creditText = new Text("Your Credits");
        creditText.setFont(Font.font("Comic Sans MS", 70));
        creditText.setFill(Color.RED);

        Text creditAmount = new Text(new Integer(credits).toString());
        creditAmount.setFont(Font.font("Comic Sans MS", 40));
        creditAmount.setFill(Color.YELLOW);

        vboxCredits.getChildren().addAll(creditText, creditAmount);
        vboxCredits.setAlignment(Pos.TOP_CENTER);
        vboxCredits.setSpacing(80);


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
        difTitle.setFont(Font.font("Comic Sans MS", 40));
        difTitle.setFill(Color.YELLOW);

        hbox.getChildren().addAll(vboxSkills, vboxCredits);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(100);

        vbox.getChildren().addAll(title, hbox, difTitle, startGameCSheet);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50.0);
        startGameCSheet.setId("characterSheetStartFont");

        pane.setCenter(stackpane);
        stackpane.getChildren().addAll(BACKGROUND, vbox);

        pane.getStylesheets().add("css/Styles.css");
        return pane;
    }

    /**
     * This pane generates the Map Scene
     * @return the generated pane to display
     */
    static Pane map() {
        /**
         * Regions generation
         */
        if (!regionsGenerated) {
            regions = new ArrayList<Region>();
            boolean tooClose = true;
            Region newRegion = null;
            for (int i = 0; i < numberOfRegions; i++) {
                tooClose = true;
                //preventing the regions to be too close
                while(tooClose) {
                    newRegion = new Region();
                    tooClose = false;
                    if (regions.size() > 0) {
                        for (Region region : regions) {
                            if ((Math.abs(newRegion.xCoordinate - region.xCoordinate) < minimunRegionSpacing)
                            && (Math.abs(newRegion.yCoordinate - region.yCoordinate) < minimunRegionSpacing)) {
                                tooClose = true;
                            }
                        }
                    }
//                    if (tooClose) {
//                        System.out.println(" -> Invalid generation");
//                    } else {
//                        System.out.println(" -> Valid generation");
//                    }
                }
                regions.add(newRegion);
            }
            //Setting the first world generated has the spawn world
            regions.get(0).hasBeenVisited = true;
            currentLocation = regions.get(0);
            regionsGenerated = true;
        }

        /**
         * Base layout
         */
        BorderPane pane = new BorderPane();
        StackPane stackpane = new StackPane();
        AnchorPane mapLayout = new AnchorPane();

        stackpane.getChildren().add(MAP_BACKGROUND);
        stackpane.getChildren().add(pane);

        ArrayList<Button> locationButt = new ArrayList<>();

        //Location info containers
        Pane infoPane = new Pane();
        VBox infoList = new VBox();

        for (int x = 0; x < regions.size(); x++) {
            Region i = regions.get(x);
            StackPane imagePane = new StackPane();
            VBox regionBox = new VBox();

            Circle planet = new Circle(28);
            if (currentLocation.equals(i)) {
                planet.setStrokeWidth(5);
                planet.setStroke(Color.GREEN);
            }
            Text regionName = null;
            Text planetText = null;

            locationButt.add(new Button("   "));
            locationButt.get(x).setId("locationButt");

            if (i.hasBeenVisited) {
                //Location appearance
                regionName = new Text(i.name);
                planetText = new Text("✹");
                planetText.setFill(Color.GREEN);
                regionBox.getChildren().add(imagePane);

            } else {
                //Location appearance
                regionName = new Text("?????");
                planetText = new Text("?");
                planetText.setFill(Color.RED);
                regionBox.getChildren().add(imagePane);
                //TODO: fix this, need to have the image above each region name
            }


            //Location Button and info

            //Because lambda is nasty: xIndex = x
            AtomicInteger xIndex = new AtomicInteger();
            xIndex.set(x);

            locationButt.get(x).setOnAction(e -> {
                try {
                    //If a location gets clicked on:

                    //resetting the info
                    infoPane.getChildren().clear();
                    infoList.getChildren().clear();
                    infoPane.setStyle("-fx-border-width: 2px;");

                    Text locationInfo;

                    //Calculating distance between current location and every other planet
                    int distance = (int) Math.sqrt(Math.pow(currentLocation.xCoordinate - i.xCoordinate, 2)
                            + Math.pow(currentLocation.yCoordinate - i.yCoordinate, 2));

                    //Calculating fuel cost based on distance and pilot skill level
                    int fuelCost = (int) ((double) distance * fuelCostPerUnit
                            * (1.00 - 0.01 * travelDiscountPerPilotLevel * pilotSkill.getValue()));

                    costToSelectedLocation = fuelCost;
                    selectedLocation = i;

                    if(i.hasBeenVisited) {
                        String visitedText = "Yes ";
                        if (currentLocation.equals(i)) {
                            visitedText = "Current Location ";
                        }
                        locationInfo = new Text(" Location: " + i.name + " \n"
                                + " Tech Level: " + i.techLevel + " \n"
                                + " Coordinates: " + "(X:" + i.xCoordinate + " | Y:" + i.yCoordinate + ")" + " \n"
                                + " Description: " + i.description + " \n"
                                + " Visited: " + visitedText + " \n"
                                + " Distance: " + distance + " \n"
                                + " Fuel cost: " + fuelCost + " (-" + pilotSkill.getValue() * 5 + "%) ");
                        locationInfo.setId("locationInfo");
                    } else {
                        locationInfo = new Text(" Location: " + "?????" + " \n"
                                + " Tech Level: " + "?????" + " \n"
                                + " Coordinates: " + "(X:" + i.xCoordinate + " | Y:" + i.yCoordinate + ")" + " \n"
                                + " Description: " + "?????" + " \n"
                                + " Visited: " + "Not yet " + " \n"
                                + " Distance: " + distance + " \n"
                                + " Fuel cost: " + fuelCost + " (-" + pilotSkill.getValue() * 5 + "%) ");
                        locationInfo.setId("locationInfo");
                    }

                    //Containers to put inside panel info (info + buttons)
                    VBox infoVBox = new VBox();
                    HBox infoButtonHBox = new HBox();
                    infoButtonHBox.setAlignment(Pos.CENTER_RIGHT);
                    infoVBox.setAlignment(Pos.CENTER_RIGHT);

                    //TODO: need to fix the overlap of the travel button over the panel border
                    //Setting and add Travel and close buttons
                    infoButtonHBox.setHgrow(travelToLocation, Priority.ALWAYS);
                    infoButtonHBox.setHgrow(closeInfoPanel, Priority.ALWAYS);
                    travelToLocation.setMaxWidth(Double.MAX_VALUE);
                    closeInfoPanel.setMaxWidth(Double.MAX_VALUE);

                    //Checking if player has enough credits to traver to this location
                    if (credits >= fuelCost) {
                        travelToLocation.setStyle("-fx-background-color: rgba(0, 156, 0, 0.7)");
                    } else {
                        travelToLocation.setStyle("-fx-background-color: rgba(255, 0, 0, 0.48)");
                    }

                    travelToLocation.setId("travelToLocation");
                    closeInfoPanel.setId("closeInfoPanel");

                    infoButtonHBox.getChildren().addAll(travelToLocation, closeInfoPanel);

                    //setting new Info and putting together the containers
                    infoList.getChildren().add(locationInfo);
                    infoVBox.getChildren().addAll(infoList, infoButtonHBox);
                    infoPane.getChildren().add(infoVBox);

                    //Calculating optimal location for the infopane to appear in
                    int infoPaneX = i.xCoordinate;
                    int infoPaneY = i.yCoordinate;

                    infoPaneX = (int) (infoPaneX * theStage.getWidth()) / xScaling;
                    infoPaneY =  (int) (infoPaneY * theStage.getHeight()) / yScaling;
                    infoPaneX = infoPaneX + (int) ((theStage.getWidth() - borderSpacing) / 2);
                    infoPaneY = infoPaneY + (int) ((theStage.getHeight() - borderSpacing) / 2);

                    if (i.xCoordinate > 0) {
                        infoPaneX = infoPaneX - (int) (infoPaneSpacing * infoPaneSpacingLeftFactor
                                + (i.name.length() * infoPaneSpacingNameFactor));
                    } else {
                        infoPaneX = infoPaneX + infoPaneSpacing;
                    }

                    if (i.yCoordinate > 0) {
                        infoPaneY = infoPaneY - infoPaneSpacing;
                    } else {
                        infoPaneY = infoPaneY + infoPaneSpacing;
                    }

                    infoPane.setLayoutX(infoPaneX);
                    infoPane.setLayoutY(infoPaneY);
                    infoPane.setId("infoPane");

                } catch (Throwable f) {
                    f.printStackTrace();
                }
            });

            regionBox.getChildren().add(regionName);
            regionBox.setAlignment(Pos.CENTER);
            imagePane.getChildren().addAll(planet, planetText, locationButt.get(x));

            regionName.setFill(Color.YELLOW);
            //TODO: fix style here
            regionName.setId("regionName");
            planetText.setId("planetText");

            mapLayout.getChildren().addAll(regionBox);

            //normalizing coordinates to screen size
            int tempX = (int) (i.xCoordinate * theStage.getWidth()) / xScaling;
            int tempY =  (int) (i.yCoordinate * theStage.getHeight()) / yScaling;
            tempX = tempX + (int) ((theStage.getWidth() - borderSpacing) / 2);
            tempY = tempY + (int) ((theStage.getHeight() - borderSpacing) / 2);
            regionBox.setLayoutX(tempX);
            regionBox.setLayoutY(tempY);
        }

        //Map buttons management
        closeInfoPanel.setOnAction(e -> {
            try {
                infoPane.getChildren().clear();
                infoPane.setStyle("-fx-border-width: 0px;");
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        travelToLocation.setOnAction(e -> {
            try {
                if (costToSelectedLocation <= credits) {
                    currentLocation = selectedLocation;
                    currentLocation.hasBeenVisited = true;
                    credits = credits - costToSelectedLocation;
                    theStage.setScene(new Scene(regionScene()));
                }
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        /**
         * Background image
         */
        MAP_BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        MAP_BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
         * Title
         */
        Text title = new Text("Map");
        title.setFill(Color.YELLOW);
        pane.setTop(title);

        //Adding planet info panel
        mapLayout.getChildren().addAll(infoPane);

        //Creating stats bar
        //TODO: for some reason CSS does not work here so the text's color is hardcoded here
        HBox statsBar = new HBox();
        Text creditsInfo = new Text("Credits: " + credits);
        creditsInfo.setId("statsBar");
        creditsInfo.setFill(Color.YELLOW);

        Text pilotInfo = new Text("Pilot: " + pilotSkill.getValue());
        pilotInfo.setId("statsBar");
        pilotInfo.setFill(Color.YELLOW);

        Text fighterInfo = new Text("Fighter: " + fighterSkill.getValue());
        fighterInfo.setId("statsBar");
        fighterInfo.setFill(Color.YELLOW);

        Text merchantInfo = new Text("Merchant: " + merchantSkill.getValue());
        merchantInfo.setId("statsBar");
        merchantInfo.setFill(Color.YELLOW);

        Text engineerInfo = new Text("Engineer: " + engineerSkill.getValue());
        engineerInfo.setId("statsBar");
        engineerInfo.setFill(Color.YELLOW);


        statsBar.setSpacing(100);
        statsBar.setAlignment(Pos.CENTER);
        statsBar.getChildren().addAll(creditsInfo, pilotInfo, fighterInfo, merchantInfo, engineerInfo);

        pane.setCenter(mapLayout);
        pane.setBottom(statsBar);
        BorderPane.setAlignment(pane.getTop(), Pos.CENTER);
        BorderPane.setAlignment(pane.getCenter(), Pos.CENTER);
        title.setId("mapTitle");
        //TODO: set the map title to yellow with CSS, I can't manage to change it
        pane.getStylesheets().add("css/Styles.css");
        return stackpane;
    }

    /**
     * This pane generates the region scene
     * @return the generated pane to display
     */
    static Pane regionScene() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        Text tempText = new Text(currentLocation.description);
        vBox.getChildren().addAll(tempText, regionBackButton);
        stackPane.getChildren().add(vBox);

        regionBackButton.setOnAction(e -> {
            try {
                theStage.setScene(new Scene(map()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        return stackPane;
    }

    /**
     *
     * @param primaryStage the Stage of SpaceTrader
     * @throws Exception if a problem occurs setting the Scene
     */
    public void start(Stage primaryStage) throws Exception {
        theStage = primaryStage;
        Main.backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        Main.backgroundMusic.setVolume(GAMEVOLUME);
        Main.backgroundMusic.play();
        startGame.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(characterSheet()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        startGameCSheet.setOnAction(e -> {
            try {
                primaryStage.setScene(new Scene(map()));
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

//TODO: try to separate this big file in different classes
//TODO: need to delete the extra images and their loading (for already visited world and not)