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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import primary.Main;
import primary.Market;

import java.util.ArrayList;

public class MarketScene extends SceneBuilder {

    private int shipCapacity = 5;
    private ArrayList<Button> buyButtons;
    private ArrayList<Button> sellButtons;

    @Override
    public Parent build() {
        return regionScene();
    }
    private Pane regionScene() {
        StackPane stackPane = new StackPane();
        BorderPane pane = new BorderPane();
        VBox centerBox = new VBox();

        /*
        Title
         */
        VBox titleBox = new VBox();

        Text regionTitle = new Text(currentLocation.getName() + "\n ~ Market ~");
        regionTitle.setId("regionTitle");
        regionTitle.setFill(Color.YELLOW);

        titleBox.getChildren().add(regionTitle);
        titleBox.setAlignment(Pos.CENTER);
        pane.setTop(titleBox);
        BorderPane.setAlignment(pane.getTop(), Pos.CENTER);

        //Sections Titles
        Text itemsForSaleTitle = new Text("--- Items for Sale ---\n");
        itemsForSaleTitle.setId("itemsForSaleTitle");
        itemsForSaleTitle.setFill(Color.YELLOW);

        Text specialItemsTitle = new Text("\n--- Special Items ---\n");
        specialItemsTitle.setId("specialItemsTitle");
        specialItemsTitle.setFill(Color.YELLOW);

        Text shipCargoTitle = new Text("\n--- Ship's Cargo ---\n");
        shipCargoTitle.setId("shipCargoTitle");
        shipCargoTitle.setFill(Color.YELLOW);



        //Tile Panes with market items

        TilePane marketItems = new TilePane();
        primary.Market localMarket = currentLocation.getRegionMarket();

        buyButtons = new ArrayList<>();

        for (int i = 0; i < localMarket.getItemsPerMarket(); i++) {
            VBox itemVBox = new VBox();

            Text itemTitle = new Text(localMarket.getItemsOffering().get(i).getName());
            itemTitle.setId("itemTitle");
            itemTitle.setFill(Color.YELLOW);

            Text itemDescription = new Text(localMarket.getItemsOffering().get(i).getDescription());
            itemDescription.setId("itemDescription");
            itemDescription.setFill(Color.YELLOW);

            int itemFinalPrice = (int)
                    ( (double) localMarket.getItemsOffering().get(i).getAdjustedPrice()
                    * (1.00 - 0.01 * Market.getDiscountMerchantLevel() * merchantSkill.getValue()));

            buyButtons.add(new Button("Buy for " + itemFinalPrice + " (-"
                    + Market.getDiscountMerchantLevel() * merchantSkill.getValue() +"%)"));

            buyButtons.get(i).setId("itemBuyButt");

            if (credits >= itemFinalPrice) {
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


        //Special Item(s)

        TilePane specialItems = new TilePane();

        VBox specialVBox = new VBox();

        Text specialItemTitle = new Text(localMarket.getSpecialItem().getName());
        specialItemTitle.setId("specialItemTitle");
        specialItemTitle.setFill(Color.YELLOW);

        String specialItemSkill = "";
        switch (localMarket.getSpecialItem().getSkillID()) {
            case 0:
                specialItemSkill = "Pilot";
                break;
            case 1:
                specialItemSkill = "Fighter";
                break;
            case 2:
                specialItemSkill = "Merchant";
                break;
            case 3:
                specialItemSkill = "Engineer";
                break;
        }
        Text itemDescription = new Text(localMarket.getSpecialItem().getDescription()
                + " - Bonus: " + localMarket.getSpecialItem().getIncAmount() + " - Type: " + specialItemSkill);
        itemDescription.setId("specialItemDescription");
        itemDescription.setFill(Color.YELLOW);

        int specialItemFinalPrice = (int)
                ( (double) localMarket.getSpecialItem().getAdjustedPrice()
                        * (1.00 - 0.01 * Market.getDiscountMerchantLevel() * merchantSkill.getValue()));

        Button specialItemBuyButton = new Button("Buy for " + specialItemFinalPrice + " (-"
                + Market.getDiscountMerchantLevel() * merchantSkill.getValue() +"%)");
        specialItemBuyButton.setId("specialItemBuyButt");
        if (credits >= specialItemFinalPrice) {
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


        //TilePane with ship slots
        //Tile Panes with market items

//        TilePane shipSlots = new TilePane();
//
//        sellButtons = new ArrayList<>();
//
//        for (int i = 0; i < localMarket.getItemsPerMarket(); i++) {
//            VBox itemVBox = new VBox();
//
//            Text itemTitle = new Text(localMarket.getItemsOffering().get(i).getName());
//            itemTitle.setId("itemTitle");
//            itemTitle.setFill(Color.YELLOW);
//
//            Text itemDescription = new Text(localMarket.getItemsOffering().get(i).getDescription());
//            itemDescription.setId("itemDescription");
//            itemDescription.setFill(Color.YELLOW);
//
//            int itemFinalPrice = (int)
//                    ( (double) localMarket.getItemsOffering().get(i).getAdjustedPrice()
//                            * (1.00 - 0.01 * Market.getDiscountMerchantLevel() * merchantSkill.getValue()));
//
//            buyButtons.add(new Button("Buy for " + itemFinalPrice + " (-"
//                    + Market.getDiscountMerchantLevel() * merchantSkill.getValue() +"%)"));
//
//            buyButtons.get(i).setId("itemBuyButt");
//
//            if (credits >= itemFinalPrice) {
//                buyButtons.get(i).setStyle("-fx-background-color: rgba(0, 156, 0)");
//            } else {
//                buyButtons.get(i).setStyle("-fx-background-color: rgba(255, 0, 0)");
//            }
//
//            itemVBox.getChildren().addAll(itemTitle, itemDescription, buyButtons.get(i));
//            itemVBox.setAlignment(Pos.CENTER);
//            itemVBox.setId("itemVBox");
//
//            marketItems.getChildren().add(itemVBox);
//        }
//
//        marketItems.setAlignment(Pos.CENTER);
//        marketItems.setHgap(10);
//        marketItems.setVgap(10);


        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(itemsForSaleTitle, marketItems, specialItemsTitle, specialItems, shipCargoTitle);

        pane.setCenter(centerBox);
        pane.setBottom(generateStatsBar());
        BorderPane.setAlignment(pane.getCenter(), Pos.CENTER);

        stackPane.getChildren().addAll(MAP_BACKGROUND, pane);

//        regionBackButton.setOnAction(e -> {
//            try {
//                setStage(new MapScene());
//            } catch (Throwable f) {
//                f.printStackTrace();
//            }
//        });
//
//        enterMarket.setOnAction(e -> {
//            try {
//                setStage(new MapScene());
//            } catch (Throwable f) {
//                f.printStackTrace();
//            }
//        });

        pane.getStylesheets().add("css/Styles.css");
        return stackPane;
    }
}
