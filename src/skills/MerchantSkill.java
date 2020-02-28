package skills;

import primary.scenes.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class MerchantSkill extends Skill {
    public MerchantSkill(int i) {
        super(i);
        name = "Merchant";
    }
    @Override
    public void upgrade(Player p) {
        AtomicInteger sp = p.skillPoints();
        if (value > 0) {
            if (sp.get() > 0) {
                p.getMerchantSkill().inc(value);
                sp.set(sp.get() - value);
            }
        } else {
            if (p.getMerchantSkill().value > 0) {
                p.getMerchantSkill().inc(value);
                sp.set(sp.get() - value);
            }
        }
    }
}
