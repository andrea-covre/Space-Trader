package primary;

import java.io.FileInputStream;
import java.util.ArrayList;

import java.util.List;

public class Ship {
    private int hp;
    private int maxHp;
    private int cargo;
    private int attack;
    private int fuelCapacity;
    private int fuel;
    private String name;
    private List<Item> items;
    private int upgradeSlots;
    private List<CharacterUpgrade> upgrades;

    public Ship(String name, int cargo,
                int upgradeSlots, int hp,
                int attack, int fuelCapacity, FileInputStream image) {
        this.name = name;
        this.cargo = cargo;
        this.attack = attack;
        this.fuelCapacity = fuelCapacity;
        this.fuel = fuelCapacity;
        this.hp = hp;
        this.maxHp = hp;
        this.upgradeSlots = upgradeSlots;
        this.items = new ArrayList<Item>();
        this.upgrades = new ArrayList<CharacterUpgrade>();
    }

    public Ship(String name, int cargo, int upgradeSlots, int hp, int attack, int fuelCapacity) {
        this.name = name;
        this.cargo = cargo;
        this.attack = attack;
        this.fuelCapacity = fuelCapacity;
        this.fuel = fuelCapacity;
        this.hp = hp;
        this.maxHp = hp;
        this.upgradeSlots = upgradeSlots;
        this.items = new ArrayList<Item>();
        this.upgrades = new ArrayList<CharacterUpgrade>();
    }

    public Ship(String name, int cargo, int upgradeSlots, FileInputStream image) {
        this(name, cargo, upgradeSlots, 10, 10, 3000, image);
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

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
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

