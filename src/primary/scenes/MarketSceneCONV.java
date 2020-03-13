package primary.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import primary.CharacterUpgrade;
import primary.Item;
import primary.Market;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.net.MalformedURLException;


public class MarketSceneCONV extends SceneLoader {

    public Text creditsInfo;
    public Text pilotInfo;
    public Text engineerInfo;
    public Text merchantInfo;
    public Text fighterInfo;
    public Text shipName;
    public Text shipHealth;
    public Text shipAttack;
    public Text shipUpgrades;
    public Text shipCapacity;

    public Text regionTitle1;
    public Text regionTitle;
    public Text itemsForSaleTitle;
    public Text specialItemsTitle;
    public Text shipCargoTitle;
    public Text shipUpgradeTitle;
    public Button backButton;

    private BackgroundImage back;

    {
        try {
            back = new BackgroundImage(
                    new Image(new File("resources/images/map_background.jpg").toURI().toURL().toString()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Button> buyButtons;
    private ArrayList<Button> sellButtons;
    private ArrayList<Button> sellUpgradesButtons;

    private primary.Market localMarket = currentLocation.getRegionMarket();

    @FXML
    private StackPane stackPane = new StackPane();
    @FXML
    private BorderPane pane = new BorderPane();
    @FXML
    private VBox centerBox = new VBox();

    @FXML
    private TilePane shipSlots = new TilePane();
    @FXML
    private TilePane marketItems = new TilePane();
    @FXML
    private TilePane upgradeSlots = new TilePane();
    @FXML
    private TilePane specialItems = new TilePane();
    private CharacterUpgrade specialItem = localMarket.getSpecialItem();
    private Button specialItemBuyButton;

    @FXML
    public void initialize() {
        regionTitle.setText(currentLocation.getName());
        stackPane.setBackground(new Background(back));
        generateBuyButtons();
        generateSpecialButtons();
        generateSellButtons();
        generateUpgradeSlots();
        generateBuyActions();
        generateSellActions();
        //Back Button
        backButton.setOnAction(e -> {
            try {
                setStage(new RegionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
        backButton.setId("backButton");
        generateStatsBar();
    }

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return FXMLLoader.load(new File(
                    "src/resources/MarketScene.fxml"
            ).toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pane marketSceneCONV() throws MalformedURLException {

        /*
        Title
         */
        regionTitle.setText(currentLocation.getName());

        //Sections Titles
        Text itemsForSaleTitle = new Text("--- Items for Sale ---\n");
        itemsForSaleTitle.setId("itemsForSaleTitle");

        Text specialItemsTitle = new Text("\n--- Special Items ---\n");
        specialItemsTitle.setId("specialItemsTitle");


        Text shipCargoTitle = new Text("\n--- Ship's Cargo ---\n");
        shipCargoTitle.setId("shipCargoTitle");


        Text shipUpgradeTitle = new Text("\n--- Upgrades ---\n");
        shipUpgradeTitle.setId("shipUpgradeTitle");

        generateBuyButtons();

        generateSpecialButtons();

        generateSellButtons();

        generateUpgradeSlots();

        //Back Button layout
        backButton.setId("backButton");
        Text dummyText = new Text(" ");

        /*
         * Button Managment
         */

        generateBuyActions();

        generateSellActions();

        //Back Button
        backButton.setOnAction(e -> {
            try {
                setStage(new RegionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        pane.getStylesheets().add("css/Styles.css");
        return stackPane;
    }

    private void generateSpecialButtons() {
        VBox specialVBox = new VBox();

        Text specialItemTitle = new Text(specialItem.getName());
        specialItemTitle.setId("specialItemTitle");

        Text itemDescription = new Text(specialItem.getDescription()
                + " (Tech: " + specialItem.getTechLevel() + " | " + specialItem.getSkillType()
                + " +" + specialItem.getIncAmount() + ")");
        itemDescription.setId("specialItemDescription");

        int specialItemFinalPrice = (int)
                ((1.00 - 0.01 * Market.getDiscountMerchantLevel()
                        * player.getMerchantSkill().getValue())
                        * specialItem.getPrice());
        specialItem.setAdjustedPrice(specialItemFinalPrice);
        specialItem.setSellingPrice((int) (specialItemFinalPrice * CharacterUpgrade.DEPRECIATION));

        specialItemBuyButton = new Button("Buy for " + specialItemFinalPrice + " (-"
                + Market.getDiscountMerchantLevel() * player.getMerchantSkill().getValue() + "%)");
        specialItemBuyButton.setId("specialItemBuyButt");
        if (player.getCredits() >= specialItemFinalPrice && !specialItem.isEquipped()) {
            specialItemBuyButton.setStyle("-fx-background-color: rgba(0, 156, 0)");
        } else {
            specialItemBuyButton.setStyle("-fx-background-color: rgba(255, 0, 0)");
        }

        specialVBox.getChildren().addAll(specialItemTitle, itemDescription, specialItemBuyButton);
        specialVBox.setAlignment(Pos.CENTER);
        specialVBox.setId("specialVBox");

        specialItems.getChildren().add(specialVBox);
        specialItems.setAlignment(Pos.CENTER);
        specialItems.setHgap(10);
        specialItems.setVgap(10);
    }

    private void generateBuyActions() {
        //Buy Buttons
        for (int i = 0; i < localMarket.getItemsPerMarket(); i++) {
            int finalI = i;

            buyButtons.get(i).setOnAction(e -> {
                try {
                    int itemFinalPrice = (int)
                            ((double) localMarket.getItemsOffering().get(finalI).getAdjustedPrice()
                                    * (1.00 - 0.01 * Market.getDiscountMerchantLevel()
                                    * player.getMerchantSkill().getValue()));
                    if (player.getCredits() >= itemFinalPrice
                            && currentShip.getCargo() > currentShip.getItems().size()) {

                        currentShip.addItem(localMarket.getItemsOffering().get(finalI));
                        player.setCredits(player.getCredits() - itemFinalPrice);

                    }
                    setStage(new MarketScene());
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            });
        }



        //Special item buy button
        specialItemBuyButton.setOnAction(e -> {
            try {
                int itemFinalPrice = localMarket.getSpecialItem().getAdjustedPrice();
                if (player.getCredits() >= itemFinalPrice
                        && currentShip.getUpgradeSlots() > currentShip.getUpgrades().size()
                        && !localMarket.getSpecialItem().isEquipped()) {
                    currentShip.getUpgrades().add(localMarket.getSpecialItem());
                    localMarket.getSpecialItem().setEquipped(true);
                    localMarket.getSpecialItem().getInc().upgrade(player);
                    player.setCredits(player.getCredits() - itemFinalPrice);
                }
                setStage(new MarketScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });
    }

    private void generateSellActions() {
        //Sell Buttons
        for (int i = 0; i < currentShip.getItems().size(); i++) {
            int finalI = i;

            sellButtons.get(i).setOnAction(e -> {
                try {
                    player.setCredits(player.getCredits()
                            + currentShip.getItems().get(finalI).getFinalSellPrice());
                    currentShip.getItems().remove(currentShip.getItems().get(finalI));
                    setStage(new MarketScene());
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            });
        }
        //Special items sell button
        for (int i = 0; i < currentShip.getUpgrades().size(); i++) {
            int finalI = i;

            sellUpgradesButtons.get(i).setOnAction(e -> {
                try {
                    player.setCredits(player.getCredits()
                            + currentShip.getUpgrades().get(finalI).getSellingPrice());
                    currentShip.getUpgrades().get(finalI).setEquipped(false);
                    localMarket.getSpecialItem().getInc().removeUpgrade(player);
                    currentShip.getUpgrades().remove(currentShip.getUpgrades().get(finalI));
                    setStage(new MarketScene());
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            });
        }
    }

    private void generateUpgradeSlots() {
        sellUpgradesButtons = new ArrayList<>();

        for (int i = 0; i < currentShip.getUpgradeSlots(); i++) {
            VBox itemUpgradesVBox = new VBox();
            if (i < currentShip.getUpgrades().size()) {
                CharacterUpgrade currentUpgradeItem = currentShip.getUpgrades().get(i);
                Text upgradeItemTitle = new Text(currentUpgradeItem.getName());
                upgradeItemTitle.setId("itemTitle");
                Text currentUpgradeItemDescription = new Text("(Tech: "
                        + specialItem.getTechLevel() + " | "
                        + specialItem.getSkillType() + " +"
                        + specialItem.getIncAmount() + ")");
                currentUpgradeItemDescription.setId("currentUpgradeItemDescription");

                int itemFinalPrice = (int)
                        ((double) currentUpgradeItem.getAdjustedPrice()
                                * (1.00 + 0.01
                                * Market.getDiscountMerchantLevel()
                                * player.getMerchantSkill().getValue()));

                sellUpgradesButtons.add(new Button("Sell for "
                        + currentUpgradeItem.getSellingPrice()));
                sellUpgradesButtons.get(i).setId("itemBuyButt");
                sellUpgradesButtons.get(i).setStyle("-fx-background-color: rgb(255,180,34)");


                itemUpgradesVBox.getChildren().addAll(upgradeItemTitle,
                        currentUpgradeItemDescription,
                        sellUpgradesButtons.get(i));
                itemUpgradesVBox.setAlignment(Pos.CENTER);
                itemUpgradesVBox.setId("itemVBox");

            } else {

                Text upgradeItemTitle = new Text("Empty Upgrade Slot\n#" + (i + 1));
                upgradeItemTitle.setId("itemTitle");


                itemUpgradesVBox.getChildren().addAll(upgradeItemTitle);
                itemUpgradesVBox.setAlignment(Pos.CENTER);
                itemUpgradesVBox.setId("itemVBox");

            }
            upgradeSlots.getChildren().add(itemUpgradesVBox);
        }

        upgradeSlots.setAlignment(Pos.CENTER);
        upgradeSlots.setHgap(10);
        upgradeSlots.setVgap(10);

    }

    private void generateSellButtons() {
        sellButtons = new ArrayList<>();

        for (int i = 0; i < currentShip.getCargo(); i++) {
            VBox itemVBox = new VBox();

            if (i < currentShip.getItems().size()) {

                Item currentCargoItem = currentShip.getItems().get(i);
                Text cargoItemTitle = new Text(currentCargoItem.getName());
                cargoItemTitle.setId("itemTitle");
                int itemFinalPrice = (int)
                        ((double) currentCargoItem.getAdjustedPrice()
                                * (1.00 + 0.01 * Market.getDiscountMerchantLevel()
                                * player.getMerchantSkill().getValue()));

                sellButtons.add(new Button("Sell for " + currentCargoItem.getFinalSellPrice()));

                sellButtons.get(i).setId("itemBuyButt");

                sellButtons.get(i).setStyle("-fx-background-color: rgb(255,180,34)");


                itemVBox.getChildren().addAll(cargoItemTitle, sellButtons.get(i));
                itemVBox.setAlignment(Pos.CENTER);
                itemVBox.setId("itemVBox");

            } else {

                Text cargoItemTitle = new Text("Empty Slot\n#" + (i + 1));
                cargoItemTitle.setId("itemTitle");


                itemVBox.getChildren().addAll(cargoItemTitle);
                itemVBox.setAlignment(Pos.CENTER);
                itemVBox.setId("itemVBox");

            }
            shipSlots.getChildren().add(itemVBox);
        }

        shipSlots.setAlignment(Pos.CENTER);
        shipSlots.setHgap(10);
        shipSlots.setVgap(10);
    }

    private void generateBuyButtons() {
        buyButtons = new ArrayList<>();

        for (int i = 0; i < localMarket.getItemsPerMarket(); i++) {
            VBox itemVBox = new VBox();

            Text itemTitle = new Text(localMarket.getItemsOffering().get(i).getName());
            itemTitle.setId("itemTitle");

            Text itemDescription = new Text(localMarket.getItemsOffering().get(i).getDescription()
                    + " (Tech: "
                    + localMarket.getItemsOffering().get(i).getTechLevel()
                    + ")");
            itemDescription.setId("itemDescription");

            int itemFinalPrice = (int)
                    ((double) localMarket.getItemsOffering().get(i).getAdjustedPrice()
                            * (1.00 - 0.01
                            * Market.getDiscountMerchantLevel()
                            * player.getMerchantSkill().getValue()));

            localMarket.getItemsOffering().get(i).setFinalBuyPrice(itemFinalPrice);
            localMarket.getItemsOffering().get(i).setFinalSellPrice((int) (itemFinalPrice
                    * primary.Market.getMarketDepreciation()));


            buyButtons.add(new Button("Buy for " + itemFinalPrice + " (-"
                    + Market.getDiscountMerchantLevel()
                    * player.getMerchantSkill().getValue() + "%)"));

            buyButtons.get(i).setId("itemBuyButt");

            if (player.getCredits() >= itemFinalPrice) {
                buyButtons.get(i).setStyle("-fx-background-color: rgba(0, 156, 0)");
            } else {
                buyButtons.get(i).setStyle("-fx-background-color: rgba(255, 0, 0)");
            }

            itemVBox.getChildren().addAll(itemTitle, itemDescription, buyButtons.get(i));
            itemVBox.setAlignment(Pos.CENTER);
            itemVBox.setId("itemVBox");

            marketItems.getChildren().add(itemVBox);
        }
        marketItems.setAlignment(Pos.CENTER);
        marketItems.setHgap(10);
        marketItems.setVgap(10);
    }
}
