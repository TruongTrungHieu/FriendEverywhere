package com.fithou.friendeverywhere.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.fithou.friendeverywhere.object.GroupObject;
import com.fithou.friendeverywhere.object.MessageObject;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;

public class MessageActivity extends FirebaseLoginBaseActivity {

    private GroupObject groupObject;
    private Firebase mFirebaseRef;
    FirebaseListAdapter<MessageObject> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        inflateView();
        initData();
        reloadView();

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://friendeverywhere-app.firebaseio.com/" + groupObject.getGroup_id());

        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        final Button sendButton = (Button) this.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringSupport.isNullOrEmpty(textEdit.getText().toString().trim())) {
                    String text = textEdit.getText().toString();
                    MessageObject message = new MessageObject(text, Constants.getPreference(getApplicationContext(), Constants.XML_USER_ID), Constants.getPreference(getApplicationContext(), Constants.XML_PHOTO), Constants.getPreference(getApplicationContext(), Constants.XML_FULL_NAME));
                    mFirebaseRef.push().setValue(message);
                    textEdit.setText("");
                    sendButton.setBackground(getResources().getDrawable(R.drawable.send_f));

                }
            }
        });

        textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!StringSupport.isNullOrEmpty(textEdit.getText().toString().trim())) {
                    sendButton.setBackground(getResources().getDrawable(R.drawable.send));
                } else {
                    sendButton.setBackground(getResources().getDrawable(R.drawable.send_f));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final ListView listView = (ListView) this.findViewById(android.R.id.list);

        mListAdapter = new FirebaseListAdapter<MessageObject>(this, MessageObject.class,
                R.layout.item_chat, mFirebaseRef) {
            @Override
            protected void populateView(View v, MessageObject model, int position) {
                String user_id = Constants.getPreference(getApplicationContext(), Constants.XML_USER_ID);
                TextView tv_send = (TextView) v.findViewById(R.id.tv_message_send);
                TextView tv_receive = (TextView) v.findViewById(R.id.tv_message_receive);
                TextView tv_name_receive = (TextView) v.findViewById(R.id.tv_name_receive);
                LinearLayout ll_send = (LinearLayout) v.findViewById(R.id.ll_send);
                LinearLayout ll_receive = (LinearLayout) v.findViewById(R.id.ll_receive);

                if (user_id.equals(model.getSend_user_id())) {
                    // message from me
                    ll_receive.setVisibility(View.GONE);
                    ll_send.setVisibility(View.VISIBLE);
                    tv_send.setText(model.getContent());
                } else {
                    // message to me
                    ll_receive.setVisibility(View.VISIBLE);
                    ll_send.setVisibility(View.GONE);
                    tv_receive.setText(model.getContent());
                    tv_name_receive.setText(model.getSend_name());
                }
            }
        };

        listView.setAdapter(mListAdapter);
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
        groupObject = (GroupObject) getIntent().getSerializableExtra("GROUP");
    }

    protected void reloadView() {
        if (groupObject != null && !StringSupport.isNullOrEmpty(groupObject.getDisplay_name())) {
            this.setTitle(groupObject.getDisplay_name());
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
