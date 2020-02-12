package primary.scenes;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import primary.NewGame;
import primary.Region;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MapScene extends SceneBuilder {

    /**
     * Base layout
     */
    BorderPane pane;
    StackPane stackpane;
    AnchorPane mapLayout;
    ArrayList<Button> locationButt;

    /**
     * stats bar stuff
     */
    HBox statsBar;
    Text pilotInfo;
    Text fighterInfo;
    Text merchantInfo;
    Text engineerInfo;
    Text creditsInfo;

    //Location info containers
    Pane infoPane;
    VBox infoList;

    //Map Buttons
    private static Button travelToLocation = new Button("Travel to");
    private static Button closeInfoPanel = new Button("Close");

    //Graphics
    private static int borderSpacing = 240;     //space to prevent planets from being too close to the screen borders [for layout generation ONLY]
    private static int infoPaneSpacing = 85;    //spacing between icon and info pane
    private static double infoPaneSpacingLeftFactor = 2.4;  //increase spacing if pane will be shown on the left of the planet icon
    private static double infoPaneSpacingNameFactor = 2;    //add extra spacing to the left based on the length of the planet's name
    private static int xScaling = 2500; //scaling factors to resize regions coordinates in displayable screen coordinates
    private static int yScaling = 2000;

    public MapScene(){
        /**
         * Regions generation
         */
        if (!regionsGenerated) {
            regions = new ArrayList<primary.Region>();
            boolean tooClose = true;
            primary.Region newRegion = null;
            for (int i = 0; i < numberOfRegions; i++) {
                tooClose = true;
                //preventing the regions to be too close
                while(tooClose) {
                    newRegion = new primary.Region();
                    tooClose = false;
                    if (regions.size() > 0) {
                        for (primary.Region region : regions) {
                            if ((Math.abs(newRegion.getxCoordinate() - region.getxCoordinate()) < minimunRegionSpacing)
                                    && (Math.abs(newRegion.getyCoordinate()
                                    - region.getyCoordinate()) < minimunRegionSpacing)) {
                                tooClose = true;
                            }
                        }
                    }
                }
                regions.add(newRegion);
            }
            //Setting the first world generated has the spawn world
            regions.get(0).setBeenVisited(true);
            currentLocation = regions.get(0);
            regionsGenerated = true;
        }

        /**
         * Base layout
         */
        pane = new BorderPane();
        stackpane = new StackPane();
        mapLayout = new AnchorPane();
        stackpane.getChildren().add(MAP_BACKGROUND);
        stackpane.getChildren().add(pane);

        locationButt = new ArrayList<>();

        //Location info containers
        infoPane = new Pane();
        infoList = new VBox();
    }

    @Override
    public Parent build() {
        return map();
    }

    private void generateRegions(){
        for (int x = 0; x < regions.size(); x++) {
            Region i = regions.get(x);
            StackPane imagePane = new StackPane();
            VBox regionBox = new VBox();

            Circle planet = new Circle(28);
            if (currentLocation.equals(i)) {
                planet.setStrokeWidth(5);
                planet.setStroke(Color.GREEN);
            }
            Text regionName = null;
            Text planetText = null;

            locationButt.add(new Button("   "));
            locationButt.get(x).setId("locationButt");

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
                //TODO: fix this, need to have the image above each region name
            }


            //Location Button and info

            //Because lambda is nasty: xIndex = x
            AtomicInteger xIndex = new AtomicInteger();
            xIndex.set(x);

            locationButt.get(x).setOnAction(e -> {
                try {
                    //If a location gets clicked on:

                    //resetting the info
                    infoPane.getChildren().clear();
                    infoList.getChildren().clear();
                    infoPane.setStyle("-fx-border-width: 2px;");

                    Text locationInfo;

                    //Calculating distance between current location and every other planet
                    int distance = (int) Math.sqrt(Math.pow(currentLocation.getxCoordinate() - i.getxCoordinate(), 2)
                            + Math.pow(currentLocation.getyCoordinate() - i.getyCoordinate(), 2));

                    //Calculating fuel cost based on distance and pilot skill level
                    int fuelCost = (int) ((double) distance * fuelCostPerUnit
                            * (1.00 - 0.01 * travelDiscountPerPilotLevel * pilotSkill.getValue()));

                    costToSelectedLocation = fuelCost;
                    selectedLocation = i;

                    if(i.hasBeenVisited()) {
                        String visitedText = "Yes ";
                        if (currentLocation.equals(i)) {
                            visitedText = "Current Location ";
                        }
                        locationInfo = new Text(" Location: " + i.getName() + " \n"
                                + " Tech Level: " + i.getTechLevel() + " \n"
                                + " Coordinates: " + "(X:" + i.getxCoordinate() + " | Y:" + i.getyCoordinate() + ")" + " \n"
                                + " Description: " + i.getDescription() + " \n"
                                + " Visited: " + visitedText + " \n"
                                + " Distance: " + distance + " \n"
                                + " Fuel cost: " + fuelCost + " (-" + pilotSkill.getValue() * 5 + "%) ");
                        locationInfo.setId("locationInfo");
                    } else {
                        locationInfo = new Text(" Location: " + "?????" + " \n"
                                + " Tech Level: " + "?????" + " \n"
                                + " Coordinates: " + "(X:" + i.getxCoordinate() + " | Y:" + i.getyCoordinate() + ")" + " \n"
                                + " Description: " + "?????" + " \n"
                                + " Visited: " + "Not yet " + " \n"
                                + " Distance: " + distance + " \n"
                                + " Fuel cost: " + fuelCost + " (-" + pilotSkill.getValue() * 5 + "%) ");
                        locationInfo.setId("locationInfo");
                    }

                    //Containers to put inside panel info (info + buttons)
                    VBox infoVBox = new VBox();
                    HBox infoButtonHBox = new HBox();
                    infoButtonHBox.setAlignment(Pos.CENTER_RIGHT);
                    infoVBox.setAlignment(Pos.CENTER_RIGHT);

                    //TODO: need to fix the overlap of the travel button over the panel border
                    //Setting and add Travel and close buttons
                    infoButtonHBox.setHgrow(travelToLocation, Priority.ALWAYS);
                    infoButtonHBox.setHgrow(closeInfoPanel, Priority.ALWAYS);
                    travelToLocation.setMaxWidth(Double.MAX_VALUE);
                    closeInfoPanel.setMaxWidth(Double.MAX_VALUE);

                    //Checking if player has enough credits to traver to this location
                    if (credits >= fuelCost) {
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

                    infoPaneX = (int) (infoPaneX * NewGame.theStage.getWidth()) / xScaling;
                    infoPaneY =  (int) (infoPaneY * NewGame.theStage.getHeight()) / yScaling;
                    infoPaneX = infoPaneX + (int) ((NewGame.theStage.getWidth() - borderSpacing) / 2);
                    infoPaneY = infoPaneY + (int) ((NewGame.theStage.getHeight() - borderSpacing) / 2);

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

            regionBox.getChildren().add(regionName);
            regionBox.setAlignment(Pos.CENTER);
            imagePane.getChildren().addAll(planet, planetText, locationButt.get(x));

            regionName.setFill(Color.YELLOW);
            //TODO: fix style here
            regionName.setId("regionName");
            planetText.setId("planetText");

            mapLayout.getChildren().addAll(regionBox);

            //normalizing coordinates to screen size
            int tempX = (int) (i.getxCoordinate() * NewGame.theStage.getWidth()) / xScaling;
            int tempY =  (int) (i.getyCoordinate() * NewGame.theStage.getHeight()) / yScaling;
            tempX = tempX + (int) ((NewGame.theStage.getWidth() - borderSpacing) / 2);
            tempY = tempY + (int) ((NewGame.theStage.getHeight() - borderSpacing) / 2);
            regionBox.setLayoutX(tempX);
            regionBox.setLayoutY(tempY);
        }
    }
    private void generateStatsBar(){
        //Creating stats bar
        //TODO: for some reason CSS does not work here so the text's color is hardcoded here

        statsBar = new HBox();
        creditsInfo = new Text("Credits: " + credits);
        creditsInfo.setId("statsBar");
        creditsInfo.setFill(Color.YELLOW);

        pilotInfo = new Text("Pilot: " + pilotSkill.getValue());
        pilotInfo.setId("statsBar");
        pilotInfo.setFill(Color.YELLOW);

        fighterInfo = new Text("Fighter: " + fighterSkill.getValue());
        fighterInfo.setId("statsBar");
        fighterInfo.setFill(Color.YELLOW);

        merchantInfo = new Text("Merchant: " + merchantSkill.getValue());
        merchantInfo.setId("statsBar");
        merchantInfo.setFill(Color.YELLOW);

        engineerInfo = new Text("Engineer: " + engineerSkill.getValue());
        engineerInfo.setId("statsBar");
        engineerInfo.setFill(Color.YELLOW);
        statsBar.setSpacing(100);
        statsBar.setAlignment(Pos.CENTER);
        statsBar.getChildren().addAll(creditsInfo, pilotInfo, fighterInfo, merchantInfo, engineerInfo);
    }
    private Pane map() {

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
                if (costToSelectedLocation <= credits) {
                    currentLocation = selectedLocation;
                    currentLocation.setBeenVisited(true);
                    credits = credits - costToSelectedLocation;
                    setStage(new RegionScene());
                }
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        /**
         * Background image
         */
        MAP_BACKGROUND.fitWidthProperty().bind(pane.widthProperty());
        MAP_BACKGROUND.fitHeightProperty().bind(pane.heightProperty());

        /**
         * Title
         */
        Text title = new Text("Map");
        title.setFill(Color.YELLOW);
        pane.setTop(title);

        //Adding planet info panel
        mapLayout.getChildren().addAll(infoPane);

        generateStatsBar();

        pane.setCenter(mapLayout);
        pane.setBottom(statsBar);
        BorderPane.setAlignment(pane.getTop(), Pos.CENTER);
        BorderPane.setAlignment(pane.getCenter(), Pos.CENTER);
        title.setId("mapTitle");
        //TODO: set the map title to yellow with CSS, I can't manage to change it
        pane.getStylesheets().add("css/Styles.css");
        return stackpane;
    }
}
