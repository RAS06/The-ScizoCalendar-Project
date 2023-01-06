package ugds.theoriginalscizocalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class HelloApplication extends Application {

    public static int year;
    public static int dayOfWeek;
    public static int daysToPrint;
    public static int firstDayOfWeek;
    public static int dayOfYear;
    public static ArrayList<String> week = new ArrayList<String>(Arrays.asList("List doesn't start at zero for some reason", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));
    public  GridPane gp = new GridPane();
    public ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //Binding attempts: result failure. Remains for fallback purposes.
        //AnchorPane a = new AnchorPane();
        //a.setStyle("-fx-background-color: black");
        //DoubleProperty dp =  new SimpleDoubleProperty(a.widthProperty().getValue() / 2);
        //b.layoutXProperty().bind(dp);
        //a.getChildren().add(b);


        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setStyle("-fx-background-color: black");
        //gp.add(new DayButton("Skeet"), 0, 0);
        //gp.add(new DayButton("Yeeet"), 1, 1);
        //gp.add(new DayButton("Reeet"), 2, 2);



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

    public void buildUI() throws IOException {
        constructStructure();
        DayButton[][] mutatorsReference = new DayButton[6][7];
        Label l = new Label("January " + year);
        l.setPrefWidth(100);
        l.setPrefHeight(100);
        l.setStyle("-fx-text-fill: white;" + "-fx-font-size: 12pt;" + "-fx-border-style: solid;" + "-fx-border-color: white;" + "-fx-border-width: 1pt;");

        gp.add(l, 3, 0);
        AtomicInteger currentDisplayedMonth = new AtomicInteger(1);
        AtomicInteger displayYear = new AtomicInteger(year);

        Button left = new Button("Previous");
        gp.add(left, 1, 0);
        left.setOnAction(e -> {
            if(!(currentDisplayedMonth.get() <= 1)){
                currentDisplayedMonth.getAndDecrement();
            }
            int index = currentDisplayedMonth.get();
            //Calculate and Change
            if(currentDisplayedMonth.get() > 12){

                index -= 12;
            }
            if(currentDisplayedMonth.get() == 12){
                displayYear.getAndDecrement();
            }
            l.setText(months.get(index - 1 ) + " " + displayYear.get());
            try {
                DayButton[][] implant = SerializationMachine.deserialize(String.valueOf(displayYear.get()), months.get(index - 1));
                for(int row = 0; row < 6; row++){
                    for(int col = 0; col < 7; col++){
                        gp.getChildren().remove(mutatorsReference[row][col]);
                        if(implant[row][col] != null) {
                            gp.add(implant[row][col], col, row + 9);
                        } else{
                            DayButton empty = new DayButton("");
                            empty.setPrefWidth(100);
                            empty.setPrefHeight(100);
                            gp.add(empty, col, row + 9);
                        }
                    }
                }
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        });

        Button right = new Button("Next");
        gp.add(right, 6, 0);
        right.setOnAction(e ->{
            if(!(currentDisplayedMonth.get() >= 24)) {
                currentDisplayedMonth.getAndIncrement();
            }
            int index = currentDisplayedMonth.get();
            //Calculate and Change
            if(currentDisplayedMonth.get() > 12){
                index -= 12;
            }
            if(currentDisplayedMonth.get() == 12){
                displayYear.getAndIncrement();
            }
            l.setText(months.get(index - 1 ) + " " + displayYear.get());
            try {
                DayButton[][] implant = SerializationMachine.deserialize(String.valueOf(displayYear.get()), months.get(index - 1));
                for(int row = 0; row < 6; row++){
                    for(int col = 0; col < 7; col++){
                        gp.getChildren().remove(mutatorsReference[row][col]);
                        if(implant[row][col] != null) {
                            gp.add(implant[row][col], col, row + 9);
                        } else{
                            DayButton empty = new DayButton("");
                            empty.setPrefWidth(100);
                            empty.setPrefHeight(100);
                            gp.add(empty, col, row + 9);
                        }
                    }
                }
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        });

        //Organize buttons for visual mutations

        //ROW-MAJOR
        for(int i = 9; i < 15; i++){
            for(int j = 0; j < 7; j++){
                Calendar c = Calendar.getInstance();
                DayButton[][] intitalize = SerializationMachine.deserialize(Integer.toString(c.get(Calendar.YEAR)), "January");
                DayButton dateButton = intitalize[i - 9][j];
                dateButton.setPrefWidth(100);
                dateButton.setPrefHeight(100);
                gp.add(dateButton, j, i); //NODE ADDITION IS IN COLUMN-MAJOR
                mutatorsReference[i - 9][j] = dateButton;
            }
        }
    }

    public void constructStructure() {
        Calendar c = Calendar.getInstance();

        //Figure out what year it is.
        year = c.get(Calendar.YEAR);
        dayOfYear = c.get(Calendar.DAY_OF_YEAR);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //System.out.println(c.get(Calendar.DAY_OF_YEAR));

        Path p = Paths.get("src/main/resources/ugds/theoriginalscizocalendar/yearStorage/" + year + ".txt");

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

                        DayButton[][] organizer = new DayButton[5][6];
                        int currRow = 0;
                        int currCol = dayOfWeek - 1;

                        for(int j = 1; j < 32; j++){
                            //Create objects
                            DayButton dayToAdd = new DayButton(months.get(currMonth) + " " + j + " " + year);
                            dayToAdd.assignDayOfWeek(dayOfWeek);
                            dayToAdd.assignRowPosition(currRow);
                            dayToAdd.assignColumnPosition(currCol);

                            currCol++;
                            if(currCol >= 7){
                                currRow++;
                                currCol = 0;
                            }

                            SerializationMachine.serialize(dayToAdd);
                            dayOfWeek++;
                            System.out.println(dayToAdd.toString());



                            if(dayOfWeek >= 8){
                                dayOfWeek = 1;
                            }

                        }
                    } else if(months.get(currMonth).equals("April") || months.get(currMonth).equals("June") || months.get(currMonth).equals("September") || months.get(currMonth).equals("November")){

                        DayButton[][] organizer = new DayButton[5][6];
                        int currRow = 0;
                        int currCol = dayOfWeek - 1;

                        for(int k = 1; k < 31; k++){
                            //Create objects
                            DayButton dayToAdd = new DayButton(months.get(currMonth) + " " + k + " " + year);
                            dayToAdd.assignDayOfWeek(dayOfWeek);
                            dayToAdd.assignRowPosition(currRow);
                            dayToAdd.assignColumnPosition(currCol);

                            currCol++;
                            if(currCol >= 7){
                                currRow++;
                                currCol = 0;
                            }

                            SerializationMachine.serialize(dayToAdd);
                            dayOfWeek++;
                            System.out.println(dayToAdd.toString());

                            if(dayOfWeek >= 8){
                                dayOfWeek = 1;
                            }
                        }
                    } else if (months.get(currMonth).equals("February") && year % 4 == 0){

                        DayButton[][] organizer = new DayButton[5][6];
                        int currRow = 0;
                        int currCol = dayOfWeek - 1;

                        for(int l = 1; l < 30; l ++){
                            //Create objects
                            DayButton dayToAdd = new DayButton(months.get(currMonth) + " " + l + " " + year);
                            dayToAdd.assignDayOfWeek(dayOfWeek);
                            dayToAdd.assignRowPosition(currRow);
                            dayToAdd.assignColumnPosition(currCol);

                            currCol++;
                            if(currCol >= 7){
                                currRow++;
                                currCol = 0;
                            }

                            SerializationMachine.serialize(dayToAdd);
                            dayOfWeek++;
                            System.out.println(dayToAdd.toString());

                            if(dayOfWeek >= 8){
                                dayOfWeek = 1;
                            }
                        }
                    } else if (months.get(currMonth).equals("February")){

                        DayButton[][] organizer = new DayButton[5][6];
                        int currRow = 0;
                        int currCol = dayOfWeek - 1;

                        for(int n = 1; n < 29; n++){
                            //Create objects
                            DayButton dayToAdd = new DayButton(months.get(currMonth) + " " + n + " " + year);
                            dayToAdd.assignDayOfWeek(dayOfWeek);
                            dayToAdd.assignRowPosition(currRow);
                            dayToAdd.assignColumnPosition(currCol);

                            currCol++;
                            if(currCol >= 7){
                                currRow++;
                                currCol = 0;
                            }

                            SerializationMachine.serialize(dayToAdd);
                            dayOfWeek++;
                            System.out.println(dayToAdd.toString());

                            if(dayOfWeek >= 8){
                                dayOfWeek = 1;
                            }
                        }

                    }
                    currMonth++;
                }


            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            year--;

        } else {
            System.out.println("Found");
        }
    }

    //<OVERLOAD LOCATION>


    // Plans to create .txt generator here.


    // </OVERLOAD LOCATION>

    public static void main(String[] args) {
        launch();
    }
}
