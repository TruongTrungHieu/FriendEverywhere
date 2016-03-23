package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fithou.friendeverywhere.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (checkFisrtUsing()) {
            Intent main = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(main);
            finish();
        } else {
            Intent register = new Intent(SplashActivity.this, RegisterActivity.class);
            startActivity(register);
            finish();
        }

    }

    private boolean checkFisrtUsing() {
        return false;
    }
}
