package ugds.theoriginalscizocalendar;

import com.google.gson.Gson;


public class SerializationMachine {
    public SerializationMachine(){}
    public static String turnIntoJson(Object o){
        Gson gson = new Gson();
        String str = gson.toJson(o);

        return str;
    }
}
