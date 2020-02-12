package primary;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import primary.scenes.SceneBuilder;
import primary.scenes.SkillsLevelSelectionScene;

/**
 * "inner" class to deal with button events
 * for incrementing and decrementing skills
 */
public class IncrSkill extends SceneBuilder implements EventHandler {
    private Skill skill;
    private int amount;

    public IncrSkill(Skill s, int i) {
        skill = s;
        amount = i;
    }

    @Override
    public void handle(Event event) {
        setStage(this);
    }

    @Override
    public Parent build() {
        if (!(skill.getValue() + amount < 0 || skillPoints - amount < 0)) {
            skillPoints = skillPoints - amount;
            skill.inc(amount);
        }
        return new SkillsLevelSelectionScene().build();
    }
}