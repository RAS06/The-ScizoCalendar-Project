package ugds.theoriginalscizocalendar;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Button;

public class DayButton extends Button {

    String name;
    String month;
    int numericalDate;
    int dayOfWeek; //1 - 7
    int rowPosition;
    int columnPosition;
    int year;

    public DayButton(String s){
        super(s);
        name = s;
    }
    public DayButton(String s, int d, int r, int c, int date, String initMonth, int y){
        super(Integer.toString(date));
        name = s;
        dayOfWeek = d;
        rowPosition = r;
        columnPosition = c;
        numericalDate = date;
        month = initMonth;
        year = y;
    }

    public DayButton(int d, int r, int c, int date, String initMonth, int y){
        super(Integer.toString(date));
        name = Integer.toString(date);
        dayOfWeek = d;
        rowPosition = r;
        columnPosition = c;
        numericalDate = date;
        month = initMonth;
        year = y;
    }

    public String getName(){return name;}
    public int getWeekDay(){return dayOfWeek;}
    public int getRowPosition(){return rowPosition;}
    public int getColumnPosition(){return columnPosition;}

    public void assignDayOfWeek(int weekDay){dayOfWeek = weekDay;}
    public void assignRowPosition(int pos){rowPosition = pos;}
    public void assignColumnPosition(int pos){columnPosition = pos;}

    @Override
    public String toString(){
        return name + " " + dayOfWeek + " [" + rowPosition + " " + columnPosition + "]";
    }
}
