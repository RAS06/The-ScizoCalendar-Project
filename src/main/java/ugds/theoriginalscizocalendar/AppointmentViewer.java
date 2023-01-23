package ugds.theoriginalscizocalendar;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;

public class AppointmentViewer extends Dialog {

    private Appointment viewedAppointment;
    private TextArea out = new TextArea();
    private Button removeButton = new Button("Delete Appointment");
    private AppointmentPane in;
    private int buttonid;

    public AppointmentViewer(Appointment a, int id, AppointmentPane apin){
        super();
        viewedAppointment = a;
        buttonid = id;
        in = apin;
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
        out.setText(viewedAppointment.getInfo());
        removeButton.setOnAction((e) ->{
            File f = new File("src/main/resources/ugds/theoriginalscizocalendar/appointmentStorage/" + viewedAppointment.getID());
            f.delete();
            in.removeButton(buttonid);
            this.close();
        });
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }


    public Pane createGridPane(){
        VBox content = new VBox(18);
        content.getChildren().add(out);
        content.getChildren().add(removeButton);
        content.setPrefWidth(400);
        content.setPrefHeight(250);

        return content;
    }
}
