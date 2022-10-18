package ugds.theoriginalscizocalendar;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;

public class DayButton extends Button {

    String name;
    int dayOfWeek; //1 - 7
    public DayButton(String s){
        super(s);
        name = s;
    }
    public String getName(){return name;}
    public void assignDayOfWeek(int weekDay){dayOfWeek = weekDay;}

    @Override
    public String toString(){
        return name + " " + dayOfWeek;
    }
}
