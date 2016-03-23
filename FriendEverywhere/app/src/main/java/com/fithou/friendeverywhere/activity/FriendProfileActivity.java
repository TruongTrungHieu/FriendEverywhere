package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.FriendObject;

public class FriendProfileActivity extends AppCompatActivity {

    private FriendObject friendObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
    }
}
