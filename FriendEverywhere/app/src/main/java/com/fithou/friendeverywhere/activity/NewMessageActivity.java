package com.fithou.friendeverywhere.activity;

import android.content.DialogInterface;
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
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.StringSupport;
import com.fithou.friendeverywhere.ultis.newmessage.UserTempObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NewMessageActivity extends AppCompatActivity {

    private ArrayList<UserTempObject> listUserTemp;
    private ArrayList<UserObject> listUser;
    private RecyclerView rv_new_message;
    private NewMessageListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String display_name_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initData();
        inflateView();
        reloadView();
    }

    protected void initData() {
        listUser = new ArrayList<>();
        // FAKE DATE
        UserObject u = new UserObject();
        u.setFullname("");
        listUser.add(u);
        UserObject u1 = new UserObject();
        u1.setFullname("Adele");
        listUser.add(u1);

        UserObject u2 = new UserObject();
        u2.setFullname("Đông Nhi");
        listUser.add(u2);
        UserObject u3 = new UserObject();
        u3.setFullname("Tóc Tiên");
        listUser.add(u3);
        UserObject u4 = new UserObject();
        u4.setFullname("Lương Bích Hữu");
        listUser.add(u4);
        UserObject u5 = new UserObject();
        u5.setFullname("Bích Phương");
        listUser.add(u5);
        UserObject u6 = new UserObject();
        u6.setFullname("Bhánh Phương");
        listUser.add(u6);
        UserObject u7 = new UserObject();
        u7.setFullname("Tuấn Hưng");
        listUser.add(u7);
        UserObject u8 = new UserObject();
        u8.setFullname("Miu Lê");
        listUser.add(u8);
        UserObject u9 = new UserObject();
        u9.setFullname("Chị Lý");
        listUser.add(u9);
        UserObject u11 = new UserObject();
        u11.setFullname("Khởi My");
        listUser.add(u11);
        UserObject u12 = new UserObject();
        u12.setFullname("Hamlet Trương");
        listUser.add(u12);
        UserObject u13 = new UserObject();
        u13.setFullname("Như Quỳnh");
        listUser.add(u13);

        sortListUser();

        listUserTemp = new ArrayList<>();
        String header = null;
        for (UserObject userObject : listUser) {
            UserTempObject userTempObject = new UserTempObject();
            userTempObject.setUserObject(userObject);
            if (StringSupport.isNullOrEmpty(header)) {
                userTempObject.setIsHeader();
            } else {
                if (header.compareTo(userObject.getFirtCharacterName()) != 0) {
                    userTempObject.setIsHeader();
                }
            }
            header = userObject.getFirtCharacterName();
            listUserTemp.add(userTempObject);
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

            adapter = new NewMessageListAdapter(listUserTemp);
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
            } else {
                for (int i = 0; i < listUserChoose.size(); ++i) {
                    if (i == 0) {
                        display_name_group = listUserChoose.get(i).getFullname();
                    } else {
                        display_name_group += ", " + listUserChoose.get(i).getFullname();
                    }
                }
            }
            showDialogDisplayNameGroup();
            // TODO: request create Group
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
}
