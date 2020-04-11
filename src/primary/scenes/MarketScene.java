package primary.scenes;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import primary.CharacterUpgrade;
import primary.Item;
import primary.Market;

import java.util.ArrayList;


public class MarketScene extends SceneLoader {

    private ArrayList<Button> buyButtons;
    private ArrayList<Button> sellButtons;
    private ArrayList<Button> sellUpgradesButtons;
    private Button backButton = new Button("Back");
    private primary.Market localMarket = currentLocation.getRegionMarket();
    private TilePane shipSlots = new TilePane();
    private TilePane marketItems = new TilePane();
    private TilePane upgradeSlots = new TilePane();
    private TilePane specialItems = new TilePane();
    private CharacterUpgrade specialItem = localMarket.getSpecialItem();
    private Button specialItemBuyButton;
    private Button fuelBuyButton;
    private Button restoreHealthButton;
    private Button winningButton;


    @Override
    public Parent build() {
        return marketScene();
    }
    private Pane marketScene() {
        StackPane stackPane = new StackPane();
        BorderPane pane = new BorderPane();
        VBox centerBox = new VBox();

        /*
        Title
         */
        VBox titleBox = new VBox();

        Text regionTitle = new Text(currentLocation.getName());
        regionTitle.setId("regionTitle");

        Text marketTitle = new Text("~ Market ~");
        marketTitle.setId("marketTitle");

        titleBox.getChildren().addAll(regionTitle, marketTitle);
        titleBox.setAlignment(Pos.CENTER);
        pane.setTop(titleBox);
        pane.setBottom(generateStatsBar());
        BorderPane.setAlignment(pane.getBottom(), Pos.CENTER);
        BorderPane.setAlignment(pane.getTop(), Pos.CENTER);

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

        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(itemsForSaleTitle, marketItems,
                specialItemsTitle, specialItems, shipCargoTitle,
                shipSlots, shipUpgradeTitle, upgradeSlots, dummyText,
                backButton);

        pane.setCenter(centerBox);
        BorderPane.setAlignment(pane.getCenter(), Pos.CENTER);

        stackPane.getChildren().addAll(MAP_BACKGROUND, pane);

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
        if (currentLocation.isWinningRegion()) {
            specialItems.getChildren().addAll(generateSpecialItemButton(), generateRefuelButton(),
                    generateRestoreHPButton(), generateWinningButton());
        } else {
            specialItems.getChildren().addAll(generateSpecialItemButton(), generateRefuelButton(),
                    generateRestoreHPButton());
        }
        specialItems.setAlignment(Pos.CENTER);
        specialItems.setHgap(10);
        specialItems.setVgap(10);
    }

    private VBox generateSpecialItemButton() {
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

        return specialVBox;
    }

    private VBox generateRestoreHPButton() {
        VBox specialVBox = new VBox();

        Text specialItemTitle = new Text("Eye drops");
        specialItemTitle.setId("specialItemTitle");

        Text itemDescription = new Text("It will restore 1 HP");
        itemDescription.setId("specialItemDescription");

        int healthPointCost = (int) (Market.getCostPerHp()
                * (1 - 0.05 * player.getEngineerSkill().getValue()));

        restoreHealthButton = new Button("Buy for " + healthPointCost
                + " (-" + (player.getEngineerSkill().getValue() * 5)
                + "%)");
        restoreHealthButton.setId("specialItemBuyButt");
        if (player.getCredits() >= healthPointCost && currentShip.getHp()
                < currentShip.getMaxHp()) {
            restoreHealthButton.setStyle("-fx-background-color: rgba(0, 156, 0)");
        } else {
            restoreHealthButton.setStyle("-fx-background-color: rgba(255, 0, 0)");
        }

        specialVBox.getChildren().addAll(specialItemTitle, itemDescription, restoreHealthButton);
        specialVBox.setAlignment(Pos.CENTER);
        specialVBox.setId("specialVBox");

        return specialVBox;
    }

    private VBox generateRefuelButton() {
        VBox specialVBox = new VBox();

        Text specialItemTitle = new Text("Jug of fuel");
        specialItemTitle.setId("specialItemTitle");

        Text itemDescription = new Text("It will refuel "
                + Market.getFuelPerJug() + " units to your ship");
        itemDescription.setId("specialItemDescription");

        int fuelCost = (int) (Market.getFuelCostPerUnit() * Market.getFuelPerJug());

        fuelBuyButton = new Button("Buy for " + fuelCost);
        fuelBuyButton.setId("specialItemBuyButt");
        if (player.getCredits() >= fuelCost && currentShip.getFuel()
                < currentShip.getFuelCapacity()) {
            fuelBuyButton.setStyle("-fx-background-color: rgba(0, 156, 0)");
        } else {
            fuelBuyButton.setStyle("-fx-background-color: rgba(255, 0, 0)");
        }

        specialVBox.getChildren().addAll(specialItemTitle, itemDescription, fuelBuyButton);
        specialVBox.setAlignment(Pos.CENTER);
        specialVBox.setId("specialVBox");

        return specialVBox;
    }

    private VBox generateWinningButton() {
        VBox specialVBox = new VBox();

        Text specialItemTitle = new Text("Holy Grail");
        specialItemTitle.setId("specialItemTitle");

        Text itemDescription = new Text("It will end all of your sufferings");
        itemDescription.setId("specialItemDescription");

        int winningItemCost = Market.getCostWinningItem();

        winningButton = new Button("Buy for " + winningItemCost);
        winningButton.setId("specialItemBuyButt");
        if (player.getCredits() >= winningItemCost) {
            winningButton.setStyle("-fx-background-color: rgba(0, 156, 0)");
        } else {
            winningButton.setStyle("-fx-background-color: rgba(255, 0, 0)");
        }

        specialVBox.getChildren().addAll(specialItemTitle, itemDescription, winningButton);
        specialVBox.setAlignment(Pos.CENTER);
        specialVBox.setId("specialWinningVBox");

        return specialVBox;
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

        fuelBuyButton.setOnAction(e -> {
            try {
                int fuelCost = (int) (Market.getFuelCostPerUnit() * Market.getFuelPerJug());
                if (player.getCredits() >= fuelCost && currentShip.getFuel()
                        < currentShip.getFuelCapacity()) {
                    player.setCredits(player.getCredits() - fuelCost);
                    currentShip.setFuel(currentShip.getFuel() + Market.getFuelPerJug());
                    if (currentShip.getFuel() > currentShip.getFuelCapacity()) {
                        currentShip.setFuel(currentShip.getFuelCapacity());
                    }
                }
                setStage(new MarketScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        restoreHealthButton.setOnAction(e -> {
            try {
                int healthPointCost = (int) (Market.getCostPerHp()
                        * (1 - 0.05 * player.getEngineerSkill().getValue()));
                if (player.getCredits() >= healthPointCost
                        && currentShip.getHp() < currentShip.getMaxHp()) {
                    player.setCredits(player.getCredits() - healthPointCost);
                    currentShip.setHp(currentShip.getHp() + 1);
                }
                setStage(new MarketScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        if (currentLocation.isWinningRegion()) {
            winningButton.setOnAction(e -> {
                try {
                    int winningCost = Market.getCostWinningItem();
                    if (player.getCredits() >= winningCost) {
                        player.setCredits(player.getCredits() - winningCost);
                        regionsGenerated = false;
                        setStage(new WinScene());
                    }
                } catch (Throwable f) {
                    f.printStackTrace();
                }
            });
        }
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
