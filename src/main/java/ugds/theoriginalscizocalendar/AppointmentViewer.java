package ugds.theoriginalscizocalendar;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AppointmentViewer extends Dialog {

    private Appointment viewedAppointment;
    private TextArea out = new TextArea();

    public AppointmentViewer(Appointment a){
        super();
        viewedAppointment = a;
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
        out.setText(viewedAppointment.getInfo());
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }


    public Pane createGridPane(){
        VBox content = new VBox(18);
        content.getChildren().add(out);
        content.setPrefWidth(400);
        content.setPrefHeight(250);

        return content;
    }
}
