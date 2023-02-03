package ugds.theoriginalscizocalendar;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class TheMiseryMachine extends Thread{
    public HelloApplication hellApp;

    public TheMiseryMachine(HelloApplication hellAppIn){
        hellApp = hellAppIn;
    }

    @Override
    public void run() {
        while (this != null) {
            try {

                sleep(3000);
                seekAndFireAbnormality();


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void seekAndFireAbnormality() {
        //6, 14 COLMAJ
        hellApp.getNodeByRowColumnIndex(5, 5); //.setStyle("-fx-fill: black")
    }

}
