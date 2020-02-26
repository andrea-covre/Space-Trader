package primary.scenes;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import primary.Main;

public class RegionScene extends SceneBuilder {

    /*
    Graphics
     */
    private int iconSize = 100;

    private Button regionBackButton = new Button("  ");
    private Button enterMarket = new Button("  ");

    @Override
    public Parent build() {
        return regionScene();
    }
    private Pane regionScene() {
        StackPane stackPane = new StackPane();
        BorderPane pane = new BorderPane();

        /*
        Title
         */
        VBox titleBox = new VBox();

        Text regionTitle = new Text(currentLocation.getName());
        regionTitle.setId("regionTitle");
        regionTitle.setFill(Color.YELLOW);

        Text regionInfo = new Text(" Location: " + currentLocation.getName() + " \n"
                + " Tech Level: " + currentLocation.getTechLevel() + " \n"
                + " Coordinates: " + "(X:" + currentLocation.getxCoordinate()
                + " | Y:" + currentLocation.getyCoordinate() + ")" + " \n"
                + " Description: " + currentLocation.getDescription() + " \n");
        regionInfo.setId("regionInfo");
        regionInfo.setFill(Color.YELLOW);
        regionInfo.setTextAlignment(TextAlignment.CENTER);

        titleBox.getChildren().addAll(regionTitle, regionInfo);
        titleBox.setAlignment(Pos.CENTER);
        pane.setTop(titleBox);
        BorderPane.setAlignment(pane.getTop(), Pos.CENTER);

        /*
        Tile Pane with options
         */
        //every tile/option is a stack pane with the icon and blank button on top, everything inside a VBox
        //the top of the VBox has the icon, the bottom has the title
        TilePane optionsPane = new TilePane();

        /*
        Icon images
         */
        ImageView MARKETPLACE_ICON = new ImageView(
                new Image(Main.getMarketPlaceIcon(), iconSize, iconSize, false, false));
        ImageView MAP_ICON = new ImageView(
                new Image(Main.getMapIcon(), iconSize, iconSize, false, true));

        //Map Icon
        StackPane mapIconStack = new StackPane();
        VBox mapIconVBox = new VBox();

        Text mapIconText = new Text("Map");
        mapIconText.setId("iconText");
        mapIconText.setFill(Color.YELLOW);
        regionBackButton.setId("mapIconButton");

        mapIconVBox.setAlignment(Pos.CENTER);
        mapIconStack.getChildren().addAll(MAP_ICON, regionBackButton);
        mapIconVBox.getChildren().addAll(mapIconStack, mapIconText);

        //Market Icon
        StackPane marketIconStack = new StackPane();
        VBox marketIconVBox = new VBox();

        Text marketIconText = new Text("Market Place");
        marketIconText.setId("iconText");
        marketIconText.setFill(Color.YELLOW);
        enterMarket.setId("marketIconButton");

        marketIconVBox.setAlignment(Pos.CENTER);
        marketIconStack.getChildren().addAll(MARKETPLACE_ICON, enterMarket);
        marketIconVBox.getChildren().addAll(marketIconStack, marketIconText);


        optionsPane.getChildren().addAll(marketIconVBox, mapIconVBox);

        optionsPane.setAlignment(Pos.CENTER);

        pane.setCenter(optionsPane);
        BorderPane.setAlignment(pane.getCenter(), Pos.CENTER);
        //vBox.getChildren().addAll(tempText, regionBackButton);

        stackPane.getChildren().addAll(MAP_BACKGROUND, pane);




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

        pane.getStylesheets().add("css/Styles.css");
        return stackPane;
    }

}
