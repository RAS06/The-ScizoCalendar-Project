package ugds.theoriginalscizocalendar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

public class TheSightMachine extends Thread{
    public AtomicBoolean running = new AtomicBoolean(false);
    public ImageFlashPane ifp;
    public Path p;
    public HelloApplication hellApp;
    public int side;
    public int times = 0;
    public double locationModifier;

    public TheSightMachine(Path imagePath, HelloApplication hellIn){
        p = imagePath;
        hellApp = hellIn;
    }

    @Override
    public void run() {
        running.set(true);

        while (running.get()) {

            try {
                System.out.println("Sight");
                sleep(1000);
                //selectImageSide
                side = (int)(Math.random() * 2) + 1;
                System.out.println(side);
                InputStream stream = new FileInputStream(p.toString());

                if(side == 1) locationModifier = 0.25; else locationModifier = 0.75;
                Image i = new Image(stream);
                ImageView iv = new ImageView();
                iv.setImage(i);
                hellApp.aPain.getChildren().add(iv);


                times++;
               // if(times > 12){
                    times = 0;
                    running.set(false);
                //}

            } catch (InterruptedException e) {throw new RuntimeException(e);} catch (FileNotFoundException e) {throw new RuntimeException(e);}


        }
    }
}
