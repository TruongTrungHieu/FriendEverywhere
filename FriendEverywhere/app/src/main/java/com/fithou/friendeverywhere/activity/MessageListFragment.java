package com.fithou.friendeverywhere.activity;

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
import com.fithou.friendeverywhere.object.MessageObject;
import com.fithou.friendeverywhere.object.UserObject;

import java.util.ArrayList;

public class MessageListFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton btn_new_message;
    private RecyclerView rv_mess_list;
    private MessageListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MessageObject> listMessage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_messagelist, container, false);

        btn_new_message = (FloatingActionButton) v.findViewById(R.id.btn_new_message);
        rv_mess_list = (RecyclerView) v.findViewById(R.id.rv_message_list);

        initData();
        reloadData();


        return v;
    }

    public void reloadData() {
        if (listMessage != null) {
            rv_mess_list.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this.getActivity());
            rv_mess_list.setLayoutManager(layoutManager);

            adapter = new MessageListAdapter(listMessage, this.getActivity());
            rv_mess_list.setAdapter(adapter);
        }
    }


    @Override
    public void onClick(View v) {

    }

    public void initData() {
        listMessage = new ArrayList<>();
        MessageObject messageObject = new MessageObject();
        UserObject userObject = new UserObject();
        messageObject.setContent("dafjaskdjfhasjkdfhajkdshfjkahdfjkahdf");
        userObject.setFullname("Nguyen Tran Dung");
        messageObject.setUserObject(userObject);
        listMessage.add(messageObject);

        MessageObject messageObject1 = new MessageObject();
        UserObject userObject1 = new UserObject();
        messageObject1.setContent("dafjaskdjfhasjkdfhajkdshfjkahdadsfjhadsjkfhajkfhjkdhfjkadshfjkadshfjkhadsfjkhasdjkfhsajkfhdasjkhdfdasjkhfjkashdfjkashdfkjhmndfmadsfjkfjkahdf");
        userObject1.setFullname("Nguyen Tran Dung");
        messageObject1.setUserObject(userObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);
        listMessage.add(messageObject1);

    }
}
