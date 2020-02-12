import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class SceneBuilder {

    protected enum Difficulty {
        EASY, MEDIUM, HARD;
    };
    protected String playerName;
    protected Difficulty difficulty;
    protected int credits;
    protected int skillPoints;
    protected static double GAMEVOLUME = 0.02;
    /**
     * Skill levels
     */

    protected Skill pilotSkill = new Skill(0);
    protected Skill fighterSkill = new Skill(0);
    protected Skill merchantSkill = new Skill(0);
    protected Skill engineerSkill = new Skill(0);
    protected final ImageView BACKGROUND = new ImageView(
            new Image(Main.backGround, 960, 1280, false, false));
    public abstract Parent build();

    public abstract void setStage(SceneBuilder b);


}
