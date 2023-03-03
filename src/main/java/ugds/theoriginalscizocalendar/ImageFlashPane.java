package ugds.theoriginalscizocalendar;

import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

public class ImageFlashPane extends Dialog {
    private Path p;

    public ImageFlashPane() {
        super();
    }

    public void receivePath(Path pIn){
        p = pIn;
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
    }

    public void closeThis() {

    }


    public Pane createGridPane() {
        VBox content = new VBox(18);

        Image i = new Image(p.toString());
        content.getChildren().add(new ImageView(i));

        content.setPrefWidth(600);
        content.setPrefHeight(600);

        return content;
    }
}
