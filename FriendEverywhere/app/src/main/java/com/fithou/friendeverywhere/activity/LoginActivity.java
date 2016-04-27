package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.fithou.friendeverywhere.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegister = (TextView) findViewById(R.id.tvNewAcLogin);

        btnRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tvNewAcLogin:
                finish();
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                break;
            default:
                break;
        }
    }
}

