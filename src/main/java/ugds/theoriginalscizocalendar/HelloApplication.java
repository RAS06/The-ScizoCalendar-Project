package ugds.theoriginalscizocalendar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;
import java.sql.Time;
import java.util.Calendar;

import java.io.IOException;


public class HelloApplication extends Application {

    public static int year;
    public static int dayOfWeek;
    public static int firstDayOfWeek;
    public static int dayOfYear;

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
        System.out.println(c.get(Calendar.DAY_OF_YEAR));

    }

    public static void main(String[] args) {
        launch();
    }
}
