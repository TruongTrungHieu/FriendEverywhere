package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.AcceptFriendAdapter;
import com.fithou.friendeverywhere.asynctask.GetRequestFriendAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;

import java.util.ArrayList;

public class AcceptFriendActivity extends AppCompatActivity {

    private RecyclerView rv_accept_friend;
    private AcceptFriendAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendObject> listFriend = null;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_friend);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        user_id = Constants.getPreference(this, Constants.XML_USER_ID);

        inflateView();
        reloadData();

        new GetRequestFriendAsyncTask(this).setCallback(new Callback<ArrayList<FriendObject>>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ArrayList<FriendObject> list) {
                if (list != null) {
                    listFriend = new ArrayList<>();
                    listFriend = list;
                    reloadData();
                }
            }
        }).execute(user_id);
    }

    public void inflateView() {
        rv_accept_friend = (RecyclerView) findViewById(R.id.rv_accept_friend);
    }

    public void reloadData() {
        if (listFriend != null) {
            rv_accept_friend.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            rv_accept_friend.setLayoutManager(layoutManager);

            adapter = new AcceptFriendAdapter(this, listFriend);
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
