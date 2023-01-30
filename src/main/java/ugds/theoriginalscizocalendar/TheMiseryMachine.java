package ugds.theoriginalscizocalendar;

public class TheMiseryMachine extends Thread{
    public HelloApplication hellApp;

    public TheMiseryMachine(HelloApplication hellAppIn){
        hellApp = hellAppIn;
    }

    @Override
    public void run() {
        while (this != null) {
            try {

                sleep(1000);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
