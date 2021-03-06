package com.fithou.friendeverywhere.asynctask;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.GroupObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.LoadingDialog;
import com.fithou.friendeverywhere.ultis.NetworkSupport;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetListGroupAsyncTask extends AsyncTask<String, Void, ArrayList<GroupObject>> {

    private Callback<ArrayList<GroupObject>> callback;
    private Context context;

    public GetListGroupAsyncTask setCallback(Callback callback) {
        this.callback = callback;
        return this;
    }

    public GetListGroupAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        if (callback != null) callback.onPreExecute();
        LoadingDialog.getDialog(context, LoadingDialog.ProgressStyle.NORMAL, true).show();

    }

    @Override
    protected ArrayList<GroupObject> doInBackground(String... params) {
        ArrayList<GroupObject> list = new ArrayList<>();
        List<NameValuePair> listParams = new ArrayList<>();
        listParams.add(new BasicNameValuePair("user_id", params[0]));
        try {
            String result = NetworkSupport.sendRequest(Constants.URL_GET_LIST_GROUP, listParams, NetworkSupport.RequestMethod.POST, 50000);
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
                JSONArray jsonArray = jsonObject.getJSONArray(context.getString(R.string.json_key_results));
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject j = jsonArray.getJSONObject(i);
                    list.add(GroupObject.parseJsonToObject(j));
                }
                return list;
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
    protected void onPostExecute(ArrayList<GroupObject> list) {
        LoadingDialog.getDialog(context).dismiss();
        if (list == null) return;
        if (callback != null) {
            callback.onPostExecute(list);
        }
    }


}
