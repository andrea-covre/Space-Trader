package primary;

import primary.Ship;
import skills.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

import primary.Item;

public class Trader {

    private Item item;
    private int price;
    private int itemAmount;
    private boolean hasNegotiated = false;


    public Trader() {
        item = new Item();
        itemAmount = new Random().nextInt(5) + 1;
        price = item.getPrice();
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public int getItemCount() {
        return itemAmount;
    }

    public boolean canNegotiate() {
        return !hasNegotiated;
    }

    public boolean negotiate(boolean merchantCheck) {
        if (hasNegotiated) return false;
        hasNegotiated = true;

        if (merchantCheck) {
            price = price / 10;
        } else {
            price = price * 2;
        }
        return merchantCheck;
    }

}
