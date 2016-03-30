package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.CountryObject;
import com.fithou.friendeverywhere.object.MessageObject;
import com.fithou.friendeverywhere.ultis.StringSupport;

public class MessageActivity extends AppCompatActivity {

    private MessageObject lastMessageObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        inflateView();
        initData();
        reloadView();
    }

    protected void inflateView() {

    }

    protected void initData() {
        lastMessageObj = (MessageObject) getIntent().getSerializableExtra("MESSAGE_DETAILS");
    }

    protected void reloadView() {
        if (lastMessageObj != null && !StringSupport.isNullOrEmpty(lastMessageObj.getGroupObject().getDisplay_name())) {
            this.setTitle(lastMessageObj.getGroupObject().getDisplay_name());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_message_activity, menu);
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
