package ugds.theoriginalscizocalendar;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppointmentDialog extends Dialog<AppointmentData> {

    public TextArea in = new TextArea();
    public Button doneButton = new Button("Save/Complete");
    public DayButton source;
    public AppointmentDialog(DayButton db) {
        super();
        source = db;
        this.setTitle("Add an Appointment!");
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
        doneButton.setOnAction(e -> {
            String save = in.getText();
            AppointmentData aData = new AppointmentData(source, save);
        });


        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }

    public Pane createGridPane(){
        VBox content = new VBox(18);
        content.getChildren().add(in);
        content.getChildren().add(doneButton);
        content.setPrefWidth(400);
        content.setPrefHeight(250);

        return content;
    }
}
