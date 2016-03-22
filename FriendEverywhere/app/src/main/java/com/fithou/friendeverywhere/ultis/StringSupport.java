package com.fithou.friendeverywhere.ultis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSupport {

    public static String phoneRegex = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
            "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
            "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$";
    public static final Pattern emailRegex =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isNullOrEmpty(String value) {
        if (value == null || value.toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkFormatPhoneNumber(String value) {
        return value.matches(phoneRegex);
    }

    public static boolean checkFormatEmail(String value) {
        Matcher matcher = emailRegex.matcher(value);
        return matcher.find();
    }

}
