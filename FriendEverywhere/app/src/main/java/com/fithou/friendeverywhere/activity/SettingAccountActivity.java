package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.DeleteAccountAsyncTask;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

public class SettingAccountActivity extends AppCompatActivity {

    private TextView tv_change_pass, tv_delete_acc;
    private Switch sw_share_pos;
    private String share_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        share_location = Constants.getPreference(this, Constants.SHARE_LOCATION);

        tv_change_pass = (TextView)findViewById(R.id.tv_change_pass);
        tv_delete_acc = (TextView)findViewById(R.id.tv_delete_account);
        sw_share_pos = (Switch)findViewById(R.id.sw_share_location);

        tv_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change_pass = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(change_pass);
            }
        });

        tv_delete_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAccountAsyncTask(getApplicationContext()).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        final JSONObject data = (JSONObject) o;
                        if (o != null) {
                            try {
                                Constants.savePreference(getApplicationContext(), Constants.XML_USER_ID, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_BIRTHDAY, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_EMAIL, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_PHONE, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_LATITUDE, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_LONGTITUDE, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_ONLINE_STATUS, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_PHOTO, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_ABOUT_ME, "");
                                Constants.savePreference(getApplicationContext(), Constants.XML_GCM_ID, "");
                                finish();
                                Intent del = new Intent(getApplicationContext(), RegisterActivity.class);
                                startActivity(del);
                            } catch (Exception e) {
                                Log.d("login service", e.getMessage());
                            }
                        }
                    }
                }).execute(Constants.XML_USER_ID);
            }
        });

        if (share_location.equals(Constants.SHARE_LOCATION_ALLOW) || StringSupport.isNullOrEmpty(share_location)){
            sw_share_pos.setChecked(true);
        }

        sw_share_pos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Constants.savePreference(getApplicationContext(), Constants.SHARE_LOCATION, Constants.SHARE_LOCATION_ALLOW);
                } else {
                    Constants.savePreference(getApplicationContext(), Constants.SHARE_LOCATION, Constants.SHARE_LOCATION_DENIED);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
