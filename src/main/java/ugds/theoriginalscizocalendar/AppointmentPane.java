package ugds.theoriginalscizocalendar;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppointmentPane extends Dialog<AppointmentData> {
    public Button addAppointment = new Button("Add Appointment");
    public DayButton source;
    public Button viewAppointment = new Button("View Appointments");

    public AppointmentPane(DayButton db) {
        super();
        this.setTitle("View or Add Appointments!");
        source = db;
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
        addAppointment.setOnAction((e) -> {
            AppointmentDialog ad = new AppointmentDialog(source);
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
