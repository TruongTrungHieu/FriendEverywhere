package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;
import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.CountryObject;
import com.fithou.friendeverywhere.object.MessageObject;
import com.fithou.friendeverywhere.ultis.StringSupport;

public class MessageActivity extends FirebaseLoginBaseActivity {

    private MessageObject lastMessageObj;
    private Firebase mFirebaseRef;
    FirebaseListAdapter<MessageObject> mListAdapter;

    private static final String fullname = "Hùng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://friendeverywhere.firebaseIO.com");

        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        Button sendButton = (Button) this.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textEdit.getText().toString();
                MessageObject message = new MessageObject(text, fullname);
                mFirebaseRef.push().setValue(message);
                textEdit.setText("");
            }
        });

        final ListView listView = (ListView) this.findViewById(android.R.id.list);
        mListAdapter = new FirebaseListAdapter<MessageObject>(this, MessageObject.class,
                android.R.layout.two_line_list_item, mFirebaseRef) {
            @Override
            protected void populateView(View v, MessageObject model, int position) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getUserObject().getFullname());
                ((TextView) v.findViewById(android.R.id.text2)).setText(model.getContent());
            }
        };
        listView.setAdapter(mListAdapter);

        inflateView();
        initData();
        reloadView();
    }

    @Override
    protected Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    protected void onFirebaseLoggedIn(AuthData authData) {
        super.onFirebaseLoggedIn(authData);
    }

    @Override
    protected void onFirebaseLoggedOut() {
        super.onFirebaseLoggedOut();
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListAdapter.cleanup();
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
