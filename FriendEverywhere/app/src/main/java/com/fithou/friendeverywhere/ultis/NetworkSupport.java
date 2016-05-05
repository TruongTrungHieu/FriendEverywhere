package com.fithou.friendeverywhere.ultis;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.fithou.friendeverywhere.R;

public class NetworkSupport {

    public static final int TIMEOUT_DEFAULT = 30000;

    public enum RequestMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    public static boolean hasNetWork(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static String sendRequest(String url) throws IOException {
        return sendRequest(url, null);
    }

    public static String sendRequest(String url, List<NameValuePair> params) throws IOException {
        return sendRequest(url, params, RequestMethod.GET, TIMEOUT_DEFAULT);
    }

    public static String sendRequest(String url,
                                     List<NameValuePair> params, RequestMethod method, int timeOut) throws IOException {
        if (TextUtils.isEmpty(url)) throw new IllegalArgumentException("URL not null");
        if (timeOut <= 0)
            timeOut = TIMEOUT_DEFAULT;
        HttpClient httpClient = new DefaultHttpClient();
        HttpParams paramsHTTP = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(paramsHTTP, timeOut);
        HttpConnectionParams.setSoTimeout(paramsHTTP, timeOut);
        String formatParams = "";
        if (params != null) {
            formatParams = URLEncodedUtils.format(params, HTTP.UTF_8);
        }

        HttpUriRequest reqMethod = null;
        switch (method) {
            case GET:
                reqMethod = new HttpGet(url + formatParams);
                break;
            case POST:
                reqMethod = new HttpPost(url);
                ((HttpPost) reqMethod).setEntity(new UrlEncodedFormEntity(params));
                break;
            case PUT:
                reqMethod = new HttpPut(url);
                ((HttpPut) reqMethod).setEntity(new UrlEncodedFormEntity(params));
                break;
            case DELETE:
                reqMethod = new HttpDelete(url + formatParams);
                break;
        }
        Log.i(Constants.LOG_TAG, reqMethod.getURI().toString());
        HttpResponse resp = httpClient.execute(reqMethod);
        if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        } else {
            Log.e(Constants.LOG_TAG, resp.getStatusLine().toString());
        }
        return null;
    }

    public static void showDialogError(final Activity activity, final IOException e) {
        showDialogError(activity, e, null);
    }

    public static void showDialogError(final Activity activity, final IOException e, final View.OnClickListener action) {
        if (activity.isFinishing())
            return;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Resources res = activity.getResources();
                String msg;
                if (!NetworkSupport.hasNetWork(activity)) {
                    msg = res.getString(R.string.error_internet_lost);
                } else if (e instanceof UnknownHostException) {
                    msg = res.getString(R.string.error_unknown_host);
                } else if (e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException) {
                    msg = res.getString(R.string.error_internet_timeout);
                } else if (e instanceof IOException) {
                    msg = res.getString(R.string.error_io_exception);
                } else {
                    msg = res.getString(R.string.error_network_general);
                }
                VLIDialog.show(activity, msg, null, action);
            }
        });
    }

    public static void showMessage(final Activity activity, final String message) {
        if (activity.isFinishing())
            return;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                VLIDialog.show(activity, message);
            }
        });
    }
}
