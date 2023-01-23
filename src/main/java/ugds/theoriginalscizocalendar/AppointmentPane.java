package ugds.theoriginalscizocalendar;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

public class AppointmentPane extends Dialog {
    public Button addAppointment = new Button("Add Appointment");
    public DayButton source;
    public Button viewAppointment = new Button("View Appointments");
    public static ArrayList<Button> appointmentButtons = new ArrayList<>();
    public ArrayList<String> appointments = new ArrayList<>();
    public Pane pane = createGridPane();

    public AppointmentPane(DayButton db) {
        super();
        this.setTitle("View or Add Appointments!");
        source = db;
    }

    public void sendAppointmentsTo(ArrayList<String> in){
        appointments = in;
    }


    public void buildUI() {

        getDialogPane().setContent(pane);
        addAppointment.setOnAction((e) -> {
            AppointmentDialog ad = new AppointmentDialog(source);
            ad.showAndWait();
        });
        //System.out.println(appointments);
        if(appointments.size() > 0) {
            //System.out.println("Appointments read");
            ArrayList<Appointment> add = readAppointments();

            for (int i = 0; i < add.size(); i++) {
                Button b = new Button("Appointment " + (i + 1));
                appointmentButtons.add(b);
                pane.getChildren().add(b);
                b.setLayoutX(10);
                b.setLayoutY(100 * (i + 1));

                int finalI = i;
                b.setOnAction((e)-> {
                    AppointmentViewer av = new AppointmentViewer(add.get(finalI), finalI, this);
                    av.showAndWait();
                });
            }
        }
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }
    public void removeButton(int id){
        pane.getChildren().remove(appointmentButtons.get(id));
    }

    private ArrayList<Appointment> readAppointments() {
        ArrayList<Appointment> appointmentItems = new ArrayList<>();
        for(int i = 0; i < appointments.size(); i++) {
            try {
                FileInputStream fis = new FileInputStream("src/main/resources/ugds/theoriginalscizocalendar/appointmentStorage/" + appointments.get(i));
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String info = "";
                String add;
                while((add = br.readLine()) != null) {
                    info = info + add;
                }
                Appointment a = new Appointment(appointments.get(i), info);
                appointmentItems.add(a);
                br.close();;
            }catch(IOException ioe){ioe.printStackTrace();}
        }
        return appointmentItems;
    }


    public Pane createGridPane(){
        VBox content = new VBox(18);
        content.getChildren().add(addAppointment);
        content.setPrefWidth(600);
        content.setPrefHeight(600);

        return content;
    }
}
