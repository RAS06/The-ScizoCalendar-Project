package ugds.theoriginalscizocalendar;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import com.google.gson.Gson;


import java.io.IOException;


public class HelloApplication extends Application {

    public static int year;
    public static int dayOfWeek;
    public static int daysToPrint;
    public static int firstDayOfWeek;
    public static int dayOfYear;
    public static ArrayList<String> week = new ArrayList<String>(Arrays.asList("List doesn't start at zero for some reason", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

                 //Binding attempts: result failure. Remains for fallback purposes.
        //AnchorPane a = new AnchorPane();
        //a.setStyle("-fx-background-color: black");
        //DoubleProperty dp =  new SimpleDoubleProperty(a.widthProperty().getValue() / 2);
        //b.layoutXProperty().bind(dp);
        //a.getChildren().add(b);

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setStyle("-fx-background-color: black");
        //gp.add(new DayButton("Skeet"), 0, 0);
        //gp.add(new DayButton("Yeeet"), 1, 1);
        //gp.add(new DayButton("Reeet"), 2, 2);

        for(int i = 0; i < 7; i++){
            for(int j = 9; j < 15; j++){
                DayButton dateButton = new DayButton("String Literal");
                dateButton.setPrefWidth(100);
                dateButton.setPrefHeight(100);
                gp.add(dateButton, i, j);
            }
        }

        stage.setHeight(1000);
        stage.setWidth(1000);

        DailyData testData = new DailyData("Name");
        //System.out.println(SerializationMachine.turnIntoJson(testData));

        Scene scene = new Scene(gp, 900, 240);
        stage.setTitle("Hello!");
        buildUI();
        stage.setScene(scene);
        stage.show();


    }

    public void buildUI(){
        Calendar c = Calendar.getInstance();

        //Figure out what year it is.
        year = c.get(Calendar.YEAR);
        dayOfYear = c.get(Calendar.DAY_OF_YEAR);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //System.out.println(c.get(Calendar.DAY_OF_YEAR));

        Path p = Paths.get("src/main/resources/ugds/theoriginalscizocalendar/yearStorage" + year + ".txt");

        if(!Files.exists(p)) { //Replace later with conditional to test the presence or absence of a file with the proper name.

            //What was the first day of the week of the year?
            //Find the lowest multiple of seven that is not less than the current day of the year.
            int multiple = 1;
            while (multiple + 7 <= dayOfYear) {
                multiple += 7;
            }
            //System.out.println(multiple);
            int daysUntilTargetDayOfWeek = dayOfYear - multiple;
            System.out.println(daysUntilTargetDayOfWeek);
            System.out.println(dayOfWeek);
            while (daysUntilTargetDayOfWeek > 0) {
                if (dayOfWeek - 1 == 0) {
                    dayOfWeek = 8;
                }
                daysUntilTargetDayOfWeek--;
                dayOfWeek--;
            }

            //System.out.println(dayOfWeek);

            //And now write lines in new .txt files that will store data for each button

            try {
                FileWriter fw = new FileWriter("src/main/resources/ugds/theoriginalscizocalendar/yearStorage/" + year + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.close();
                System.out.println("Created");
            } catch(IOException ioe){
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Found");
        }
        //Print lines to demarcate data for each button element in the .txt file.
        try {
            FileWriter fw = new FileWriter("src/main/resources/ugds/theoriginalscizocalendar/yearStorage/" + year + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            if(year % 4 == 0)
                daysToPrint = 366;
            else
                daysToPrint = 365;


        } catch (IOException ioe){
            ioe.printStackTrace();
        }




        //Now construct a series of 2D Arrays (24 for two years, Last Jan to next year's December).

        ArrayList<Month> printable = new ArrayList<Month>();
        ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

        int currMonth = 0;
        try {
            FileWriter fw = new FileWriter("src/main/resources/ugds/theoriginalscizocalendar/yearStorage/" + year + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < 24; i++){
                if(currMonth == 12) {
                    currMonth = 0;
                    year++;
                }

                //Month m = new Month(months.get(i) + year);
               if(months.get(currMonth).equals("January") || months.get(currMonth).equals("March") || months.get(currMonth).equals("May") || months.get(currMonth).equals("July") || months.get(currMonth).equals("August") || months.get(currMonth).equals("October") || months.get(currMonth).equals("December")){

                   for(int j = 1; j < 32; j++){
                       //Create objects
                       DayButton dayToAdd = new DayButton(months.get(currMonth) + " " + j + " " + year);
                       dayToAdd.assignDayOfWeek(dayOfWeek);
                       dayOfWeek++;
                       System.out.println(dayToAdd.toString());

                       if(dayOfWeek == 8){
                           dayOfWeek = 1;
                       }
                   }
               } else if(months.get(currMonth).equals("April") || months.get(currMonth).equals("June") || months.get(currMonth).equals("September") || months.get(currMonth).equals("November")){
                   for(int k = 1; k < 31; k++){
                       //DayButton dayToAdd = new DayButton(months.get(i) + " " + k + " " + year);
                       //gson.toJson(dayToAdd);
                   }
               } else if (months.get(currMonth).equals("February") && daysToPrint == 366){
                   for(int l = 1; l < 30; l ++){
                       //DayButton dayToAdd = new DayButton(months.get(i) + " " + l + " " + year);
                   }
               } else{
                   for(int n = 1; n < 29; n++){
                       // dayToAdd = new DayButton(months.get(i) + " " + m + " " + year);
                       //gson.toJson(dayToAdd);
                   }
               }
                currMonth++;
            }


        } catch (IOException ioe){
            ioe.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}
