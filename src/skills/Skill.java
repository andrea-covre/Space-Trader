package skills;

import primary.scenes.Player;

public abstract class Skill {
    protected String name;

    public Skill(int i) {
        value = i;
    }
    protected int value;

    public void inc(int amount) {
        value = value + amount;
    }

    public int getValue() {
        return value;
    }

    protected void dec() {
        value--;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public abstract void upgrade(Player p);

    public void removeUpgrade(Player p) {
        value = -value;
        upgrade(p);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public String getName() {
        return name;
    }
}