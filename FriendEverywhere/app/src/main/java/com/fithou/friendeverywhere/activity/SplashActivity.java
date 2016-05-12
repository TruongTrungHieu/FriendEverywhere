package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkFisrtUsing();

    }

    private void checkFisrtUsing() {
        String user_id = Constants.getPreference(getApplicationContext(), Constants.XML_USER_ID);
        String create_password = Constants.getPreference(getApplicationContext(), Constants.XML_CREATE_PASSWORD);
        if (StringSupport.isNullOrEmpty(user_id)) {
            Intent register = new Intent(SplashActivity.this, RegisterActivity.class);
            startActivity(register);
            finish();
        } else {
            if (StringSupport.isNullOrEmpty(create_password) || create_password.equals("0")) {
                Intent create_pass = new Intent(SplashActivity.this, CreatePasswordActivity.class);
                startActivity(create_pass);
                finish();
            } else {
                Intent main = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        }
    }
}
