package com.fithou.friendeverywhere.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.ConfirmFriendAsyncTask;
import com.fithou.friendeverywhere.asynctask.CreateGroupAsyncTask;
import com.fithou.friendeverywhere.asynctask.SendFriendRequestAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.GroupObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

public class FriendProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FriendObject friendObject;
    private Button btn_ketban, btn_accept, btn_decline, btn_call, btn_message, btn_chat;
    private LinearLayout ll_friend, ll_not_friend, ll_requested;
    private TextView tv_fullname;
    private EditText edt_about, edt_birthday, edt_phone, edt_email;

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initData();
        inflateView();
        reloadData();
        hideKeyboard();
    }

    protected void initData() {
        user_id = Constants.getPreference(this, Constants.XML_USER_ID);
        friendObject = (FriendObject) getIntent().getSerializableExtra("FRIEND");
    }

    protected void inflateView() {
        btn_accept = (Button) findViewById(R.id.btn_accept_friend_request);
        btn_call = (Button) findViewById(R.id.btn_friend_call);
        btn_chat = (Button) findViewById(R.id.btn_friend_chat);
        btn_decline = (Button) findViewById(R.id.btn_decline_friend_request);
        btn_ketban = (Button) findViewById(R.id.btn_friend_request);
        btn_message = (Button) findViewById(R.id.btn_friend_message);
        tv_fullname = (TextView) findViewById(R.id.tv_full_fp);
        edt_about = (EditText) findViewById(R.id.et_friend_profileGioithieu);
        edt_birthday = (EditText) findViewById(R.id.et_friend_profileNgaysinh);
        edt_phone = (EditText) findViewById(R.id.et_friend_profileSdt);
        edt_email = (EditText) findViewById(R.id.et_friend_profileEmail);

        ll_friend = (LinearLayout) findViewById(R.id.ll_friend);
        ll_not_friend = (LinearLayout) findViewById(R.id.ll_not_friend);
        ll_requested = (LinearLayout) findViewById(R.id.ll_friend_requested);

        btn_accept.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_chat.setOnClickListener(this);
        btn_decline.setOnClickListener(this);
        btn_ketban.setOnClickListener(this);
        btn_message.setOnClickListener(this);

    }

    private void reloadData() {
        if (friendObject != null) {
            tv_fullname.setText(friendObject.getFriend_object().getFullname());
            edt_about.setText(friendObject.getFriend_object().getAbout_me());
            edt_birthday.setText(friendObject.getFriend_object().getBirthday());
            edt_phone.setText(friendObject.getFriend_object().getPhone());
            edt_email.setText(friendObject.getFriend_object().getEmail());
        }
        switch (friendObject.getFriend_status()) {
            case Constants.FRIEND_STATUS_NONE:
                ll_not_friend.setVisibility(View.VISIBLE);
                break;
            case Constants.FRIEND_STATUS_ACCEPTED:
                ll_friend.setVisibility(View.VISIBLE);
                break;
            case Constants.FRIEND_STATUS_REQUESTED:
                ll_requested.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_friend_request:
                new SendFriendRequestAsyncTask(this).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        final JSONObject data = (JSONObject) o;
                        if (o != null) {
                            try {
                                showWarningDialog("Yêu cầu kết bạn đã được gửi!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).execute(Constants.getPreference(this, Constants.XML_USER_ID), friendObject.getFriend_object().getUser_id());
                break;
            case R.id.btn_accept_friend_request:
                new ConfirmFriendAsyncTask(this).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        final JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            try {
                                showWarningDialog("Hai bạn đã trở thành bạn bè");
                                friendObject.setFriend_status(2);
                                reloadData();
                            } catch (Exception e) {
                                Log.e("accept_fp", e.getMessage());
                            }
                        }
                    }
                }).execute(user_id, friendObject.getFriend_object().getUser_id(), "2");
                break;
            case R.id.btn_decline_friend_request:
                new ConfirmFriendAsyncTask(this).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        final JSONObject jsonObject = (JSONObject) o;
                        if (jsonObject != null) {
                            try {
                                onBackPressed();
                            } catch (Exception e) {
                                Log.e("decline_fp", e.getMessage());
                            }
                        }
                    }
                }).execute(user_id, friendObject.getFriend_object().getUser_id(), "0");
                break;
            case R.id.btn_friend_call:
                if (friendObject != null) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + friendObject.getFriend_object().getPhone()));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    this.startActivity(callIntent);
                }
                break;
            case R.id.btn_friend_message:
                if (friendObject != null) {
                    if (!StringSupport.isNullOrEmpty(friendObject.getFriend_object().getPhone())) {
                        Uri uri = Uri.parse("smsto:" + friendObject.getFriend_object().getPhone());
                        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                        this.startActivity(it);
                    }
                }
                break;
            case R.id.btn_friend_chat:
                if (friendObject != null) {
                    new CreateGroupAsyncTask(this).setCallback(new Callback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onPostExecute(Object o) {
                            if (o != null) {
                                final JSONObject jsonObject = (JSONObject) o;
                                try {
                                    GroupObject groupObject = GroupObject.parseJsonToObject(jsonObject);
                                    Intent chat = new Intent(FriendProfileActivity.this, MessageActivity.class);
                                    chat.putExtra("GROUP", groupObject);
                                    startActivity(chat);
                                } catch (Exception e) {
                                    e.getMessage();
                                    return;
                                }
                            }
                        }
                    }).execute(friendObject.getFriend_object().getFullname(), user_id, friendObject.getFriend_object().getUser_id());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend_profile, menu);
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

    private void hideKeyboard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
