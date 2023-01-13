package ugds.theoriginalscizocalendar;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppointmentDialog extends Dialog<DialogData> {

    public TextArea in = new TextArea();
    public AppointmentDialog() {
        super();
        this.setTitle("Add Appointments!");
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }

    public Pane createGridPane(){
        VBox content = new VBox(18);
        content.getChildren().add(in);
        content.setPrefWidth(400);
        content.setPrefHeight(250);

        return content;
    }
}
