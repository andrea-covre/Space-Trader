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
    private int itemAmount;


    public Trader() {
        item = new Item();
        itemAmount = new Random().nextInt(5);
    }

    public Item getItem() {
        return item;
    }

}
