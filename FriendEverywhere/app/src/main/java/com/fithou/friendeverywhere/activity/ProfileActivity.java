package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fithou.friendeverywhere.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_aboutme, et_birthday, et_fullname, et_email;
    private ImageView img_avatar;
    private Button btn_Luu, btn_Sua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        inflateView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    protected void inflateView(){
        et_aboutme = (EditText)findViewById(R.id.et_profileGioithieu);
        et_birthday = (EditText)findViewById(R.id.et_profileNgaysinh);
        et_fullname = (EditText)findViewById(R.id.et_profileTen);
        et_email = (EditText)findViewById(R.id.et_profileEmail);

        img_avatar = (ImageView)findViewById(R.id.iv_profileactivity);

        btn_Luu = (Button)findViewById(R.id.btn_profileLuu);
        btn_Sua = (Button)findViewById(R.id.btn_profileSua);

        btn_Luu.setOnClickListener(this);
        btn_Sua.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_profileLuu:
                enableAll(false);
                break;
            case R.id.btn_profileSua:
                enableAll(true);
                break;
            default:
                break;
        }
    }

    protected void enableAll(boolean editing) {
        et_fullname.setEnabled(editing);
        et_birthday.setEnabled(editing);
        et_email.setEnabled(editing);
        et_aboutme.setEnabled(editing);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
