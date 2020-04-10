package skills;

import primary.scenes.Player;

import java.util.Random;

public abstract class Skill {
    protected String name;
    private static  final int SKILLCHECK_MULTIPLIER = 10;
    public boolean skillCheck(int difficultyOrdinal) {
        Random r = new Random();
        return ((r.nextInt(value + 1) * SKILLCHECK_MULTIPLIER) > ((difficultyOrdinal * 40) + 40));

    }

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