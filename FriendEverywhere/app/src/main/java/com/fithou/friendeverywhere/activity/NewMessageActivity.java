package com.fithou.friendeverywhere.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.CountryAdapter;
import com.fithou.friendeverywhere.adapter.NewMessageListAdapter;
import com.fithou.friendeverywhere.asynctask.CreateGroupAsyncTask;
import com.fithou.friendeverywhere.asynctask.GetListFriendAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.GroupObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Commons;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;
import com.fithou.friendeverywhere.ultis.newmessage.UserTempObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NewMessageActivity extends AppCompatActivity implements Commons.OnRecycleItemClickListener_NewMessage {

    private ArrayList<UserTempObject> listUserTemp;
    private ArrayList<UserObject> listUser;
    private RecyclerView rv_new_message;
    private NewMessageListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String display_name_group;
    private String user_id;
    private String user_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        user_id = Constants.getPreference(this, Constants.XML_USER_ID);

        initData();
        inflateView();
        reloadView();

        new GetListFriendAsyncTask(this).setCallback(new Callback<ArrayList<FriendObject>>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ArrayList<FriendObject> list) {
                if (list != null) {
                    listUser = new ArrayList<>();
                    for (FriendObject f : list) {
                        listUser.add(f.getFriend_object());
                    }
                    initData();
                    reloadView();
                }
            }
        }).execute(user_id);
    }

    protected void initData() {
        if (listUser != null) {
            sortListUser();

            listUserTemp = new ArrayList<>();
            String header = null;
            for (UserObject userObject : listUser) {
                UserTempObject userTempObject = new UserTempObject();
                userTempObject.setUserObject(userObject);
                if (StringSupport.isNullOrEmpty(header)) {
                    userTempObject.setIsHeader();
                } else {
                    if (header.compareTo(StringSupport.getFirstCharacterName(userObject.getFullname())) != 0) {
                        userTempObject.setIsHeader();
                    }
                }
                header = StringSupport.getFirstCharacterName(userObject.getFullname());
                listUserTemp.add(userTempObject);
            }
        }
    }

    protected void inflateView() {
        rv_new_message = (RecyclerView) findViewById(R.id.rv_new_message);
    }

    protected void reloadView() {
        if (listUserTemp != null) {
            rv_new_message.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            rv_new_message.setLayoutManager(layoutManager);

            adapter = new NewMessageListAdapter(listUserTemp, this);
            rv_new_message.setAdapter(adapter);
        }
    }

    protected void sortListUser() {
        Collections.sort(listUser, new Comparator<UserObject>() {
            public int compare(UserObject v1, UserObject v2) {
                return v1.getSeriousName().compareTo(v2.getSeriousName());
            }
        });
    }

    protected void actionSend() {
        ArrayList<UserObject> listUserChoose = new ArrayList<>();
        for (UserTempObject userTempObject : listUserTemp) {
            if (userTempObject.isSelected()) {
                listUserChoose.add(userTempObject.getUserObject());
            }
        }
        if (listUserChoose.size() == 0) {
            Toast.makeText(getBaseContext(), getString(R.string.new_message_null_friend), Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (listUserChoose.size() == 1) {
                display_name_group = listUserChoose.get(0).getFullname();
                user_id_arr = listUserChoose.get(0).getUser_id();
            } else {
                for (int i = 0; i < listUserChoose.size(); ++i) {
                    if (i == 0) {
                        display_name_group = listUserChoose.get(i).getFullname();
                        user_id_arr = listUserChoose.get(i).getUser_id();
                    } else {
                        display_name_group += ", " + listUserChoose.get(i).getFullname();
                        user_id_arr += "_" + listUserChoose.get(i).getUser_id();
                    }
                }
                showDialogDisplayNameGroup();
            }
            // TODO: request create Group
            new CreateGroupAsyncTask(this).setCallback(new Callback() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public void onPostExecute(Object o) {
                    if (o != null) {
                        final JSONObject jsonObject = (JSONObject) o;
                        try {
                            GroupObject groupObject = GroupObject.parseJsonToObject(jsonObject);
                            Intent chat = new Intent(NewMessageActivity.this, MessageActivity.class);
                            chat.putExtra("GROUP", groupObject);
                            startActivity(chat);
                        } catch (Exception e) {
                            e.getMessage();
                            return;
                        }
                    }
                }
            }).execute(display_name_group, user_id, user_id_arr);
        }
    }

    protected void showDialogDisplayNameGroup() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewMessageActivity.this);
        alertDialog.setMessage(getString(R.string.description));

        final EditText input = new EditText(NewMessageActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setHint(getResources().getString(R.string.new_message_hint));
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        alertDialog.setView(input);
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton(getResources().getString(R.string.new_message_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        alertDialog.setNegativeButton(getResources().getString(R.string.new_message_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        final AlertDialog dialog;
        dialog = alertDialog.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input.getText().toString();
                if (StringSupport.isNullOrEmpty(name)) {
                    dialog.setMessage(getString(R.string.new_message_error));
                    return;
                }
                display_name_group = name;
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_newmessage_send:
                actionSend();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRecycleItemClicked(View v, int position) {
        UserTempObject userTempObject = listUserTemp.get(position);
        if (userTempObject.isSelected()) {
            listUserTemp.get(position).removeSelected();
        } else {
            listUserTemp.get(position).setIsSelected();
        }
        reloadView();
    }
}
