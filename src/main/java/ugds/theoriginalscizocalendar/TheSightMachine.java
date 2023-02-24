package ugds.theoriginalscizocalendar;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

public class TheSightMachine extends Thread{
    public AtomicBoolean running = new AtomicBoolean(false);
    public Path p;

    public TheSightMachine(){

    }
    public void givePath(Path imagePath){
        p = imagePath;
    }
    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            ImageFlashPane ifp = new ImageFlashPane(p);
            ifp.show();
            try {
                sleep(1000);
            } catch (InterruptedException e) {throw new RuntimeException(e);}
            ifp.closeThis();
            running.set(false);
        }
    }
}
