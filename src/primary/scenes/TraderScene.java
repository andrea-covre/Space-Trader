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

import primary.Trader;

public class TraderScene extends SceneLoader {
    public StackPane stackPane;
    public BorderPane borderPane;
    public HBox hbox;
    public Text title;
    public Text item;

    private primary.Trader trader = new Trader();

    @FXML
    private Button robButton = new Button("Rob");
    @FXML
    private Button ignoreButton = new Button("Ignore");
    @FXML
    private Button buyButton = new Button("Buy");
    @FXML
    private Button negotiateButton = new Button("Negotiate");
    /*
    Graphics
     */
    private int iconSize = 100;

    private BackgroundImage back;
    {
        try {
            back = new BackgroundImage(
                        new Image(new File("src/resources/images/map_background.jpg").toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        borderPane.setBackground(new Background(back));
        TraderScene();
    }

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File("src/resources/TraderScene.fxml").toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }
    private void TraderScene() {

        item.setText(trader.getItem().getName());

        buyButton.setOnAction(e -> {
            
        });

    }

}
