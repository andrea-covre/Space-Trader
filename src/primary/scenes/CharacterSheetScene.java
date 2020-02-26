package primary.scenes;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CharacterSheetScene extends SceneLoader {
    private static Button startGameCSheet = new Button("Start");
    @Override
    public Parent build() {
        return characterSheet();
    }

    private Pane characterSheet() {

        /*
          base layout
         */
        BorderPane pane = new BorderPane();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        VBox vboxSkills = new VBox();
        VBox vboxCredits = new VBox();
        StackPane stackpane = new StackPane();


        /*
         * Background image
         */
        BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /*
         * Title
         */
        Text title = new Text("Welcome, " + player.getPlayerName());
        title.setFont(Font.font("Comic Sans MS", 70));
        title.setFill(Color.YELLOW);

        /*
         * Skills
         */
        Text skill = new Text("Your primary.Skill");
        skill.setFont(Font.font("Comic Sans MS", 70));
        skill.setFill(Color.RED);

        Text pilot = new Text("Pilot: \t\t" + player.getPilotSkill());
        pilot.setFont(Font.font("Comic Sans MS", 40));
        pilot.setFill(Color.YELLOW);

        Text fighter = new Text("Fighter: \t\t" + player.getFighterSkill());
        fighter.setFont(Font.font("Comic Sans MS", 40));
        fighter.setFill(Color.YELLOW);

        Text merchant = new Text("Fighter: \t\t" + player.getMerchantSkill());
        merchant.setFont(Font.font("Comic Sans MS", 40));
        merchant.setFill(Color.YELLOW);

        Text engineer = new Text("Engineer: \t" + player.getEngineerSkill());
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

        Text creditAmount = new Text(Integer.toString(player.getCredits()));
        creditAmount.setFont(Font.font("Comic Sans MS", 40));
        creditAmount.setFill(Color.YELLOW);

        vboxCredits.getChildren().addAll(creditText, creditAmount);
        vboxCredits.setAlignment(Pos.TOP_CENTER);
        vboxCredits.setSpacing(80);

        startGameCSheet.setOnAction(e -> {
            try {
                setStage(new MapScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        /*
         * Difficulty
         */
        String difficultyText;
        if (setDifficulty == Difficulty.EASY) {
            difficultyText = "Easy";
        } else if (setDifficulty == Difficulty.MEDIUM) {
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

}
