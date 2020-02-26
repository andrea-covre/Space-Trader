package primary;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates a Character Upgrade Object
 *
 * This class holds two extra variables than Item: skillID and incAmount
 */

public class CharacterUpgrade {
    private String name;
    private String description;
    private int price;
    private int skillID;
    private int incAmount;
    private Random rand = new Random();

    /**
     * Constructor for Character Upgrade class
     */
    public CharacterUpgrade() {
        int[] probability = new int[]{1,1,1,1,1,1,2,2,2,3};
        String[] var = generateUpgrade();
        name = "Supreme " + var[0];
        description = var[1];
        incAmount = probability[rand.nextInt(10)];
        price = Integer.parseInt(var[2]) + (incAmount * 100);
        skillID = Integer.parseInt(var[3]);
    }
    /**
     * Generates Character Upgrade from the Character Upgrade text file in the resources file
     *
     * For skill ID's:
     * 0 represents Pilot
     * 1 represents Fighter
     * 2 represents Merchant
     * 3 represents Engineer
     *
     * incAmount is how much the Upgrade increments a skill, dependent on a probability scheme
     * The probability is 60% for an increase of 1, 30% for an increase of 2, and 10% for an increase of 3
     * The incAmount also will slightly increase the price of a character upgrade:
     * Increase of 100 credits for 1, 200 credits for 1, and 300 credits for 3
     */
    private String[] generateUpgrade() {
        ArrayList<String> upgradeDictionary = new ArrayList<>();
        BufferedReader upgradeSC = null;
        String[] var = null;
        String a = null;

        try {
            File items = new File("resources/CharacterUpgrade.txt");
            upgradeSC = new BufferedReader(new FileReader(items));
        } catch(FileNotFoundException e) {
            System.out.println("Character Upgrades "
                    + "file is missing from the resources folder");
        }
        assert upgradeSC != null;
        try {
            while ((a = upgradeSC.readLine()) != null) {
                upgradeDictionary.add(a);
            }
            String s = upgradeDictionary.get(rand.nextInt(upgradeDictionary.size()));
            var = s.split(":");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return var;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getPrice() {
        return price;
    }
    public int getSkillID() {
        return skillID;
    }
    public int getIncAmount() {
        return incAmount;
    }

}
