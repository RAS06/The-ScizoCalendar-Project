package ugds.theoriginalscizocalendar;

public class Appointment {
    public String id;
    public String info;

    public Appointment(String name, String data){
        id = name;
        info = data;
    }
    @Override
    public String toString(){
        return id + " " + info;
    }

    public String getInfo(){return info;}
}
