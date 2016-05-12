package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.ContactAdapter;
import com.fithou.friendeverywhere.asynctask.GetListFriendAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;

import java.util.ArrayList;


public class ContactsFragment extends Fragment implements View.OnClickListener {

    private ArrayList<FriendObject> listFriend;
    private ContactAdapter contactAdapter;
    private ExpandableListView myList;
    private FloatingActionButton btn_find_friend;
    private String user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        user_id = Constants.getPreference(this.getContext(), Constants.XML_USER_ID);

        myList = (ExpandableListView) v.findViewById(R.id.expand_list_view_contact);
        myList.setGroupIndicator(null);
        myList.setClickable(true);
        myList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    myList.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        btn_find_friend = (FloatingActionButton) v.findViewById(R.id.btn_find_friend);
        btn_find_friend.setOnClickListener(this);

        reloadView();

        new GetListFriendAsyncTask(this.getActivity()).setCallback(new Callback<ArrayList<FriendObject>>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ArrayList<FriendObject> list) {
                if (list != null) {
                    listFriend = new ArrayList<>();
                    listFriend = list;
                    reloadView();
                }
            }
        }).execute(user_id);

        return v;
    }

    private void reloadView() {
        if (listFriend != null) {
            contactAdapter = new ContactAdapter(this.getActivity(), listFriend);
            myList.setAdapter(contactAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_find_friend) {
            Intent find_friend_activity = new Intent(this.getActivity(), FindFriendActivity.class);
            startActivity(find_friend_activity);
        }
    }
}
