package primary.scenes;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RegionScene extends SceneLoader {
    private Button regionBackButton = new Button("Back to Map");

    @Override
    public Parent build() {
        return regionScene();
    }
    private Pane regionScene() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        Text tempText = new Text(currentLocation.getDescription());
        vBox.getChildren().addAll(tempText, regionBackButton);
        stackPane.getChildren().add(vBox);

        regionBackButton.setOnAction(e -> {
            try {
                setStage(new MapScene());
            } catch (Throwable f) {
                f.printStackTrace();
            }
        });

        return stackPane;
    }

}
