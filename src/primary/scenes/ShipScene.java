package primary.scenes;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.net.MalformedURLException;

public class ShipScene extends SceneLoader {


    private BackgroundImage back = new BackgroundImage(new Image(new File("resources/ShipBackground.jpg").toURI().toURL().toString()),
            BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
    @FXML
    private StackPane MainPane;

    @FXML
    private Text statsText;

    @FXML
    private StackPane ShipPane;
    @FXML
    private ImageView ShipImage;

    @FXML
    private Text ShipName;

    // Add a public no-args constructor
    public ShipScene() throws MalformedURLException {
        ShipImage = new ImageView(String.valueOf(new File("resources/defaultShip.png").toURI().toURL()));


    }

    @FXML
    public void initialize(){
        statsText.setText("HP: " + playerShip.getHp() +"\n"
                + "Attack: " + playerShip.getAttack() + "\n"
                + "Cargo: " + playerShip.getCargo() + "\n"
                + "Name: " + playerShip.getName());
        ShipName.setText("The " + playerShip.name);
        MainPane.setBackground(new Background(back));
    }
    @Override
    public Parent build() {
        return null;
    }

    @FXML
    public void handleBackClick(MouseEvent mouseEvent) {
        ShipName.setText(ShipName.getText()+1);
    }
}
