package com.fithou.friendeverywhere.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.FindFriendAdapter;
import com.fithou.friendeverywhere.asynctask.FindFriendAsyncTask;
import com.fithou.friendeverywhere.object.CountryObject;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

import org.json.JSONObject;

import java.util.ArrayList;

public class FindFriendActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rv_find_friend;
    private FindFriendAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendObject> listFriend = null;
    private Button btn_find_friend;
    private EditText edt_phone_number;
    private TextView edt_country;
    private String phoneNumber;
    private CountryObject countryObject;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initData();
        inflateView();
        reloadView();
    }

    private void inflateView() {
        edt_phone_number = (EditText) findViewById(R.id.edt_phone_number_ff);
        edt_country = (TextView) findViewById(R.id.tv_find_quoc_gia);
        btn_find_friend = (Button) findViewById(R.id.btn_find_friend);
        rv_find_friend = (RecyclerView) findViewById(R.id.rv_find_friend);

        btn_find_friend.setOnClickListener(this);
    }

    private void initData() {
        countryObject = new CountryObject("Vietnam", "+84");
        user_id = Constants.getPreference(this, Constants.XML_USER_ID);
    }

    private void reloadView() {
        if (countryObject != null) {
            edt_country.setText(countryObject.toString());
        }

        if (listFriend != null) {
            rv_find_friend.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            rv_find_friend.setLayoutManager(layoutManager);

            adapter = new FindFriendAdapter(this, listFriend);
            rv_find_friend.setAdapter(adapter);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_find_friend:
                if (checkPhonenumber()) {
                    new FindFriendAsyncTask(this).setCallback(new Callback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onPostExecute(Object o) {
                            final JSONObject data = (JSONObject) o;
                            try {
                                listFriend = new ArrayList<>();

                                String user_id = data.getString("user_id");
                                String full_name = data.optString("fullname", "");
                                String birthday = data.optString("birthday", "");
                                String email = data.optString("email", "");
                                String phone = data.optString("phone", "");
                                double latitude = data.optDouble("latitude", -1);
                                double longitude = data.optDouble("longtitude", -1);
                                int online_status = data.optInt("online_status", 0);
                                String photo = data.optString("photo", "");
                                String about_me = data.optString("about_me", "");
                                String gcm_id = data.optString("gcm_id", "");
                                int friend_status = data.getInt("friend_status");

                                UserObject userObject = new UserObject();
                                userObject.setUser_id(user_id);
                                userObject.setFullname(full_name);
                                userObject.setBirthday(birthday);
                                userObject.setEmail(email);
                                userObject.setPhone(phone);
                                userObject.setLatitude((float) latitude);
                                userObject.setLongtitude((float) longitude);
                                userObject.setOnline_status(online_status);
                                userObject.setPhoto(photo);
                                userObject.setAbout_me(about_me);
                                userObject.setGcm_id(gcm_id);

                                FriendObject friendObject = new FriendObject();
                                friendObject.setFriend_object(userObject);
                                friendObject.setFriend_status(friend_status);

                                listFriend.add(friendObject);
                                reloadView();
                            } catch (Exception e) {
                                Log.d("FindFriendAsyncTask", e.getMessage());
                            }
                        }
                    }).execute(phoneNumber, user_id);
                }
                break;
            default:
                break;
        }
    }

    private boolean checkPhonenumber() {
        if (StringSupport.isNullOrEmpty(edt_phone_number.getText().toString())) {
            showWarningDialog(getString(R.string.register_phone_empty));
            return false;
        } else {
            String phone = edt_phone_number.getText().toString();
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

    @Override
    protected void onResume() {
        super.onResume();
        countryObject = (CountryObject) getIntent().getSerializableExtra("COUNTRY");
        if (countryObject != null) {
            edt_country.setText(countryObject.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_find_friend, menu);
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
