package primary;

import java.io.FileInputStream;
import java.util.ArrayList;

import java.util.List;

public class Ship {
    private int hp;
    private int maxHp;
    private int cargo;
    private int attack;
    private String name;
    private List<Item> items;
    private int upgradeSlots;
    private List<CharacterUpgrade> upgrades;
    private FileInputStream image;

    public Ship(String name, int cargo,
                int upgradeSlots, int hp,
                int attack, FileInputStream image) {
        this.name = name;
        this.cargo = cargo;
        this.attack = attack;
        this.hp = hp;
        this.maxHp = hp;
        this.upgradeSlots = upgradeSlots;
        this.items = new ArrayList<Item>();
        this.upgrades = new ArrayList<CharacterUpgrade>();
        this.image = image;
    }

    public Ship(String name, int cargo, int upgradeSlots, int hp, int attack) {
        this.name = name;
        this.cargo = cargo;
        this.attack = attack;
        this.hp = hp;
        this.maxHp = hp;
        this.upgradeSlots = upgradeSlots;
        this.items = new ArrayList<Item>();
        this.upgrades = new ArrayList<CharacterUpgrade>();
    }

    public Ship(String name, int cargo, int upgradeSlots, FileInputStream image) {
        this(name, cargo, upgradeSlots, 10, 10, image);
    }



    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getCargo() {
        return cargo;
    }

    public int getHp() {
        return hp;
    }


    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getUpgradeSlots() {
        return upgradeSlots;
    }

    public List<CharacterUpgrade> getUpgrades() {
        return upgrades;
    }

}

