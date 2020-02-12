import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {
    /**
     * Graphics resorces
     */
    protected static FileInputStream backGround;
    protected static FileInputStream mapBackGround;
    protected static FileInputStream unknownRegion;
    protected static FileInputStream visitedRegion;

    private NewGame newGame = new NewGame();
    public void start(Stage stage) throws Exception {
        newGame.start(stage);
    }

    public static void main(String[] args) throws FileNotFoundException {
        loadGraphics();
        launch(args);
    }

    /**
     * Loads all the graphics needed for the NewGame class
     * @throws FileNotFoundException if one of the images is not found
     */
    public static void loadGraphics() throws FileNotFoundException {
        backGround = new FileInputStream("resources/menu_background.jpg");
        mapBackGround = new FileInputStream("resources/map_background.jpg");
    }
}
