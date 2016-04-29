package com.fithou.friendeverywhere.adapter;

/**
 * Created by admin on 07/04/2016.
 */
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.activity.FriendProfileActivity;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.contactlist.DetailInfo;

public class ContactAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private Context context;
    private ArrayList<UserObject> contactList;

    public ContactAdapter(Context context, ArrayList<UserObject> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        DetailInfo detailInfo = (DetailInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.contact_child_row, null);
        }

        ImageButton imgbtn_call = (ImageButton)view.findViewById(R.id.imgbtn_contact_call);
        imgbtn_call.setOnClickListener(this);
        ImageButton imgbtn_sms = (ImageButton)view.findViewById(R.id.imgbtn_contact_sms);
        imgbtn_sms.setOnClickListener(this);
        ImageButton imgbtn_chat = (ImageButton)view.findViewById(R.id.imgbtn_contact_chat);
        imgbtn_chat.setOnClickListener(this);
        ImageButton imgbtn_info = (ImageButton)view.findViewById(R.id.imgbtn_contact_info);
        imgbtn_info.setOnClickListener(this);

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return contactList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return contactList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        UserObject headerInfo = (UserObject) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.contact_group_heading, null);
        }

        ImageView img_ava = (ImageView) view.findViewById(R.id.img_contact_ava);

        TextView txt_name = (TextView)view.findViewById(R.id.txt_contact_name);
        txt_name.setText(headerInfo.getFullname());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imgbtn_contact_call:
                Toast.makeText(context, "Gọi", Toast.LENGTH_LONG).show();
                //check parent hien tai xem co quyen goi dien k
                break;
            case R.id.imgbtn_contact_sms:
                Toast.makeText(context, "Nhắn tin", Toast.LENGTH_LONG).show();
                //check parent hien tai xem co quyen nhan tin k
                break;
            case R.id.imgbtn_contact_chat:
                Toast.makeText(context, "Trò chuyện", Toast.LENGTH_LONG).show();
                break;
            case R.id.imgbtn_contact_info:
                Toast.makeText(context, "Thông tin", Toast.LENGTH_LONG).show();
                Intent info = new Intent (context, FriendProfileActivity.class);
                context.startActivity(info);
                break;
            default:
                break;
        }
    }
}
