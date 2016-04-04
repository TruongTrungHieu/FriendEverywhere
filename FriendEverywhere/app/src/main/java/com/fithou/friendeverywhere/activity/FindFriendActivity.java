package com.fithou.friendeverywhere.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.AcceptFriendAdapter;
import com.fithou.friendeverywhere.adapter.FindFriendAdapter;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;

import java.util.ArrayList;

public class FindFriendActivity extends AppCompatActivity {
    private RecyclerView rv_find_friend;
    private FindFriendAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendObject> listFriend = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        init();
        reloadData();
    }

    public void init()
    {
        rv_find_friend = (RecyclerView) findViewById(R.id.rv_find_friend);
    }
    public void reloadData() {

        listFriend = new ArrayList<>();
        FriendObject f1 = new FriendObject();
        UserObject u = new UserObject();
        u.setFullname("Khang Minh");
        f1.setFriend_object(u);
        listFriend.add(f1);
        listFriend.add(f1);
        listFriend.add(f1);
        listFriend.add(f1);
        listFriend.add(f1);
        listFriend.add(f1);
        listFriend.add(f1);
        listFriend.add(f1);

        if (listFriend != null) {
            rv_find_friend.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            rv_find_friend.setLayoutManager(layoutManager);

            adapter = new FindFriendAdapter(listFriend);
            rv_find_friend.setAdapter(adapter);
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

}
