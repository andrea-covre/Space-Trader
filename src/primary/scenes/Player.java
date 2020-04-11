package primary.scenes;

import primary.Ship;
import skills.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {

    protected String playerName;
    protected int credits;
    protected AtomicInteger skillPoints;
    protected Ship playerShip;

    /**
     * skills.Skill levels
     */

    protected PilotSkill pilotSkill = new PilotSkill(0);
    protected FighterSkill fighterSkill = new FighterSkill(0);
    protected MerchantSkill merchantSkill = new MerchantSkill(0);
    protected EngineerSkill engineerSkill = new EngineerSkill(0);

    public Player(String name, int c, int s) throws FileNotFoundException {
        playerName = name;
        credits = c;
        skillPoints = new AtomicInteger(s);
        playerShip = new Ship("basic bitch", 50,
                10, new FileInputStream("src/resources/images/defaultShip.png"));
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCredits() {
        return credits;
    }

    public int getSkillPoints() {
        return skillPoints.get();
    }
    public AtomicInteger skillPoints() {
        return skillPoints;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints.set(skillPoints);
    }

    public void setPlayerName(String name) {
        this.playerName = name; }

    public void resetSkill() {
        setSkillPoints(pilotSkill.getValue() + engineerSkill.getValue()
                + fighterSkill.getValue() + merchantSkill.getValue() + skillPoints.get());
        pilotSkill.setValue(0);
        engineerSkill.setValue(0);
        fighterSkill.setValue(0);
        merchantSkill.setValue(0);
    }

    public Skill getEngineerSkill() {
        return engineerSkill;
    }

    public Skill getFighterSkill() {
        return fighterSkill;
    }

    public Skill getPilotSkill() {
        return pilotSkill;
    }

    public Skill getMerchantSkill() {
        return merchantSkill;
    }

    public Ship getShip() {
        return playerShip;
    }

}
