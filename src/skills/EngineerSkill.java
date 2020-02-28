package skills;

import primary.scenes.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class EngineerSkill extends Skill {
    public EngineerSkill(int i) {
        super(i);
        name = "Engineer";
    }

    @Override
    public void upgrade(Player p) {
        AtomicInteger sp = p.skillPoints();
        if (value > 0) {
            if (sp.get() > 0) {
                p.getEngineerSkill().inc(value);
                sp.set(sp.get() - value);
            }
        } else {
            if (p.getEngineerSkill().value > 0) {
                p.getEngineerSkill().inc(value);
                sp.set(sp.get() - value);
            }
        }
    }


}
