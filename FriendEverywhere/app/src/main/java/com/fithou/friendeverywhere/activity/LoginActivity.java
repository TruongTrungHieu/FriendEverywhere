package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.LoginAsyncTask;
import com.fithou.friendeverywhere.object.CountryObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnRegister, tv_country;
    private EditText et_sdt, et_pass;
    private CountryObject countryObject;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegister = (TextView) findViewById(R.id.tvNewAcLogin);
        et_pass = (EditText)findViewById(R.id.edtPasslogin);
        et_sdt = (EditText)findViewById(R.id.edtSdt_login);
        tv_country = (TextView)findViewById(R.id.tvMaQuocGia_login);
        btn_login = (Button)findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(this);
        tv_country.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        initData();
        reloadData();
    }

    private void initData() {
        countryObject = new CountryObject("Vietnam", "+84");
    }

    private void reloadData() {
        if (countryObject != null) {
            tv_country.setText(countryObject.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        countryObject = (CountryObject) getIntent().getSerializableExtra("COUNTRY");
        reloadData();
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tvNewAcLogin:
                finish();
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.btn_login:
                finish();
                if (!StringSupport.isNullOrEmpty(et_sdt.getText().toString()) && !StringSupport.isNullOrEmpty(et_pass.getText().toString())) {
                        new LoginAsyncTask(this).setCallback(new Callback() {
                            @Override
                            public void onPreExecute() {

                            }

                            @Override
                            public void onPostExecute(Object o) {
                                final JSONObject data = (JSONObject) o;
                                if (o != null) {
                                    try {
                                        String user_id = data.getString("user_id");
                                        String fullname = data.getString("fullname");
                                        String birthday = data.getString("birthday");
                                        String email = data.getString("email");
                                        String phone = data.getString("phone");
                                        String latitude = data.getString("latitude");
                                        String longtitude = data.getString("longtitude");
                                        String online_status = data.getString("online_status");
                                        String photo = data.getString("photo");
                                        String about_me = data.getString("about_me");
                                        String gcm_id = data.getString("gcm_id");

                                        Constants.savePreference(getApplicationContext(), Constants.XML_USER_ID, user_id);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_FULL_NAME, fullname);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_BIRTHDAY, birthday);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_EMAIL, email);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_PHONE, phone);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_LATITUDE, latitude);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_LONGTITUDE, longtitude);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_ONLINE_STATUS, online_status);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_PHOTO, photo);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_ABOUT_ME, about_me);
                                        Constants.savePreference(getApplicationContext(), Constants.XML_GCM_ID, gcm_id);

                                        finish();
                                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(main);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).execute(et_sdt.getText().toString(),et_pass.getText().toString());

                } else {
                    Toast.makeText(this, "Chưa nhập mã xác nhận!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tvMaQuocGia_login:
                Intent choose_country = new Intent(LoginActivity.this, CountryListActivity.class);
                choose_country.putExtra("REGISTER", false);
                startActivity(choose_country);
                break;
            default:
                break;
        }
    }
}

