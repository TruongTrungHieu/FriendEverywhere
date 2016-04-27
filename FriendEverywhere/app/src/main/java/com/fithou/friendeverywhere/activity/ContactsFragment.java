package com.fithou.friendeverywhere.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.ContactAdapter;
import com.fithou.friendeverywhere.object.UserObject;

import java.util.ArrayList;


public class ContactsFragment extends Fragment implements View.OnClickListener {

    private ArrayList<UserObject> userList;

    private ContactAdapter contactAdapter;
    private ExpandableListView myList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contacts, container, false);

        userList = new ArrayList<UserObject>();
        UserObject userObject = new UserObject();
        userObject.setFullname("Vu Huy Hung");
        userList.add(userObject);
        UserObject userObject1 = new UserObject();
        userObject1.setFullname("Hoang Thanh Hoa");
        userList.add(userObject1);
        UserObject userObject2 = new UserObject();
        userObject2.setFullname("Do Xuan Cho");
        userList.add(userObject2);
        UserObject userObject3 = new UserObject();
        userObject3.setFullname("Nhu Thanh Chung");
        userList.add(userObject3);
        UserObject userObject4 = new UserObject();
        userObject4.setFullname("Mai Kim Chi");
        userList.add(userObject4);

        myList = (ExpandableListView)v.findViewById(R.id.expand_list_view_contact);
        myList.setGroupIndicator(null);
        myList.setClickable(true);
        myList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    myList.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        contactAdapter = new ContactAdapter(this.getActivity(), userList);

        myList.setAdapter(contactAdapter);
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
