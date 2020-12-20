package it.unisa.c03.myPersonalTrainer;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ParametersController {

    public static void main(String[] args) {


        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        String x = (df.format(date));


        Parameters p = new Parameters(50, 25, 49.9f, "marcosica@io.it", date);
        ParametersService service = new ParametersService();
        /*try {
            if (service.acceptableParameters(50, 20, 30, "marcosica@io.it", date)) System.out.println("tutto bene");
        } catch (IOException e) {
            e.getMessage();
        }*/

        System.out.println(p.toString());


    }
}
