package com.fithou.friendeverywhere.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageButton;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.BaseEnum;
import com.fithou.friendeverywhere.ultis.TypefaceSpan;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BaseEnum.TabIndex tabIndex = BaseEnum.TabIndex.TabIndexMap;
    private ImageButton btn_tab_map, btn_tab_messages, btn_tab_contacts, btn_tab_settings;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpannableString s = new SpannableString("Friend Everywhere");
        s.setSpan(new TypefaceSpan(this, "BLKCHCRY.TTF"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);

        btn_tab_map = (ImageButton) findViewById(R.id.btn_tab_map);
        btn_tab_contacts = (ImageButton) findViewById(R.id.btn_tab_contacts);
        btn_tab_messages = (ImageButton) findViewById(R.id.btn_tab_messages);
        btn_tab_settings = (ImageButton) findViewById(R.id.btn_tab_settings);

        btn_tab_map.setOnClickListener(this);
        btn_tab_contacts.setOnClickListener(this);
        btn_tab_messages.setOnClickListener(this);
        btn_tab_settings.setOnClickListener(this);

        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        MapFragment mapFragment = new MapFragment();
        transaction.add(R.id.container, mapFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tab_map:
                tabIndex = BaseEnum.TabIndex.TabIndexMap;
                mapButtonTap();
                break;
            case R.id.btn_tab_messages:
                tabIndex = BaseEnum.TabIndex.TabIndexMessages;
                messageListButtonTap();
                break;
            case R.id.btn_tab_contacts:
                tabIndex = BaseEnum.TabIndex.TabIndexContacts;
                contactsButtonTap();
                break;
            case R.id.btn_tab_settings:
                tabIndex = BaseEnum.TabIndex.TabIndexSettings;
                settingButtonTap();
                break;
            default:
                break;
        }
    }

    private void mapButtonTap() {
        btn_tab_map.setBackgroundColor(getResources().getColor(R.color.footer_actived));
        btn_tab_contacts.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_messages.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_settings.setBackgroundColor(getResources().getColor(R.color.transparent));

        transaction = manager.beginTransaction();
        MapFragment mapFragment = new MapFragment();
        transaction.replace(R.id.container, mapFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void messageListButtonTap() {
        btn_tab_map.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_contacts.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_messages.setBackgroundColor(getResources().getColor(R.color.footer_actived));
        btn_tab_settings.setBackgroundColor(getResources().getColor(R.color.transparent));

        transaction = manager.beginTransaction();
        MessageListFragment messageListFragment = new MessageListFragment();
        transaction.replace(R.id.container, messageListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void contactsButtonTap() {
        btn_tab_map.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_contacts.setBackgroundColor(getResources().getColor(R.color.footer_actived));
        btn_tab_messages.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_settings.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    private void settingButtonTap() {
        btn_tab_map.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_contacts.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_messages.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_tab_settings.setBackgroundColor(getResources().getColor(R.color.footer_actived));
    }
}
