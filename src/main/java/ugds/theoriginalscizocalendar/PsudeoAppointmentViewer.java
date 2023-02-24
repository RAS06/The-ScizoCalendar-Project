package ugds.theoriginalscizocalendar;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Optional;

public class PsudeoAppointmentViewer extends Dialog {

    public String storySnippet;
    public TextArea ta = new TextArea();
    public Button end = new Button("I Understand");
    public DayButton src;
    public HelloApplication hellApp;

    public PsudeoAppointmentViewer(String textIn, DayButton srcIn, HelloApplication hellAppIn){
        storySnippet = textIn;
        src = srcIn;
        hellApp = hellAppIn;
        ta.setText(storySnippet);
        ta.setEditable(false);
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);


    }


    public Pane createGridPane(){
        VBox content = new VBox(18);

        content.getChildren().addAll(ta, end);
        end.setLayoutY(100);
        Window window = this.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());

        end.setOnAction(e ->{
            window.hide();
            src.addEventHandler(MouseEvent.MOUSE_ENTERED, e1 -> {
                src.setFont(new Font(11));
                src.setText("+ Create or view\n Appointments!");
            });
            src.addEventHandler(MouseEvent.MOUSE_EXITED, e1 -> {
                src.setFont(new Font(40));
                src.setText(src.getName());
            });
            src.setOnAction(
                    (e2) -> {
                        ArrayList<String> appointments = hellApp.seekAssociation(src);
                        AppointmentPane ap = new AppointmentPane(src, hellApp);
                        if(appointments != null){
                            //System.out.println(appointments);
                            ap.sendAppointmentsTo(appointments);
                        }
                        ap.buildUI();
                        Optional<AppointmentData> result = ap.showAndWait();
                    });
        });
        content.setPrefWidth(400);
        content.setPrefHeight(250);

        return content;
    }
}
