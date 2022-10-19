package ugds.theoriginalscizocalendar;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SerializationMachine {
    public SerializationMachine(){}
    public static void serialize(DayButton db){
        String s = db.toString();

        try{
            FileWriter fw = new FileWriter("src/main/resources/ugds/theoriginalscizocalendar/yearStorage/" + HelloApplication.year + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            if(!s.contains("December 31")){
                bw.write(s + "\n");
            } else{bw.write(s);}

            bw.close();

        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
