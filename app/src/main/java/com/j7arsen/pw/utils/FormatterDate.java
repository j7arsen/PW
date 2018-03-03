package com.j7arsen.pw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by arsen on 03.03.2018.
 */

public class FormatterDate {

    public static String formatServerDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yy, H:mm:SS a", Locale.US);
            SimpleDateFormat resultFormatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date serverDate = dateFormat.parse(date);
            return resultFormatDate.format(serverDate);
        } catch (ParseException e) {
            return date;
        }
    }

}
