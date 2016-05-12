package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.adapter.MessageListAdapter;
import com.fithou.friendeverywhere.asynctask.GetListGroupAsyncTask;
import com.fithou.friendeverywhere.object.GroupObject;
import com.fithou.friendeverywhere.object.MessageObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;

import java.util.ArrayList;

public class MessageListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rv_mess_list;
    private MessageListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GroupObject> listGroup;
    private FloatingActionButton floatingActionButton;
    private String user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_messagelist, container, false);

        rv_mess_list = (RecyclerView) v.findViewById(R.id.rv_message_list);
        floatingActionButton = (FloatingActionButton) v.findViewById(R.id.btn_new_message);

        floatingActionButton.setOnClickListener(this);

        initData();
        reloadData();

        new GetListGroupAsyncTask(this.getActivity()).setCallback(new Callback<ArrayList<GroupObject>>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ArrayList<GroupObject> list) {
                if (list != null) {
                    listGroup = new ArrayList<>();
                    listGroup = list;
                    reloadData();
                }
            }
        }).execute(user_id);

        return v;
    }

    public void reloadData() {
        if (listGroup != null) {
            rv_mess_list.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this.getActivity());
            rv_mess_list.setLayoutManager(layoutManager);

            adapter = new MessageListAdapter(listGroup, this.getActivity());
            rv_mess_list.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_new_message:
                Intent new_mess_activity = new Intent(this.getActivity(), NewMessageActivity.class);
                startActivity(new_mess_activity);
                break;
            default:
                break;
        }
    }


    public void initData() {
        user_id = Constants.getPreference(this.getContext(), Constants.XML_USER_ID);
    }
}
