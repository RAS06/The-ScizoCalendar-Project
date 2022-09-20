package ugds.theoriginalscizocalendar;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;

public class DayButton extends Button {

    DoubleProperty x, y;
    public DayButton(String s){
        super(s);

    }
}
