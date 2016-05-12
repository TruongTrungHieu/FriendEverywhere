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

/**
 * Created by admin on 11/05/2016.
 */
public class ChangePasswordAsyncTask extends AsyncTask<String, Void, JSONObject> {

    private Callback callback;
    private Context context;

    public ChangePasswordAsyncTask(Context context) {
        this.context = context;
    }

    public ChangePasswordAsyncTask setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    @Override
    protected void onPreExecute() {
        if (callback != null) callback.onPreExecute();
        LoadingDialog.getDialog(context, LoadingDialog.ProgressStyle.NORMAL, true).show();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject registerObj = new JSONObject();
        List<NameValuePair> listParams = new ArrayList<>();
        listParams.add(new BasicNameValuePair("user_id", params[0]));
        listParams.add(new BasicNameValuePair("old_pass", params[1]));
        listParams.add(new BasicNameValuePair("new_pass", params[2]));
        try {
            String result = NetworkSupport.sendRequest(Constants.URL_CHANGE_PASS, listParams, NetworkSupport.RequestMethod.POST, 50000);
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
                registerObj = jsonObject.getJSONObject(context.getString(R.string.json_key_results));
                return registerObj;
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
    protected void onPostExecute(JSONObject aUserObject) {
        super.onPostExecute(aUserObject);
        LoadingDialog.getDialog(context).dismiss();
        if (aUserObject == null) return;
        if (callback != null) callback.onPostExecute(aUserObject);
    }


}
