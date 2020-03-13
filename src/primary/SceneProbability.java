package primary;

import javafx.scene.Parent;
import primary.scenes.*;
import java.util.Random;

public class SceneProbability extends SceneLoader {
    private Random rand = new Random();
    private int random = rand.nextInt(11);
    private static int[][] probabilities = new int[][]{
            {0, 0, 0, 1, 2, 3, 3, 3, 3, 3, 3}, //ez
            {0, 0, 1, 1, 2, 2, 3, 3, 3, 3, 3}, // med
            {0, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3}}; // hard

    public void probability() {
        int sceneProb = probabilities[setDifficulty.ordinal()][random];
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
                if (currentShip.getItems().size() == 0) {
                    selectedLocation.getRegionMarket().generateMarket(selectedLocation);
                    currentLocation = selectedLocation;
                    currentLocation.setBeenVisited(true);
                    setStage(new RegionScene());
                } else {
                    setStage(new SPEncounterScene());
                }
            } catch (Throwable f) {
                f.printStackTrace();
            }
        } else if (sceneProb == 3) {
            try {
                selectedLocation.getRegionMarket().generateMarket(selectedLocation);
                currentLocation = selectedLocation;
                currentLocation.setBeenVisited(true);
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
