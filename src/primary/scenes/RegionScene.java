package primary.scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class RegionScene extends SceneLoader {
    @FXML
    private BorderPane pane;
    @FXML
    private Text regionInfo;
    @FXML
    private Text regionTitle;
    @FXML
    private ImageView marketPlaceImage;
    @FXML
    private ImageView mapIcon;
    @FXML
    private Button regionBackButton = new Button("Back to Map");
    @FXML
    private Button enterMarket = new Button(" ");

    private BackgroundImage back;
    {
        try {
            back = new BackgroundImage(
                        new Image(new File("src/resources/images/map_background.jpg")
                                .toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        pane.setBackground(new Background(back));
        regionScene();
    }

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File("src/resources/RegionScene.fxml")
                    .toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void regionScene() {

        /*
        Title
         */
        regionTitle.setText(currentLocation.getName());

        regionInfo.setText(" Location: " + currentLocation.getName() + " \n"
                + " Tech Level: " + currentLocation.getTechLevel() + " \n"
                + " Coordinates: " + "(X:" + currentLocation.getxCoordinate()
                + " | Y:" + currentLocation.getyCoordinate() + ")" + " \n"
                + " Description: " + currentLocation.getDescription() + " \n");


        /*
        Tile Pane with options
         */
        //every tile/option is a stack pane with the icon and blank
        // button on top, everything inside a VBox
        //the top of the VBox has the icon, the bottom has the title
        /*
        Icon resources.images
         */
        /*
    Graphics
     */
        int iconSize = 100;
        marketPlaceImage.fitHeightProperty().setValue(iconSize);
        marketPlaceImage.fitWidthProperty().setValue(iconSize);

        mapIcon.fitHeightProperty().setValue(iconSize);
        mapIcon.fitWidthProperty().setValue(iconSize);


        regionBackButton.setOnAction(e -> {
            try {
                setStage(new MapScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        enterMarket.setOnAction(e -> {
            try {
                setStage(new MarketScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

    }

}
