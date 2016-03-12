package com.fithou.friendeverywhere.ultis;

import android.nfc.FormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatetimeSupport {

    public static Date convertStringToDate(String dateString, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(Date date, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
