package com.fithou.friendeverywhere.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fithou.friendeverywhere.R;

/**
 * Created by TRUNGHIEU on 26/2/2016.
 */
public class MessageListFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton btn_new_message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_messagelist, container, false);

        btn_new_message = (FloatingActionButton)v.findViewById(R.id.btn_new_message);

        return v;
    }

    @Override
    public void onClick(View v) {

    }

}
