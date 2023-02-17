package ugds.theoriginalscizocalendar;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TheMiseryMachine extends Thread{
    public HelloApplication hellApp;
    public AtomicBoolean running = new AtomicBoolean(false);
    public TheMiseryMachine(HelloApplication hellAppIn){
        hellApp = hellAppIn;
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                sleep(3000);
                System.out.println(hellApp.getRandomButton());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            int targetWeirdness = (int)(Math.random() * 2) + 1;
//            switch (targetWeirdness) {
//                case 1:seekAndFireAbnormality(); break;
//                case 2:System.out.println("Safe...for now"); break;
//            }
        }
    }



    public void seekAndFireAbnormality() {
        //Find a valid DB object and cause it to stand out visually.
        ArrayList<DayButton> reference = hellApp.getCurrGP();
        DayButton target = null;
        int indexOfOperableDB = (int)(Math.random() * reference.size() - 1);


        while(target == null || target.getNumericalDate() == 0) { //Find new
            if (reference.get(indexOfOperableDB) != null) {
                target = reference.get(indexOfOperableDB);
            } else {
                indexOfOperableDB = (int)(Math.random() * reference.size() - 1);
            }
        }

        System.out.println(target);

        TheSpazMachine spaz = new TheSpazMachine(hellApp, target);
        spaz.start();

        System.out.println("Fired");
    }

}
