package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.AcceptFriendAdapter;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;

import java.util.ArrayList;

public class AcceptFriendActivity extends AppCompatActivity {

    private RecyclerView rv_accept_friend;
    private AcceptFriendAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendObject> listFriend = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_friend);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        inflateView();
        reloadData();
    }

    public void inflateView() {
        rv_accept_friend = (RecyclerView) findViewById(R.id.rv_accept_friend);
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
            rv_accept_friend.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            rv_accept_friend.setLayoutManager(layoutManager);

            adapter = new AcceptFriendAdapter(listFriend);
            rv_accept_friend.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept_friend, menu);
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
