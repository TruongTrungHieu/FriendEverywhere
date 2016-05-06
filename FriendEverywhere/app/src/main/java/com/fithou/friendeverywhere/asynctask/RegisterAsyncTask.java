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
 * Created by admin on 06/05/2016.
 */
public class RegisterAsyncTask extends AsyncTask<String, Void, JSONObject> {

    private Callback callback;
    private Context context;

    public RegisterAsyncTask setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public RegisterAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        if (callback != null) callback.onPreExecute();
        LoadingDialog.getDialog(context, LoadingDialog.ProgressStyle.NORMAL, true).show();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject checkphoneObj = new JSONObject();
        List<NameValuePair> listParams = new ArrayList<>();
        listParams.add(new BasicNameValuePair("phone_number", params[0]));
        try {
            String result = NetworkSupport.sendRequest(Constants.URL_REGISTER, listParams, NetworkSupport.RequestMethod.POST, 50000);
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
                checkphoneObj = jsonObject.getJSONObject(context.getString(R.string.json_key_results));
                return checkphoneObj;
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
    protected void onPostExecute(JSONObject aNhanvienObject) {
        super.onPostExecute(aNhanvienObject);
        LoadingDialog.getDialog(context).dismiss();
        if (aNhanvienObject == null) return;
        if (callback != null) callback.onPostExecute(aNhanvienObject);
    }

}
