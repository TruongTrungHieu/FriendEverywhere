package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.ChangePasswordAsyncTask;
import com.fithou.friendeverywhere.asynctask.UpdateProfileAsyncTask;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText et_old, et_new, et_confirm;
    private Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        et_old = (EditText) findViewById(R.id.et_old_password);
        et_new = (EditText) findViewById(R.id.et_new_password);
        et_confirm = (EditText) findViewById(R.id.et_confirm_new_password);
        btn_ok = (Button) findViewById(R.id.btn_ok_change_pass);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            check();

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void check() {
        if (!StringSupport.isNullOrEmpty(et_old.getText().toString().trim()) &&
                (!StringSupport.isNullOrEmpty(et_new.getText().toString().trim())) &&
                (!StringSupport.isNullOrEmpty(et_confirm.getText().toString().trim()))) {
            if (et_new.getText().toString().trim().length() >= 6) {
                if (et_new.getText().toString().equals(et_confirm.getText().toString())) {
                    new ChangePasswordAsyncTask(getApplicationContext()).setCallback(new Callback() {
                        @Override
                        public void onPreExecute() {

                        }
                        @Override
                        public void onPostExecute(Object o) {
                            onBackPressed();
                            Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công!", Toast.LENGTH_LONG).show();
                            final JSONObject data = (JSONObject) o;
                            if (o != null) {
                                try {

                                } catch (Exception e) {
                                    Log.d("login service", e.getMessage());
                                }
                            }
                        }
                    }).execute(Constants.XML_USER_ID, et_old.getText().toString().trim(), et_new.getText().toString().trim());
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu mới không khớp!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(ChangePasswordActivity.this, "Độ dài mật khẩu phải trên 6 ký tự!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(ChangePasswordActivity.this, "Chưa nhập đầy đủ!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change_pass, menu);
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
