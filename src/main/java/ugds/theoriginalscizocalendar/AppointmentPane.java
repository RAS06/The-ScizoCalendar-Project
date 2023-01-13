package ugds.theoriginalscizocalendar;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppointmentPane extends Dialog<DialogData> {
    public Button addAppointment = new Button("Add Appointment");
    public Button viewAppointment = new Button("View Appointments");
    public AppointmentPane() {
        super();
        this.setTitle("View or Add Appointments!");
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
        addAppointment.setOnAction((e) -> {
            AppointmentDialog ad = new AppointmentDialog();
            ad.showAndWait();
        });

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }

    public Pane createGridPane(){
        VBox content = new VBox(18);
        content.getChildren().add(addAppointment);
        content.setPrefWidth(600);
        content.setPrefHeight(600);

        return content;
    }
}
