package skills;

import primary.scenes.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class PilotSkill extends Skill {
    public PilotSkill(int i) {
        super(i);
        name = "Pilot";
    }
    @Override
    public void upgrade(Player p) {
        AtomicInteger sp = p.skillPoints();
        if (value > 0) {
            if (sp.get() > 0) {
                p.getPilotSkill().inc(value);
                sp.set(sp.get() - value);
            }
        } else {
            if (p.getPilotSkill().value > 0) {
                p.getPilotSkill().inc(value);
                sp.set(sp.get() - value);
            }
        }
    }
}
