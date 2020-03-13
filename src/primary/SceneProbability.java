package primary;

import javafx.scene.Parent;
import javafx.scene.Scene;
import primary.scenes.*;

import java.util.Map;
import java.util.Random;

public class SceneProbability extends SceneLoader{
    private Random rand = new Random();
    private int random = rand.nextInt(11);
    private int [] probabilities = new int[]{0,0,1,1,2,2,3,3,3,3,3};
    public void Probability() {
        int sceneProb = probabilities[random];
        if (sceneProb == 0) {
            try {
                setStage(new TraderScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else if (sceneProb == 1) {
            try {
                setStage(new BanditScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else if (sceneProb == 2) {
            try {
                setStage(new BanditScene()); // Replace with the Police Scene
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else if (sceneProb == 3) {
            try {
                setStage(new RegionScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        }
    }

    @Override
    public Parent build() {
        return null;
    }
}
