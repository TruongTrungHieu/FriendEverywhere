package com.fithou.friendeverywhere.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.SendFriendRequestAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;

import org.json.JSONObject;

public class FriendProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FriendObject friendObject;
    private Button btn_ketban, btn_accept, btn_decline, btn_call, btn_message, btn_chat;
    private LinearLayout ll_friend, ll_not_friend, ll_requested;
    private String userId, friendId;

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
        friendObject = new FriendObject();
        friendObject.setFriend_status(0);
        userId = Constants.XML_USER_ID;
        friendId = "1462355499";
    }

    protected void inflateView(){
        btn_accept = (Button)findViewById(R.id.btn_accept_friend_request);
        btn_call = (Button)findViewById(R.id.btn_friend_call);
        btn_chat = (Button)findViewById(R.id.btn_friend_chat);
        btn_decline = (Button)findViewById(R.id.btn_decline_friend_request);
        btn_ketban = (Button)findViewById(R.id.btn_friend_request);
        btn_message = (Button)findViewById(R.id.btn_friend_message);

        ll_friend = (LinearLayout)findViewById(R.id.ll_friend);
        ll_not_friend = (LinearLayout)findViewById(R.id.ll_not_friend);
        ll_requested = (LinearLayout)findViewById(R.id.ll_friend_requested);

        btn_accept.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_chat.setOnClickListener(this);
        btn_decline.setOnClickListener(this);
        btn_ketban.setOnClickListener(this);
        btn_message.setOnClickListener(this);

    }

    protected void checkFriend(){

        switch (friendObject.getFriend_status())
        {
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

    private void reloadData() {
        checkFriend();
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

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).execute(userId, friendId);
                break;
            case R.id.btn_accept_friend_request:

                break;
            case R.id.btn_decline_friend_request:

                break;
            case R.id.btn_friend_call:

                break;
            case R.id.btn_friend_message:

                break;
            case R.id.btn_friend_chat:

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

    private void hideKeyboard(){
        View v = this.getCurrentFocus();
        if (v != null){
            InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
