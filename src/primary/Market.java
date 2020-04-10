package primary;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class contains the items for each region's marketplace
 */
public class Market {
    private ArrayList<Item> itemsOffering = new ArrayList<Item>();
    private CharacterUpgrade specialItem;
    private static int itemsPerMarket = 6;
    private static final double TECH_LEVEL_DISCOUNT_FACTOR = 0.1;
    private static final double DISCOUNT_MERCHANT_LEVEL = 3;
    private static final double MARKET_DEPRECIATION = 0.9;
    private static final double FUEL_COST_PER_UNIT = 1.5;
    private static final int FUEL_PER_JUG = 100;
    private static final int COST_PER_HP = 200;
    private static final int COST_WINNING_ITEM = 1000;

    private Random rand = new Random();

    public void generateMarket(Region currentRegion) {
        itemsOffering = new ArrayList<Item>();
        Item temp = null;
        for (int i = 0; i < itemsPerMarket; i++) {
            boolean isNotValid = true;
            while (isNotValid) {
                temp = new Item();
                isNotValid = false;
                if (itemsOffering.size() > 0) {
                    for (Item item : itemsOffering) {
                        if (temp.getName().equals(item.getName())) {
                            isNotValid = true;
                            break;
                        }
                    }
                }
                if (temp.getTechLevel() > currentRegion.getTechLevel()) {
                    isNotValid = true;
                }
            }
            itemsOffering.add(temp);
            calculateAdjustedPrice(itemsOffering.get(i), currentRegion.getTechLevel());
        }

        boolean isNotValidUpgrade = true;
        CharacterUpgrade tempUpgrade = null;
        while (isNotValidUpgrade) {
            tempUpgrade = new CharacterUpgrade();
            isNotValidUpgrade = tempUpgrade.getTechLevel() > currentRegion.getTechLevel();
        }
        specialItem = tempUpgrade;
    }

    //final price is going to be discounted based on the tech level
    // difference between item and region, plus a random
    //decrease or increase
    private void calculateAdjustedPrice(Item item, int regionTech) {
        item.setAdjustedPrice((int) (item.getPrice()
                - (regionTech - item.getTechLevel())
                * TECH_LEVEL_DISCOUNT_FACTOR * item.getPrice()
                + item.getPrice() * rand.nextDouble() / 5));
    }


    private void calculateAdjustedPrice(CharacterUpgrade item, int regionTech) {
        item.setAdjustedPrice((int) (item.getPrice() - (regionTech - item.getTechLevel())
                * TECH_LEVEL_DISCOUNT_FACTOR * item.getPrice()
                + item.getPrice() * rand.nextDouble() / 5));
    }

    public ArrayList<Item> getItemsOffering() {
        return itemsOffering;
    }

    public int getItemsPerMarket() {
        return itemsPerMarket;
    }

    public static double getDiscountMerchantLevel() {
        return DISCOUNT_MERCHANT_LEVEL;
    }

    public CharacterUpgrade getSpecialItem() {
        return specialItem;
    }

    public static double getMarketDepreciation() {
        return MARKET_DEPRECIATION;
    }

    public static double getFuelCostPerUnit() {
        return FUEL_COST_PER_UNIT;
    }

    public static int getFuelPerJug() {
        return FUEL_PER_JUG;
    }

    public static int getCostPerHp() {
        return COST_PER_HP;
    }

    public static int getCostWinningItem() {
        return COST_WINNING_ITEM;
    }
}

