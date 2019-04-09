package com.baulin.alexander.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String convertUnixToHour(int unites) {
        Date date = new Date(unites * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
}
