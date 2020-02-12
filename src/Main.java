import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;


public class Main extends Application {
    /**
     * Graphics resorces
     */
    protected static FileInputStream backGround;
<<<<<<< HEAD
    protected static AudioClip backgroundMusic;
=======
    protected static FileInputStream mapBackGround;
    protected static FileInputStream unknownRegion;
    protected static FileInputStream visitedRegion;

>>>>>>> 57c3c35be20aec7a5a2463ff69aad05886ab66cc
    private NewGame newGame = new NewGame();
    public void start(Stage stage) throws Exception {
        newGame.start(stage);
    }

    public static void main(String[] args) throws FileNotFoundException {
        loadResources();
        launch(args);
    }

    /**
     * Loads all the graphics needed for the NewGame class
     * @throws FileNotFoundException if one of the images is not found
     */
    public static void loadResources() throws FileNotFoundException {
        backGround = new FileInputStream("resources/menu_background.jpg");
<<<<<<< HEAD
        backgroundMusic = new AudioClip(Paths.get("resources/default_music.mp3").toUri().toString());
=======
        mapBackGround = new FileInputStream("resources/map_background.jpg");
>>>>>>> 57c3c35be20aec7a5a2463ff69aad05886ab66cc
    }
}
