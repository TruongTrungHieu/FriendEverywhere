package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.ultis.Constants;

public class FriendProfileActivity extends AppCompatActivity {

    private FriendObject friendObject;
    private Button btn_ketban, btn_accept, btn_decline, btn_call, btn_message, btn_chat;
    private LinearLayout ll_friend, ll_not_friend, ll_requested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initData();
        inflateView();
        reloadData();
    }

    protected void initData() {
        friendObject = new FriendObject();
        friendObject.setFriend_status(0);
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
}
