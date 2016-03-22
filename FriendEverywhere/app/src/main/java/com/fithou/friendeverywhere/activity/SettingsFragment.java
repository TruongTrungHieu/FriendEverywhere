package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fithou.friendeverywhere.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button btn_suaInfo, btn_accSettings, btn_aboutUs, btn_feedback, btn_logout;
    private ImageView img_avatar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    protected void inflateView(){
/*        btn_aboutUs = Button.OnClickListener();
        btn_accSettings = (Button)findViewById();
        btn_feedback = (Button)findViewById();
        btn_logout = (Button)findViewById();
        btn_suaInfo = (Button)findViewById();

        img_avatar = (ImageView)findViewById(R.id.iv_profileactivity);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        return v;
    }

    @Override
    public void onClick(View v) {

    }

    //Intent t = new Intent(SettingsFragment.this, ProfileActivity.class);
    //startActivity(t);
}
