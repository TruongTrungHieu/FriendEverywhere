package com.fithou.friendeverywhere.ultis;

public interface Callback<Result> {
    void onPreExecute();
    void onPostExecute(Result result);
}
