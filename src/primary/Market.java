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

    private Random rand = new Random();

    public void generateMarket(Region currentRegion) {
        Item temp = null;
        for (int i = 0; i < itemsPerMarket; i++) {
            boolean isADuplicate = true;
            while (isADuplicate) {
                temp = new Item();
                isADuplicate = false;
                if (itemsOffering.size() > 0) {
                    for (int j = 0; j < itemsOffering.size(); j++) {
                        if (temp.getName().equals(itemsOffering.get(j).getName())) {
                            isADuplicate = true;
                            break;
                        }
                    }
                }
            }
            itemsOffering.add(temp);
            calculateAdjustedPrice(itemsOffering.get(i), currentRegion.getTechLevel());
        }

        specialItem = new CharacterUpgrade();
        calculateAdjustedPrice(specialItem, currentRegion.getTechLevel());
    }

    //final price is going to be discounted based on the tech level difference between item and region, plus a random
    //decrease or increase
    private void calculateAdjustedPrice(Item item, int regionTech) {
        item.setAdjustedPrice( (int) (item.getPrice() - (regionTech - item.getTechLevel())
                * TECH_LEVEL_DISCOUNT_FACTOR * item.getPrice() + item.getPrice() * rand.nextDouble() / 5) );
    }

    private void calculateAdjustedPrice(CharacterUpgrade item, int regionTech) {
        item.setAdjustedPrice( (int) (item.getPrice() - (regionTech - item.getTechLevel())
                * TECH_LEVEL_DISCOUNT_FACTOR * item.getPrice() + item.getPrice() * rand.nextDouble() / 5) );
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
}

