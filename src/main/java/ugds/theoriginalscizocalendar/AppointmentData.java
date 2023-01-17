package ugds.theoriginalscizocalendar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AppointmentData {
    public String path;
    public DayButton source;
    public String appointmentInfo;
    public AppointmentData(DayButton src, String data){
        source = src;
        appointmentInfo = data;
        constructFile();
    }

    private void constructFile() {
        String pathID = "";
        for(int i = 0; i < source.toString().length() - 6; i++){
            if(!source.toString().substring(i, i + 1).equals(" ")){
                pathID = pathID + source.toString().substring(i, i + 1);
            }
        }
        int subIDIndicies;
        if (appointmentInfo.length() > 10) {
            String noSpace = "";
            for(int i = 0; i < 10; i++){
                if(!appointmentInfo.toString().substring(i, i + 1).equals(" ")){
                    pathID = pathID + appointmentInfo.substring(i, i + 1);
                }
            }
            pathID = pathID + noSpace;
        } else {
            String noSpace = "";
            for(int i = 0; i < appointmentInfo.length(); i++){
                if(!appointmentInfo.toString().substring(i, i + 1).equals(" ")){
                    pathID = pathID + appointmentInfo.substring(i, i + 1);
                }
            }
            pathID = pathID + noSpace;
        }
        try {
            path = "src/main/resources/ugds/theoriginalscizocalendar/appointmentStorage/" + pathID + ".txt";
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(appointmentInfo);
            bw.close();
        } catch(IOException ioe){ioe.printStackTrace();}
    }


}
