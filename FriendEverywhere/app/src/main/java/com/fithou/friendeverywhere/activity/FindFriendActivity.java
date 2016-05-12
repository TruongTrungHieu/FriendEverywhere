package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.AcceptFriendAdapter;
import com.fithou.friendeverywhere.adapter.FindFriendAdapter;
import com.fithou.friendeverywhere.asynctask.FindFriendAsyncTask;
import com.fithou.friendeverywhere.asynctask.LoginAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;

import org.json.JSONObject;

import java.util.ArrayList;

public class FindFriendActivity extends AppCompatActivity {
    private RecyclerView rv_find_friend;
    private FindFriendAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendObject> listFriend = null;
    private Button btn_find_friend;
    private EditText et_phone_number;
    private String id_found, name_found;
    private TextView tv_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);
        btn_find_friend=(Button)findViewById(R.id.btn_find_friend);
        et_phone_number=(EditText)findViewById(R.id.et_find_std);
        tv_country = (TextView)findViewById(R.id.tv_find_quoc_gia);

        tv_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose_country = new Intent(getApplicationContext(), CountryListActivity.class);
                choose_country.putExtra("REGISTER", false);
                startActivity(choose_country);
            }
        });

        btn_find_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FindFriendAsyncTask(getApplicationContext()).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        final JSONObject data = (JSONObject) o;
                        if (o != null) {
                            try {
                                id_found = data.getString("user_id");
                                name_found = data.getString("fullname");
                            } catch (Exception e) {
                                Log.d("login service", e.getMessage());
                            }
                        }
                    }
                }).execute("+84"+et_phone_number.getText().toString().trim());
            }
        });

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
        /*FriendObject f = new FriendObject();
        UserObject u = new UserObject();
        u.setFullname(name_found);
        u.setUser_id(id_found);
        f.setFriend_object(u);
        listFriend.add(f);*/

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
