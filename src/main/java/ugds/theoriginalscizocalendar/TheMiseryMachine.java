package ugds.theoriginalscizocalendar;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int targetWeirdness = (int)(Math.random() * 3) + 1;
            switch (targetWeirdness) {
                case 1:seekAndFireAbnormality(); break;
                case 2:System.out.println("Safe...for now"); break;
                case 3:flashImage(); break;
            }
        }
    }




    public void seekAndFireAbnormality() {
        //Find a valid DB object and cause it to stand out visually.
//        ArrayList<DayButton> reference = hellApp.getCurrGP();
//        DayButton target = null;
//        int indexOfOperableDB = (int)(Math.random() * reference.size() - 1);
//
//
//        while(target == null || target.getNumericalDate() == 0) { //Find new
//            if (reference.get(indexOfOperableDB) != null) {
//                target = reference.get(indexOfOperableDB);
//            } else {
//                indexOfOperableDB = (int)(Math.random() * reference.size() - 1);
//            }
//        }
//

        DayButton target = hellApp.getRandomButton();
        System.out.println(target);
        TheSpazMachine spaz = new TheSpazMachine(hellApp, target);
        spaz.start();

        String findStorySnippet = findTextIn();


        target.setOnAction(e -> {
            PsudeoAppointmentViewer PAV = new PsudeoAppointmentViewer(findStorySnippet, target, hellApp);
            PAV.show();
        });

        System.out.println("Fired");
    }

    public String findTextIn() {
        File directory = new File("src/main/resources/ugds/theoriginalscizocalendar/psudoAppointmentStorage");
        int max = directory.list().length;
        int storyID = (int)(Math.random() * max);
        String returnStory = "";
        String in = "";
        try {
            String nameOfFile = directory.list()[storyID];
            Path p = Paths.get("src/main/resources/ugds/theoriginalscizocalendar/psudoAppointmentStorage/" + nameOfFile);
            FileInputStream fis = new FileInputStream(p.toFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while(((in = br.readLine()) != null)){
                returnStory = returnStory + in + "\n";
            }
        }catch (FileNotFoundException fnfe){fnfe.printStackTrace();} catch (IOException e) {throw new RuntimeException(e);}


        return returnStory;
    }

    private void flashImage() {
        //Find pass image into thread.
        File directory = new File("src/main/resources/ugds/theoriginalscizocalendar/imageStorage");
        int max = directory.list().length;
        int storyID = (int)(Math.random() * max);
        String nameOfFile = directory.list()[storyID];
        Path p = Paths.get("src/main/resources/ugds/theoriginalscizocalendar/imageStorage/" + nameOfFile);
        System.out.println(p.toString());

        TheSightMachine tsm = new TheSightMachine(p, hellApp);
        tsm.start();

    }

}
