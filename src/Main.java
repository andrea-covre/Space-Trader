import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;


public class Main extends Application {
    /**
     * Graphics resorces
     */
    protected static FileInputStream backGround;
    protected static AudioClip backgroundMusic;
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
        backgroundMusic = new AudioClip(Paths.get("resources/default_music.mp3").toUri().toString());
    }
}
