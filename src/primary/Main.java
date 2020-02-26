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
<<<<<<< HEAD
    private static FileInputStream shipBackGround;
    protected static FileInputStream unknownRegion;
    protected static FileInputStream visitedRegion;
=======
    private static FileInputStream marketPlaceIcon;
    private static FileInputStream mapIcon;
>>>>>>> d5a82110c408cee0abdf185637c05673829053f2
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
     * @throws FileNotFoundException if one of the images is not found
     */
    public static void loadResources() throws FileNotFoundException {
        backGround = new FileInputStream("resources/menu_background.jpg");

        backgroundMusic = new AudioClip(
                Paths.get("resources/default_music.mp3").toUri().toString());
        mapBackGround = new FileInputStream("resources/map_background.jpg");
<<<<<<< HEAD
        shipBackGround = new FileInputStream("resources/ShipBackground.jpg");
=======
        marketPlaceIcon = new FileInputStream("resources/MarketPlace_Icon.png");
        mapIcon = new FileInputStream("resources/Map_Icon.png");
>>>>>>> d5a82110c408cee0abdf185637c05673829053f2
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

    public static FileInputStream getShipBackground() {
        return shipBackGround;
    }

}
