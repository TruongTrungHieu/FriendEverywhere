package com.fithou.friendeverywhere.ultis;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {

    public static final String BASE_URL = "http://www.friendverywhere.esy.es/api/";
    public static final String URL_CHECK_PHONE = BASE_URL + "check_phone.php";
    public static final String URL_CHANGE_PASS = BASE_URL + "change_password.php";
    public static final String URL_CONFIRM_FRIEND = BASE_URL + "confirm_friend.php";
    public static final String URL_CREATE_GROUP = BASE_URL + "create_group.php";
    public static final String URL_GET_LIST_FRIEND = BASE_URL + "get_list_friend.php";
    public static final String URL_GET_LIST_GROUP = BASE_URL + "get_list_group.php";
    public static final String URL_LOGIN = BASE_URL + "login.php";
    public static final String URL_REGISTER = BASE_URL + "register.php";
    public static final String URL_SEND_REQUEST_FRIEND = BASE_URL + "send_request_friend.php";
    public static final String URL_UPDATE_PROFILE = BASE_URL + "update_profile.php";

    public static final String LOG_TAG = "LOG_SYSTEM";

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
    public static final String XML_FILE_NAME = "FE_XML";
    public static final String XML_DEFAULT = "";

    public static final String XML_USER_ID = "user_id";

    public static void savePreference(Context mContext, String key, String value) {
        SharedPreferences mSharedPrefences = mContext.getSharedPreferences(
                XML_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPrefences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static String getPreference(Context mContext, String key) {
        SharedPreferences mSharedPrefences = mContext.getSharedPreferences(
                XML_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPrefences.getString(key, XML_DEFAULT);
    }

    public static boolean hasNetWork(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


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
