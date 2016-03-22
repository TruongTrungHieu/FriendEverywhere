package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fithou.friendeverywhere.R;

public class ConfirmPinCodeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_confirm_code;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pin_code);

        phoneNumber = getIntent().getStringExtra("PHONENUMBER");

        inflateView();
    }

    private void inflateView() {
        btn_confirm_code = (Button)findViewById(R.id.btnXacNhan);

        btn_confirm_code.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnXacNhan:
                finish();
                Intent main = new Intent(ConfirmPinCodeActivity.this, MainActivity.class);
                startActivity(main);

                break;
            default:
                break;
        }
    }

}
