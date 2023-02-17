package ugds.theoriginalscizocalendar;

import java.util.concurrent.atomic.AtomicBoolean;

public class TheSpazMachine extends Thread{
    public HelloApplication hellfireApp;
    public DayButton[][] ref;
    public DayButton target;
    public boolean state = false;
    public AtomicBoolean running = new AtomicBoolean(false);
    public int count = 0;

    public TheSpazMachine(HelloApplication in, DayButton targetInit){
        hellfireApp = in;
        target = targetInit;
    }
    @Override
    public void run(){
        running.set(true);
        while(running.get()) {
            try {
                sleep(100);

            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            if (state) {
                target.setStyle("-fx-background-color: black");
                state = false;
                count++;
            } else {
                target.setStyle("-fx-background-color: white");
                state = true;
                count++;
            }
            if (count > 12) {
                running.set(false);
            }
        }
    }
}
