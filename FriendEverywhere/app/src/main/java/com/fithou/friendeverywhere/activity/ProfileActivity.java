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
import com.fithou.friendeverywhere.object.UserObject;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_aboutme, et_birthday, et_fullname, et_email;
    private ImageView img_avatar;
    private ImageView img_capture;
    private Menu currentMenu;
    private boolean editing = false;
    private UserObject userObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        inflateView();
        initData();
        reloadView();
    }

    private void reloadView() {
        // TODO: fill data user
        enableAll();
    }

    protected void initData() {
        userObject = new UserObject();
        // TODO: get user from references
    }

    protected void inflateView() {
        et_aboutme = (EditText) findViewById(R.id.et_profileGioithieu);
        et_birthday = (EditText) findViewById(R.id.et_profileNgaysinh);
        et_fullname = (EditText) findViewById(R.id.et_profileTen);
        et_email = (EditText) findViewById(R.id.et_profileEmail);
        img_avatar = (ImageView) findViewById(R.id.iv_profileactivity);
        img_capture = (ImageView) findViewById(R.id.img_capture_profile);

        img_capture.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_capture_profile:
                break;
            default:
                break;
        }
    }

    protected void enableAll() {
        et_fullname.setEnabled(editing);
        et_birthday.setEnabled(editing);
        et_email.setEnabled(editing);
        et_aboutme.setEnabled(editing);
        img_capture.setVisibility(editing ? View.VISIBLE : View.GONE);
        if (currentMenu != null) {
            currentMenu.getItem(0).setVisible(!editing);
            currentMenu.getItem(1).setVisible(editing);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        currentMenu = menu;
        currentMenu.getItem(0).setVisible(!editing);
        currentMenu.getItem(1).setVisible(editing);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_profile_done:
                editing = !editing;
                enableAll();
                break;
            case R.id.action_profile_edit:
                editing = !editing;
                enableAll();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
