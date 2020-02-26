package primary.scenes;

import primary.Ship;
import primary.Skill;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player {

    protected String playerName;
    protected  int credits;
    protected  int skillPoints;
    protected  Ship playerShip;

    /**
     * primary.Skill levels
     */
    protected  Skill pilotSkill = new Skill(0);
    protected  Skill fighterSkill = new Skill(0);
    protected  Skill merchantSkill = new Skill(0);
    protected Skill engineerSkill = new Skill(0);

    public Player(String name, int c, int s) throws FileNotFoundException {
        playerName = name;
        credits = c;
        skillPoints = s;
        playerShip = new Ship("basic bitch", 50, new FileInputStream("resources/defaultShip.png"));
    }

    public void IncrPilot(int a) {
        if (a > 0){
            if (skillPoints > 0) {
                pilotSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        } else {
            if (pilotSkill.getValue() > 0) {
                pilotSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        }
    }
    public void IncrFighter(int a) {
        if (a > 0){
            if (skillPoints > 0) {
                fighterSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        } else {
            if (fighterSkill.getValue() > 0) {
                fighterSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        }

    }
    public void IncrMerchant(int a) {
        if (a > 0) {
            if (skillPoints > 0) {
                merchantSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        } else {
            if (merchantSkill.getValue() > 0) {
                merchantSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        }

    }
    public void IncrEngineer(int a) {
        if (a > 0) {
            if (skillPoints > 0) {
                engineerSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        } else {
            if (engineerSkill.getValue() > 0) {
                engineerSkill.inc(a);
                skillPoints = skillPoints - a;
            }
        }
    }
    public String getPlayerName() {
        return playerName;
    }

    public int getCredits() {
        return credits;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void resetSkill() {
        setSkillPoints(pilotSkill.getValue() + engineerSkill.getValue()
                + fighterSkill.getValue() + merchantSkill.getValue() );
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
