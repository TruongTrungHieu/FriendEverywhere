package com.fithou.friendeverywhere.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.CheckphoneAsyncTask;
import com.fithou.friendeverywhere.asynctask.RegisterAsyncTask;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class ConfirmPinCodeActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_pincode;
    private Button btn_confirm_code;
    private String phoneNumber;
    private int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pin_code);

        phoneNumber = getIntent().getStringExtra("PHONENUMBER");
        generate();

        inflateView();
        showNotification();
    }

    public void generate() {
        Random r = new Random();
        int Low = 999;
        int High = 9999;
        code = r.nextInt(High-Low) + Low;
    }

    private void inflateView() {
        btn_confirm_code = (Button)findViewById(R.id.btnGuiMa_pin_code);
        et_pincode = (EditText)findViewById(R.id.edtSdt_pin_code);
        btn_confirm_code.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnGuiMa_pin_code:
                if (!StringSupport.isNullOrEmpty(et_pincode.getText().toString())) {
                    if (Integer.parseInt(et_pincode.getText().toString().trim()) == code) {
                        new RegisterAsyncTask(this).setCallback(new Callback() {
                            @Override
                            public void onPreExecute() {

                            }

                            @Override
                            public void onPostExecute(Object o) {
                                final JSONObject data = (JSONObject) o;
                                if (o != null) {
                                    try {
                                        String user_id = data.getString("user_id");
                                        Constants.savePreference(getApplicationContext(), Constants.XML_USER_ID, user_id);
                                        finish();
                                        Intent main = new Intent(ConfirmPinCodeActivity.this, MainActivity.class);
                                        startActivity(main);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).execute(phoneNumber);

                    }
                    else {
                        Toast.makeText(this, "Mã xác nhận không đúng!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Chưa nhập mã xác nhận!", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    public void showNotification(){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getSystemService(ns);

        int icon = R.drawable.splash;
        long when = System.currentTimeMillis();
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(this, ConfirmPinCodeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification;
        notification = new Notification.Builder(context)
                .setContentTitle("Mã xác nhận")
                .setContentText(code +
                        " Bạn đã đăng ký thành công! ").setSmallIcon(icon)
                .setContentIntent(contentIntent).setWhen(when).setAutoCancel(true)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notificationManager.notify(5, notification);
    }

}
