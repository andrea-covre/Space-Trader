package primary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates a Item Object
 */
public class Item {
    private String name;
    private String description;
    private int price;
    private int techLevel;
    private int adjustedPrice;
    private int adjustedSellingPrice;
    private int finalBuyPrice;
    private int finalSellPrice;

    /**
     * Constructor for Item class
     */
    public Item() {
        String[] var = generateItem();
        name = var[0];
        description = var[1];
        price = Integer.parseInt(var[2]);
        techLevel = Integer.parseInt(var[3]);
    }
    /**
     * This method generates an Item from the Items text file in the resources file
     * @return the item string
     */
    public String[] generateItem() {
        ArrayList<String> itemDictionary = new ArrayList<>();
        BufferedReader itemsSC = null;
        Random rand = new Random();
        String[] var = null;
        String a;

        try {
            File items = new File("src/resources/Items.txt");
            itemsSC = new BufferedReader(new FileReader(items));
        } catch (FileNotFoundException e) {
            System.out.println("Items "
                    + "file is missing from the resources folder");
        }
        assert itemsSC != null;
        try {
            while ((a = itemsSC.readLine()) != null) {
                itemDictionary.add(a);
            }
            String s = itemDictionary.get(rand.nextInt(itemDictionary.size()));
            var = s.split(":");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return var;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public int getAdjustedPrice() {
        return adjustedPrice;
    }

    public void setAdjustedPrice(int adjustedPrice) {
        this.adjustedPrice = adjustedPrice;
    }

    public int getAdjustedSellingPrice() {
        return adjustedSellingPrice;
    }

    public void setAdjustedSellingPrice(int adjustedSellingPrice) {
        this.adjustedSellingPrice = adjustedSellingPrice;
    }

    public int getFinalBuyPrice() {
        return finalBuyPrice;
    }

    public int getFinalSellPrice() {
        return finalSellPrice;
    }

    public void setFinalBuyPrice(int finalBuyPrice) {
        this.finalBuyPrice = finalBuyPrice;
    }

    public void setFinalSellPrice(int finalSellPrice) {
        this.finalSellPrice = finalSellPrice;
    }
}
