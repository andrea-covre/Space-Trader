package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import primary.NewGame;
import primary.Region;
import primary.SceneProbability;
import primary.Ship;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class MapScene extends SceneLoader {

    private BackgroundImage back;
    {
        try {
            back = new BackgroundImage(

                    new Image(new File("src/resources/images/map_background.jpg")
                            .toURI().toURL().toString()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            System.out.println("malformued URL expetion at mapscene line 41");
        }
    }
    @FXML
    public void initialize() {
        map();
        stackpane.setBackground(new Background(back));
        generateStatsBar();
    }

    @Override
    public Parent build() {
        FXMLLoader p =  new FXMLLoader();
        p.setController(this);
        try {
            return FXMLLoader.load(new File("src/resources/MapScene.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Base layout
     */
    @FXML
    private BorderPane pane;

    @FXML
    private StackPane stackpane;

    @FXML
    private AnchorPane mapLayout;


    private ArrayList<Button> locationButt;

    //Location info containers
    private Pane infoPane;
    private VBox infoList;

    //Map Buttons
    private  Button travelToLocation = new Button("Travel to");
    private  Button closeInfoPanel = new Button("Close");

    //Graphics
    //space to prevent planets from being too close to the screen borders
    // [for layout generation ONLY]
    private static int borderSpacing = 240;

    //spacing between icon and info pane
    private static double infoPaneSpacingLeftFactor = 2.4;

    //increase spacing if pane will be shown on the left of the planet icon
    private static int infoPaneSpacing = 85;

    //add extra spacing to the left based on the length of the planet's name
    private static double infoPaneSpacingNameFactor = 2;

    //scaling factors to resize regions coordinates in displayable screen coordinates
    private static int yScaling = 2000;
    private static int xScaling = 2500;



    public MapScene() {

        /*
         * Regions generation
         */
        if (!regionsGenerated) {
            regions = new ArrayList<>();
            boolean tooClose;
            primary.Region newRegion = null;
            for (int i = 0; i < numberOfRegions; i++) {
                tooClose = true;
                //preventing the regions to be too close
                while (tooClose) {
                    newRegion = new primary.Region();
                    tooClose = false;
                    if (regions.size() > 0) {
                        for (primary.Region region : regions) {
                            if ((Math
                                    .abs(newRegion.getxCoordinate() - region.getxCoordinate())
                                    < minimunRegionSpacing)
                                    && (Math
                                    .abs(newRegion.getyCoordinate() - region.getyCoordinate())
                                    < minimunRegionSpacing)) {
                                tooClose = true;
                                break;
                            }
                        }
                    }
                }
                regions.add(newRegion);
            }
            //Setting the first world generated has the spawn world
            regions.get(0).setBeenVisited(true);
            currentLocation = regions.get(0);
            currentLocation.getRegionMarket().generateMarket(currentLocation);
            regionsGenerated = true;

            regions.get(2).setWinningRegion(true);


            currentShip = new Ship("AFO", 5, 3, 50, 10, 3000);
        }

        /*
         * Base layout
         */

        locationButt = new ArrayList<>();

        //Location info containers
        infoPane = new Pane();
        infoList = new VBox();
    }





    private void generateRegions() {
        for (int x = 0; x < regions.size(); x++) {

            //create mini-pane for this region
            Region i = regions.get(x);
            StackPane imagePane = new StackPane();
            VBox regionBox = new VBox();

            //detect if this region is current and assign appropriate graphic
            Circle planet = new Circle(28);
            if (currentLocation.equals(i)) {
                planet.setStrokeWidth(5);
                planet.setStroke(Color.GREEN);
            }

            locationButt.add(new Button("   "));
            locationButt.get(x).setId("locationButt");

            /*
            determine if region has been visited and give it the correct associated properties
             */
            Text regionName;
            Text planetText;
            if (i.hasBeenVisited()) {
                //Location appearance
                regionName = new Text(i.getName());
                planetText = new Text("✹");
                planetText.setFill(Color.GREEN);
                regionBox.getChildren().add(imagePane);

            } else {
                //Location appearance
                regionName = new Text("?????");
                planetText = new Text("?");
                planetText.setFill(Color.RED);
                regionBox.getChildren().add(imagePane);

            }


            //Location Button and info set for this region
            setLocationButton(x, i);

            // add this region to the map
            regionBox.getChildren().add(regionName);
            regionBox.setAlignment(Pos.CENTER);
            imagePane.getChildren().addAll(planet, planetText, locationButt.get(x));

            regionName.setFill(Color.YELLOW);

            regionName.setId("regionName");
            planetText.setId("planetText");

            mapLayout.getChildren().addAll(regionBox);

            //normalizing coordinates to screen size
            int tempX = (int) (i.getxCoordinate() * NewGame.getTheStage().getWidth()) / xScaling;
            int tempY =  (int) (i.getyCoordinate() * NewGame.getTheStage().getHeight()) / yScaling;
            tempX = tempX + (int) ((NewGame.getTheStage().getWidth() - borderSpacing) / 2);
            tempY = tempY + (int) ((NewGame.getTheStage().getHeight() - borderSpacing) / 2);
            regionBox.setLayoutX(tempX);
            regionBox.setLayoutY(tempY);
        }
    }

    /**
     * for any given button in the list, assign it this specific region
     * and apply these properties
     * @param x index of the button
     * @param i input region to be modified by this button
     */
    private void setLocationButton(int x, Region i) {
        locationButt.get(x).setOnAction(e -> {
            try {
                //If a location gets clicked on:

                //resetting the info
                infoPane.getChildren().clear();
                infoList.getChildren().clear();
                infoPane.setStyle("-fx-border-width: 2px;");

                Text locationInfo;

                //Calculating distance between current location and every other planet
                int distance = (int) Math.sqrt(
                        Math.pow(currentLocation.getxCoordinate() - i.getxCoordinate(), 2)
                        + Math.pow(currentLocation.getyCoordinate() - i.getyCoordinate(), 2));

                //Calculating fuel cost based on distance and pilot skill level
                int fuelCost = (int) ((double) distance * fuelCostPerUnit
                        * (1.00 - 0.01 * travelDiscountPerPilotLevel
                        * player.getPilotSkill().getValue()));

                costToSelectedLocation = fuelCost;
                selectedLocation = i;

                if (i.hasBeenVisited()) {
                    String visitedText = "Yes ";
                    if (currentLocation.equals(i)) {
                        visitedText = "Current Location ";
                    }
                    locationInfo = new Text(" Location: " + i.getName() + " \n"
                            + " Tech Level: " + i.getTechLevel() + " \n"
                            + " Coordinates: " + "(X:" + i.getxCoordinate()
                            + " | Y:" + i.getyCoordinate() + ")" + " \n"
                            + " Description: " + i.getDescription() + " \n"
                            + " Visited: " + visitedText + " \n"
                            + " Distance: " + distance + " \n"
                            + " Fuel cost: " + fuelCost + " (-"
                            + player.getPilotSkill().getValue() * 5 + "%) ");
                    locationInfo.setId("locationInfo");
                } else {
                    locationInfo = new Text(" Location: " + "?????" + " \n"
                            + " Tech Level: " + "?????" + " \n"
                            + " Coordinates: " + "(X:" + i.getxCoordinate()
                            + " | Y:" + i.getyCoordinate() + ")" + " \n"
                            + " Description: " + "?????" + " \n"
                            + " Visited: " + "Not yet " + " \n"
                            + " Distance: " + distance + " \n"
                            + " Fuel cost: " + fuelCost + " (-"
                            + player.getPilotSkill().getValue() * 5 + "%) ");
                    locationInfo.setId("locationInfo");
                }

                //Containers to put inside panel info (info + buttons)
                VBox infoVBox = new VBox();
                HBox infoButtonHBox = new HBox();
                infoButtonHBox.setAlignment(Pos.CENTER_RIGHT);
                infoVBox.setAlignment(Pos.CENTER_RIGHT);



                //Setting and add Travel and close buttons
                HBox.setHgrow(travelToLocation, Priority.ALWAYS);
                HBox.setHgrow(closeInfoPanel, Priority.ALWAYS);
                travelToLocation.setMaxWidth(Double.MAX_VALUE);
                closeInfoPanel.setMaxWidth(Double.MAX_VALUE);

                //Checking if player has enough credits to traver to this location
                if (currentShip.getFuel() >= fuelCost) {
                    travelToLocation.setStyle("-fx-background-color: rgba(0, 156, 0, 0.7)");
                } else {
                    travelToLocation.setStyle("-fx-background-color: rgba(255, 0, 0, 0.48)");
                }

                travelToLocation.setId("travelToLocation");
                closeInfoPanel.setId("closeInfoPanel");

                infoButtonHBox.getChildren().addAll(travelToLocation, closeInfoPanel);

                //setting new Info and putting together the containers
                infoList.getChildren().add(locationInfo);
                infoVBox.getChildren().addAll(infoList, infoButtonHBox);
                infoPane.getChildren().add(infoVBox);

                //Calculating optimal location for the infopane to appear in
                int infoPaneX = i.getxCoordinate();
                int infoPaneY = i.getyCoordinate();

                infoPaneX = (int) (infoPaneX * NewGame.getTheStage().getWidth()) / xScaling;
                infoPaneY =  (int) (infoPaneY * NewGame.getTheStage().getHeight()) / yScaling;
                infoPaneX = infoPaneX + (int) ((
                        NewGame.getTheStage().getWidth() - borderSpacing) / 2);
                infoPaneY = infoPaneY + (int) ((
                        NewGame.getTheStage().getHeight() - borderSpacing) / 2);

                if (i.getxCoordinate() > 0) {
                    infoPaneX = infoPaneX - (int) (infoPaneSpacing * infoPaneSpacingLeftFactor
                            + (i.getName().length() * infoPaneSpacingNameFactor));
                } else {
                    infoPaneX = infoPaneX + infoPaneSpacing;
                }

                if (i.getyCoordinate() > 0) {
                    infoPaneY = infoPaneY - infoPaneSpacing;
                } else {
                    infoPaneY = infoPaneY + infoPaneSpacing;
                }

                infoPane.setLayoutX(infoPaneX);
                infoPane.setLayoutY(infoPaneY);
                infoPane.setId("infoPane");

            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

    }
    private void map() {

        generateRegions();
        //Map buttons management
        closeInfoPanel.setOnAction(e -> {
            try {
                infoPane.getChildren().clear();
                infoPane.setStyle("-fx-border-width: 0px;");
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        travelToLocation.setOnAction(e -> {
            try {
                if (costToSelectedLocation <= currentShip.getFuel()) {
                    if (currentLocation != selectedLocation) {
                        currentShip.setFuel(currentShip.getFuel() - costToSelectedLocation);
                        SceneProbability prob = new SceneProbability();
                        prob.probability();

                    } else {
                        // probability roll only happens if we moving to new location
                        setStage(new RegionScene());
                    }

                }
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        /*
         * Background image
         */
        MAP_BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        MAP_BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /*
         * Title
         */
        Text title = new Text("Map");
        title.setFill(Color.YELLOW);
        pane.setTop(title);

        //Adding planet info panel
        mapLayout.getChildren().addAll(infoPane);

        pane.setCenter(mapLayout);
        pane.setBottom(generateStatsBar());

        BorderPane.setAlignment(pane.getTop(), Pos.CENTER);
        BorderPane.setAlignment(pane.getCenter(), Pos.CENTER);
        BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);

        title.setId("mapTitle");

        pane.getStylesheets().add("css/Styles.css");
    }

}
