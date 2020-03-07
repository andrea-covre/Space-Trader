package primary.scenes;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import skills.EngineerSkill;
import skills.FighterSkill;
import skills.MerchantSkill;
import skills.PilotSkill;

import java.net.MalformedURLException;

public class SkillsLevelSelectionScene extends SceneLoader {
    /**
     * skills.Skill Level Selection Scene buttons
     */
    //Pilot
    private  Button pilotUp = new Button("⬆");
    private  Button pilotDown = new Button("⬇");

    //Fighter
    private  Button fighterUp = new Button("⬆");
    private  Button fighterDown = new Button("⬇");

    //Merchant
    private  Button merchantUp = new Button("⬆");
    private  Button merchantDown = new Button("⬇");

    //Engineer
    private  Button engineerUp = new Button("⬆");
    private  Button engineerDown = new Button("⬇");

    //Navigation
    private  Button startGame = new Button("Start");
    private  Button backToNameSelection = new Button("Back");

    @Override
    public Parent build() {
        return skillsLevelSelection();
    }

    private void setButtons() {
        // setting actions for all skill leveling buttons
        pilotUp.setOnAction(e -> {
            new PilotSkill(1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        pilotDown.setOnAction(e -> {
            new PilotSkill(-1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        fighterUp.setOnAction(e -> {
            new FighterSkill(1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        fighterDown.setOnAction(e -> {
            new FighterSkill(-1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        merchantUp.setOnAction(e -> {
            new MerchantSkill(1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        merchantDown.setOnAction(e -> {
            new MerchantSkill(-1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        engineerUp.setOnAction(e -> {
            new EngineerSkill(1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        engineerDown.setOnAction(e -> {
            new EngineerSkill(-1).upgrade(player);
            try {
                setStage(this);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        });

        //setting back button to return to name selections
        backToNameSelection.setOnAction(e -> {
            player.resetSkill();
            try {
                setStage(new NameSelectionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        // setting start game to start the game
        startGame.setOnAction(e -> {
            try {
                setStage(new CharacterSheetScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
    }

    private Pane skillsLevelSelection() {

        /*
        set buttons here in helper method
         */
        setButtons();
        /*
         * skills.Skill Nodes
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
        Text pilotLevelDisplay = new Text(player.getPilotSkill().toString());
        pilotLevelDisplay.setId("skillLevelFont");
        Text fighterLevelDisplay = new Text(player.getFighterSkill().toString());
        fighterLevelDisplay.setId("skillLevelFont");
        Text merchantLevelDisplay = new Text(player.getMerchantSkill().toString());
        merchantLevelDisplay.setId("skillLevelFont");
        Text engineerLevelDisplay = new Text(player.getEngineerSkill().toString());
        engineerLevelDisplay.setId("skillLevelFont");

        /*
         * Nodes to display skill points available
         */
        Text skillPointsDisplay = new Text("Skill points: "
                + player.getSkillPoints());
        skillPointsDisplay.setId("skillLevelFont");

        /*
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

        /*
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
}
