package skills;

import primary.scenes.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class FighterSkill extends Skill {
    public FighterSkill(int i) {
        super(i);
        name = "Fighter";
    }
    @Override
    public void upgrade(Player p) {
        AtomicInteger sp = p.skillPoints();
        if (value > 0) {
            if (sp.get() > 0) {
                p.getFighterSkill().inc(value);
                sp.set(sp.get() - value);
            }
        } else {
            if (p.getFighterSkill().value > 0) {
                p.getFighterSkill().inc(value);
                sp.set(sp.get() - value);
            }
        }
    }
}
