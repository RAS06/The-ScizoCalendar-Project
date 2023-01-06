package ugds.theoriginalscizocalendar;

import com.google.gson.Gson;
import javafx.scene.text.Font;

import java.io.*;


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
    public static DayButton[][] deserialize(/* Year */ String y, /* Month */ String m) throws IOException {
        DayButton[][] returnStructure = new DayButton[6][7];
        FileInputStream fis = new FileInputStream("src/main/resources/ugds/theoriginalscizocalendar/yearStorage/" + y + ".txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while((line = br.readLine()) != null){
            if(line.contains(m)){
                //Extract all data from line and create button object.
                String mon = line.substring(0, line.indexOf(" "));
                line = line.substring((line.indexOf(" ")) + 1);
                int date = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                line = line.substring((line.indexOf(" ") + 1));
                int year = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                line = line.substring(line.indexOf(" ") + 1);
                int dayOfWeek = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                line = line.substring(line.indexOf(" ") + 2);
                int row = Integer.parseInt(line.substring(0, line.indexOf(" ")));
                line = line.substring(line.indexOf(" ") + 1);
                int col = Integer.parseInt(line.substring(0, line.indexOf("]")));
                DayButton dayToAdd = new DayButton(dayOfWeek, row, col, date, mon, year);
                dayToAdd.setPrefWidth(100);
                dayToAdd.setPrefHeight(100);
                Font font = new Font(40);
                dayToAdd.setFont(font);
                //System.out.println(mon + " " + date + " " + year + " " + dayOfWeek + " " + row + " " + col);
                returnStructure[row][col] = dayToAdd;
            }
        }
//        for (DayButton[] x : returnStructure)
//        {
//            for (DayButton q : x)
//            {
//                System.out.print(q + " ");
//            }
//            System.out.println();
//        }
        br.close();
        return returnStructure;
    }
}
