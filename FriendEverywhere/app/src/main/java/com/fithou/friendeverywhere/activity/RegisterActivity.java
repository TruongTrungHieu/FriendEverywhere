package com.fithou.friendeverywhere.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.CheckphoneAsyncTask;
import com.fithou.friendeverywhere.object.CountryObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_country;
    private EditText edt_phone;
    private Button btn_sendcode;
    private TextView tv_login;
    private CountryObject countryObject;
    private String phoneNumber;

    private static final int CHECK_PHONE_NOT_FOUND = 0;
    private static final int CHECK_PHONE_EXISTING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initData();
        inflateView();
        reloadData();
    }

    private void initData() {
        countryObject = new CountryObject("Vietnam", "+84");
    }

    private void inflateView() {
        tv_country = (TextView) findViewById(R.id.tvMaQuocGia_register);
        edt_phone = (EditText) findViewById(R.id.edtSdt_register);
        btn_sendcode = (Button) findViewById(R.id.btnGuiMa_register);
        tv_login = (TextView) findViewById(R.id.tvLogin_register);

        tv_country.setOnClickListener(this);
        btn_sendcode.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    private void reloadData() {
        if (countryObject != null) {
            tv_country.setText(countryObject.toString());
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tvMaQuocGia_register:
                Intent choose_country = new Intent(RegisterActivity.this, CountryListActivity.class);
                choose_country.putExtra("REGISTER", true);
                startActivity(choose_country);
                break;
            case R.id.btnGuiMa_register:
                if (checkValidatePhoneNumber()) {

                    new CheckphoneAsyncTask(this).setCallback(new Callback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onPostExecute(Object o) {
                            final JSONObject data = (JSONObject) o;
                            if (o != null) {
                                try {
                                    int check = data.getInt("check_phone");
                                    if (check == CHECK_PHONE_EXISTING) {
                                        finish();
                                        Intent pin_code = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(pin_code);
                                    } else {
                                        finish();
                                        Intent pin_code = new Intent(RegisterActivity.this, ConfirmPinCodeActivity.class);
                                        pin_code.putExtra("PHONENUMBER", phoneNumber);
                                        startActivity(pin_code);
                                    }
                                } catch (Exception e) {
                                    e.getMessage();
                                    return;
                                }
                            }
                        }
                    }).execute(phoneNumber);

                }
                break;
            case R.id.tvLogin_register:
                finish();
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        countryObject = (CountryObject) getIntent().getSerializableExtra("COUNTRY");
        reloadData();
    }

    private boolean checkValidatePhoneNumber() {
        if (StringSupport.isNullOrEmpty(edt_phone.getText().toString())) {
            showWarningDialog(getString(R.string.register_phone_empty));
            return false;
        } else {
            String phone = edt_phone.getText().toString();
            if (phone.charAt(0) == '0') {
                StringBuilder builder = new StringBuilder(phone);
                builder.deleteCharAt(0);
                phone = builder.toString();
            }
            if (countryObject == null) {
                phoneNumber = "+84" + phone;
            } else {
                phoneNumber = countryObject.getDial_code() + phone;
            }
            if (StringSupport.checkFormatPhoneNumber(phoneNumber)) {
                return true;
            } else {
                showWarningDialog(getString(R.string.register_phone_format));
                return false;
            }
        }
    }

    private void showWarningDialog(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}

