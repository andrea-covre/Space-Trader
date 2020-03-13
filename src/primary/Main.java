package primary;

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
    private static FileInputStream backGround;
    private static AudioClip backgroundMusic;
    private static FileInputStream mapBackGround;
    private static FileInputStream marketPlaceIcon;
    private static FileInputStream mapIcon;
    private NewGame newGame = new NewGame();

    public void start(Stage stage) throws Exception {
        newGame.start(stage);
    }

    public static void main(String[] args) throws FileNotFoundException {

        loadResources();
        launch(args);
    }

    /**
     * Loads all the graphics needed for the primary.NewGame class
     * @throws FileNotFoundException if one of the resources.images is not found
     */
    public static void loadResources() throws FileNotFoundException {
        backGround =  new FileInputStream("src/resources/images/menu_background.jpg");

        backgroundMusic = new AudioClip(
                Paths.get("src/resources/default_music.mp3").toUri().toString());
        mapBackGround = new FileInputStream("src/resources/images/map_background.jpg");
        marketPlaceIcon = new FileInputStream("src/resources/images/MarketPlace_Icon.png");
        mapIcon = new FileInputStream("src/resources/images/Map_Icon.png");
    }

    public static AudioClip getBackgroundMusic() {
        return backgroundMusic;
    }

    public static FileInputStream getBackGround() {
        return backGround;
    }

    public static FileInputStream getMapBackGround() {
        return mapBackGround;
    }

    public static FileInputStream getMarketPlaceIcon() {
        return marketPlaceIcon;
    }

    public static FileInputStream getMapIcon() {
        return mapIcon;
    }

    public static void setBackGround(FileInputStream backGround) {
        Main.backGround = backGround;
    }

}
