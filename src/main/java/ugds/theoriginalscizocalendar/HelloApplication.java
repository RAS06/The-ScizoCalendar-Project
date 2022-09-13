package ugds.theoriginalscizocalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;

import java.io.IOException;


public class HelloApplication extends Application {

    public static int year;
    public static int dayOfWeek;
    public static int firstDayOfWeek;
    public static int dayOfYear;
    public static ArrayList<String> week = new ArrayList<String>(Arrays.asList("List doesn't start at zero for some reason", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"));

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 240);
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
        System.out.println(c.get(Calendar.DAY_OF_YEAR));

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
                FileWriter fw = new FileWriter("src/main/resources/ugds/theoriginalscizocalendar/yearStorage" + year + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("String Literal. THERE IS NO MOLE. THERE IS NO MOLE.");
                bw.close();
            } catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
