package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import primary.Ship;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ShipScene extends SceneLoader {

    private BackgroundImage back = new BackgroundImage(
            new Image(new File("resources/ShipBackground.jpg").toURI().toURL().toString()),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    @FXML
    private StackPane mainPane;

    @FXML
    private Text statsText;

    @FXML
    private ImageView shipImage;

    @FXML
    private Text shipName;

    // Add a public no-args constructor
    public ShipScene() throws MalformedURLException {
        shipImage = new ImageView(String.valueOf(
                new File("resources/defaultShip.png").toURI().toURL()));
    }

    @FXML
    public void initialize() {
        Ship playerShip = player.getShip();
        statsText.setText("HP: " + playerShip.getHp() + "\n"
                + "Attack: " + playerShip.getAttack() + "\n"
                + "Cargo: " + playerShip.getCargo() + "\n"
                + "Name: " + playerShip.getName());
        shipName.setText("The " + playerShip.getName());
        mainPane.setBackground(new Background(back));
    }
    @Override
    public Parent build() {
        FXMLLoader p =  new FXMLLoader();
        p.setController(this);
        try {
            return p.load(new File("resources/ShipScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void handleBackClick(MouseEvent mouseEvent) {
        setStage(new MapScene());
    }
}
