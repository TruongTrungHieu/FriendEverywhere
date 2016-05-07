package com.fithou.friendeverywhere.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.ultis.Constants;
import com.makeramen.roundedimageview.RoundedImageView;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button btn_accSettings, btn_aboutUs, btn_feedback, btn_logout, btn_terms, btn_suaInfo;
    private ImageView img_avatar;
    private TextView tv_fullname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    protected void inflateView(View view) {
        btn_aboutUs = (Button) view.findViewById(R.id.btn_aboutUs);
        btn_accSettings = (Button) view.findViewById(R.id.btn_accountSettings);
        btn_feedback = (Button) view.findViewById(R.id.btn_feedback);
        btn_logout = (Button) view.findViewById(R.id.btn_Logout);
        btn_suaInfo = (Button) view.findViewById(R.id.btn_UpdateInfo);
        btn_terms = (Button) view.findViewById(R.id.btn_terms);
        tv_fullname = (TextView) view.findViewById(R.id.tv_SettingFullname);
        img_avatar = (ImageView) view.findViewById(R.id.img_avatarSetting);

        btn_aboutUs.setOnClickListener(this);
        btn_accSettings.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        btn_suaInfo.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_terms.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        inflateView(v);

        String fullname = Constants.getPreference(this.getActivity(), Constants.XML_FULL_NAME);
        tv_fullname.setText(fullname);
        // TODO: set avatar
        
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_aboutUs:
                Intent aboutus = new Intent(this.getActivity(), AboutUsActivity.class);
                startActivity(aboutus);
                break;
            case R.id.btn_UpdateInfo:
                Intent update = new Intent(this.getActivity(), ProfileActivity.class);
                startActivity(update);
                break;
            case R.id.btn_accountSettings:
                Intent account = new Intent(this.getActivity(), AboutUsActivity.class);
                startActivity(account);
                break;
            case R.id.btn_feedback:
                Intent feedback = new Intent(this.getActivity(), FeedBackActivity.class);
                startActivity(feedback);
                break;
            case R.id.btn_terms:
                Intent terms = new Intent(this.getActivity(), TermsOfUse.class);
                startActivity(terms);
                break;
            case R.id.btn_Logout:
                this.getActivity().finish();
                logout_action();
                Intent logout = new Intent(this.getActivity(), RegisterActivity.class);
                startActivity(logout);
                break;
            default:
                break;
        }
    }

    private void logout_action() {
        Constants.savePreference(this.getContext(), Constants.XML_USER_ID, "");
        Constants.savePreference(this.getContext(), Constants.XML_BIRTHDAY, "");
        Constants.savePreference(this.getContext(), Constants.XML_EMAIL, "");
        Constants.savePreference(this.getContext(), Constants.XML_PHONE, "");
        Constants.savePreference(this.getContext(), Constants.XML_LATITUDE, "");
        Constants.savePreference(this.getContext(), Constants.XML_LONGTITUDE, "");
        Constants.savePreference(this.getContext(), Constants.XML_ONLINE_STATUS, "");
        Constants.savePreference(this.getContext(), Constants.XML_PHOTO, "");
        Constants.savePreference(this.getContext(), Constants.XML_ABOUT_ME, "");
        Constants.savePreference(this.getContext(), Constants.XML_GCM_ID, "");
    }

}
