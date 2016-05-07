package com.fithou.friendeverywhere.asynctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.LoadingDialog;
import com.fithou.friendeverywhere.ultis.NetworkSupport;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateProfileAsyncTask extends AsyncTask<String, Void, JSONObject> {

    private Callback callback;
    private Context context;

    public UpdateProfileAsyncTask setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public UpdateProfileAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        if (callback != null) callback.onPreExecute();
        LoadingDialog.getDialog(context, LoadingDialog.ProgressStyle.NORMAL, true).show();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject userJsonObject = new JSONObject();
        List<NameValuePair> listParams = new ArrayList<>();
        listParams.add(new BasicNameValuePair("user_id", params[0]));
        listParams.add(new BasicNameValuePair("fullname", params[1]));
        listParams.add(new BasicNameValuePair("birthday", params[2]));
        listParams.add(new BasicNameValuePair("email", params[3]));
        listParams.add(new BasicNameValuePair("photo", params[4]));
        listParams.add(new BasicNameValuePair("about_me", params[5]));
        try {
            String result = NetworkSupport.sendRequest(Constants.URL_UPDATE_PROFILE, listParams, NetworkSupport.RequestMethod.POST, 50000);
            JSONObject jsonObject = new JSONObject(result);
            if (jsonObject.getInt(context.getString(R.string.json_key_code)) == 1) {
                final String message = jsonObject.getString(context.getString(R.string.json_key_message));
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NetworkSupport.showMessage((Activity) context, message);
                    }
                });
            } else {
                userJsonObject = jsonObject.getJSONObject(context.getString(R.string.json_key_results));
                return userJsonObject;
            }
        } catch (IOException e) {
            NetworkSupport.showDialogError((Activity) context, e);
            return null;
        } catch (final JSONException e) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NetworkSupport.showMessage((Activity) context, e.getMessage());
                }
            });
            return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject userJsonObject) {
        super.onPostExecute(userJsonObject);
        LoadingDialog.getDialog(context).dismiss();
        if (userJsonObject == null) return;
        if (callback != null) callback.onPostExecute(userJsonObject);
    }

}
