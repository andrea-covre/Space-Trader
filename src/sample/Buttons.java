package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Buttons {
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
}
