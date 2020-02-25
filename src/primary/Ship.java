package primary;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Ship {
    private int hp;
    private int cargo;
    private int attack;
    public String name;
    private ArrayList[] items;
    FileInputStream image;

    public Ship(String n, int c, FileInputStream i, int h, int a) {
        name = n;
        cargo = c;
        attack = a;
        hp = h;
        items = new ArrayList[c];
        image = i;
    }

    public Ship(String n, int c, FileInputStream i) {
        this(n,c, i ,10,10);
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

}
