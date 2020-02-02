import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 * "inner" class to deal with button events
 * for incrementing and decrementing skills
 */
class IncrSkill implements EventHandler {
    private Skill skill;
    private int amount;

    IncrSkill(Skill s, int i) {
        skill = s;
        amount = i;
    }

    @Override
    public void handle(Event event) {
        if (!(skill.getValue() + amount < 0 || NewGame.skillPoints - amount < 0)) {
            NewGame.skillPoints = NewGame.skillPoints - amount;
            skill.inc(amount);
            try {
                NewGame.theStage.setScene(new Scene(NewGame.skillsLevelSelection()));
            } catch (Throwable f) {
                f.printStackTrace();
            }
        }
    }
}