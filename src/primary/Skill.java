package primary;

public class Skill {
    private int value;


    public Skill(int i) {
        value = i;
    }

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

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}