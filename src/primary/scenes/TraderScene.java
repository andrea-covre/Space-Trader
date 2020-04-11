package primary.scenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import javafx.concurrent.Task;

import javafx.event.EventHandler;
import javafx.concurrent.WorkerStateEvent;

import primary.Trader;

public class TraderScene extends SceneLoader {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Text item;
    @FXML
    private Text itemDescription;
    @FXML
    private Text result;

    private primary.Trader trader;

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
                        new Image(new File(
                                "src/resources/images/map_background.jpg"
                            ).toURI().toURL().toString()),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        trader = new Trader();
        borderPane.setBackground(new Background(back));
        traderScene();
    }

    @Override
    public Parent build() {
        FXMLLoader loader =  new FXMLLoader();
        loader.setController(this);
        try {
            return loader.load(new File(
                        "src/resources/TraderScene.fxml"
                    ).toURI().toURL());
        } catch (IOException e) {
            System.out.println("ERROR HERE");
            e.printStackTrace();
        }
        return null;
    }
    private void traderScene() {

        result.setStyle("-fx-fill: transparent");

        int itemAmount = trader.getItemCount();
        item.setText(String.format("%d %s for %d Each",
                                    itemAmount,
                                    trader.getItem().getName(),
                                    trader.getPrice()));
        itemDescription.setText(trader.getItem().getDescription());
        generateBuyButton();

        EventHandler<WorkerStateEvent> regionEventEnd = ev -> {
            selectedLocation.getRegionMarket().generateMarket(selectedLocation);
            currentLocation = selectedLocation;
            currentLocation.setBeenVisited(true);
            setStage(new RegionScene());
        };
        EventHandler<WorkerStateEvent> negotationEventEnd = ev ->
                result.setStyle("-fx-fill: transparent");

        buyButton.setOnAction(e -> {
            boolean buy = player.getCredits() >= trader.getPrice()
                    && currentShip.getCargo() >= 1;
            if (buy) {
                // only take as many as you can before the ship gets full.
                int bought = 0;
                for (; bought < itemAmount; bought++) {
                    currentShip.addItem(trader.getItem());
                    player.setCredits(player.getCredits() - trader.getPrice());
                }
                displayResult("Successfully Bought " + bought + " items\n"
                                + "for " + bought * trader.getPrice() + " credits.\n"
                                + "Travelling to Destination",
                                regionEventEnd);
            }
        });

        ignoreButton.setOnAction(e -> {
            displayResult("Ignored Trader.\nTravelling to destination",
                            regionEventEnd);
        });

        robButton.setOnAction(e -> {
            if (player.getFighterSkill().skillCheck(setDifficulty.ordinal())) {
                int numRobbed = new Random().nextInt(itemAmount) + 1;
                int num = 0;
                for (; num < numRobbed && currentShip.getCargo()
                        > currentShip.getItems().size(); num++) {
                    currentShip.addItem(trader.getItem());
                }
                displayResult("Robbed Trader Successfully!\n"
                        + "Received " + num + " items.\n"
                        + "Travelling to destination.", regionEventEnd);
            } else {
                int damage = new Random().nextInt(5);
                currentShip.setHp(currentShip.getHp() - damage);
                displayResult("Failed to Rob Trader.\nShip Lost "
                                    + damage
                                    + " HP\nTravelling to destination.",
                            regionEventEnd);
            }
        });

        negotiateButton.setOnAction(e -> {
            if (trader.canNegotiate()) {
                if (trader.negotiate(player.getMerchantSkill()
                        .skillCheck(setDifficulty.ordinal()))) {
                    displayResult("Negotiation Succeeded: Price Reduced!",
                                    negotationEventEnd);
                } else {
                    displayResult("Negotiation Failed: Price Increased!",
                                    negotationEventEnd);
                }
                int curPrice = trader.getPrice();
                int curTotal = curPrice * itemAmount;

                item.setText(String.format("%d %s for %d",
                                            itemAmount,
                                            trader.getItem().getName(),
                                            curTotal));
                generateBuyButton();

                negotiateButton.setStyle(
                    "-fx-background-color: rgba(255, 0, 0)");
            }
        });
    }

    private void generateBuyButton() {
        boolean canBuy = player.getCredits() >= trader.getPrice()
                && currentShip.getCargo() >= 1;
        if (!canBuy) {
            buyButton.setStyle("-fx-background-color: rgba(255, 0, 0)");
        } else {
            buyButton.setStyle("-fx-background-color: rgba(0, 255, 0)");
        }

    }

    private void displayResult(String res, EventHandler task) {
        result.setText(res);
        result.setStyle("-fx-text-fill: WHITE");
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        if (currentShip.getHp() <= 0) {
            sleeper.setOnSucceeded(event -> {
                regionsGenerated = false;
                setStage(new LoseScene());
            });
        } else {
            sleeper.setOnSucceeded(task);
        }
        new Thread(sleeper).start();
    }

}
