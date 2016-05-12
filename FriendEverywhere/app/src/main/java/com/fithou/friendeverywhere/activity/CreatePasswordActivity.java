package com.fithou.friendeverywhere.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.CreatePasswordAsyncTask;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

public class CreatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_fullname, edt_pass;
    private Button btn_create_pass;
    private String fullname, pass, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        user_id = Constants.getPreference(getApplicationContext(), Constants.XML_USER_ID);

        edt_fullname = (EditText) findViewById(R.id.edt_fullname_cp);
        edt_pass = (EditText) findViewById(R.id.edt_pass_cp);
        btn_create_pass = (Button) findViewById(R.id.btn_create_pass);

        btn_create_pass.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_create_pass:
                if (checkValidate()) {
                    new CreatePasswordAsyncTask(this).setCallback(new Callback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onPostExecute(Object o) {
                            final JSONObject data = (JSONObject) o;
                            if (o != null) {
                                try {
                                    String fullname = data.getString("fullname");
                                    String change_pass = data.getInt("change_pass") + "";
                                    Constants.savePreference(getApplicationContext(), Constants.XML_FULL_NAME, fullname);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_CREATE_PASSWORD, change_pass);

                                    Intent main = new Intent(CreatePasswordActivity.this, MainActivity.class);
                                    startActivity(main);
                                } catch (Exception e) {
                                    e.getMessage();
                                    return;
                                }
                            }
                        }
                    }).execute(user_id, fullname, pass);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }

    private boolean checkValidate() {
        fullname = edt_fullname.getText().toString().trim();
        pass = edt_pass.getText().toString().trim();
        if (StringSupport.isNullOrEmpty(fullname)) {
            showWarningDialog("Tên hiển thị không được bỏ trống.");
            return false;
        }
        if (StringSupport.isNullOrEmpty(pass)) {
            showWarningDialog("Mật khẩu không được bỏ trống.");
            return false;
        }
        return true;
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
