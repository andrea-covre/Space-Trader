package primary;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import primary.scenes.SceneLoader;
import primary.scenes.SkillsLevelSelectionScene;

/**
 * "inner" class to deal with button events
 * for incrementing and decrementing skills
 */
public class IncrSkill extends SceneLoader implements EventHandler<javafx.event.ActionEvent> {
    private Skill skill;
    private int amount;

    public IncrSkill(Skill s, int i) {
        skill = s;
        amount = i;
    }

    @Override
    public void handle(ActionEvent event) {
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