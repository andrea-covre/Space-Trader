import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CharacterSheetScene extends SceneBuilder {
    @Override
    public Parent build() {
        return characterSheet();
    }

    @Override
    public void setStage(SceneBuilder b) {

    }
    private Pane characterSheet() {
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

        Text creditAmount = new Text(Integer.toString(credits));
        creditAmount.setFont(Font.font("Comic Sans MS", 50));
        creditAmount.setFill(Color.YELLOW);

        vboxCredits.getChildren().addAll(creditText, creditAmount);
        vboxCredits.setAlignment(Pos.TOP_CENTER);
        vboxCredits.setSpacing(100);


        /*
         * Difficulty
         */
        String difficultyText = "";
        if (difficulty == difficulty.EASY) {
            difficultyText = "Easy";
        } else if (difficulty == difficulty.MEDIUM) {
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

}
