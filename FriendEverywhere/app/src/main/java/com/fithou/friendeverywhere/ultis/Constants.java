package com.fithou.friendeverywhere.ultis;

public class Constants {
    public static final String BASE_URL = "http://192.168.2.247:8080/fe/";
    public static final String URL_CHECK_PHONE = BASE_URL + "check_phone.php";
    /*
     *  Datetime format
     */
    public static final String DATE_FORMAT_SERVER = "dd/MM/yyyy hh:mm:ss";
    public static final String DATE_FORMAT_BIRTHDAY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DATE = "dd MMM yyyy";
    public static final String DATE_FORMAT_TIME = "hh:mm";

    /*
     *  Key share preferences
     */

    /*
     * Key Friend status
     */
    public static final int FRIEND_STATUS_NONE = 0;
    public static final int FRIEND_STATUS_REQUESTED = 1;
    public static final int FRIEND_STATUS_ACCEPTED = 2;

    /*
     * Key Message error
     */
    public static final int MESSAGE_ERROR_NO = 1;
    public static final int MESSAGE_ERROR_YES = 2;

    /*
     * Key Message seen
     */
    public static final int MESSAGE_SEEN = 1;
    public static final int MESSAGE_NEW = 2;

    /*
     * Key Attachment
     */
    public static final int ATTACHMENT_YES = 1;
    public static final int ATTACHMENT_NO = 2;

    /*
     * Key Attachment type
     */
    public static final int ATTACHMENT_TYPE_IMAGE = 1;
    public static final int ATTACHMENT_TYPE_FILE = 2;
}
