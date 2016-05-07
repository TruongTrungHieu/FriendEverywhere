package com.fithou.friendeverywhere.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.UpdateProfileAsyncTask;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;

import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_aboutme, et_birthday, et_fullname, et_email, et_phone;
    private ImageView img_avatar;
    private ImageView img_capture;
    private Menu currentMenu;
    private boolean editing = false;
    private UserObject userObject;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        inflateView();
        initData();
        reloadView();
        hideKeyboard();
    }

    private void reloadView() {
        // TODO: set image
        et_fullname.setText(userObject.getFullname());
        et_aboutme.setText(userObject.getAbout_me());
        et_birthday.setText(userObject.getBirthday());
        et_phone.setText(userObject.getPhone());
        et_email.setText(userObject.getEmail());
        enableAll();
    }

    protected void initData() {
        userObject = new UserObject();
        userObject.setUser_id(Constants.getPreference(this, Constants.XML_USER_ID));
        userObject.setFullname(Constants.getPreference(this, Constants.XML_FULL_NAME));
        userObject.setAbout_me(Constants.getPreference(this, Constants.XML_ABOUT_ME));
        userObject.setBirthday(Constants.getPreference(this, Constants.XML_BIRTHDAY));
        userObject.setEmail(Constants.getPreference(this, Constants.XML_EMAIL));
        userObject.setPhoto(Constants.getPreference(this, Constants.XML_PHOTO));
        userObject.setPhone(Constants.getPreference(this, Constants.XML_PHONE));
    }

    protected void inflateView() {
        et_aboutme = (EditText) findViewById(R.id.et_profileGioithieu);
        et_birthday = (EditText) findViewById(R.id.et_profileNgaysinh);
        et_fullname = (EditText) findViewById(R.id.et_profileTen);
        et_email = (EditText) findViewById(R.id.et_profileEmail);
        et_phone = (EditText) findViewById(R.id.et_profileSdt);
        img_avatar = (ImageView) findViewById(R.id.iv_profileactivity);
        img_capture = (ImageView) findViewById(R.id.img_capture_profile);

        img_capture.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_capture_profile:
                new ImageDialog(this, R.layout.dialog_capture).show();
                break;
            default:
                break;
        }
    }

    protected void enableAll() {
        et_fullname.setEnabled(editing);
        et_birthday.setEnabled(editing);
        et_email.setEnabled(editing);
        et_aboutme.setEnabled(editing);
        img_capture.setVisibility(editing ? View.VISIBLE : View.GONE);
        if (currentMenu != null) {
            currentMenu.getItem(0).setVisible(!editing);
            currentMenu.getItem(1).setVisible(editing);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        currentMenu = menu;
        currentMenu.getItem(0).setVisible(!editing);
        currentMenu.getItem(1).setVisible(editing);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_profile_done:
                if (checkValidate()) {
                    new UpdateProfileAsyncTask(this).setCallback(new Callback() {
                        @Override
                        public void onPreExecute() {

                        }

                        @Override
                        public void onPostExecute(Object o) {
                            final JSONObject data = (JSONObject) o;
                            if (o != null) {
                                try {
                                    String user_id = data.getString("user_id");
                                    String fullname = data.getString("fullname");
                                    String birthday = data.getString("birthday");
                                    String email = data.getString("email");
                                    String phone = data.getString("phone");
                                    String latitude = data.getString("latitude");
                                    String longtitude = data.getString("longtitude");
                                    String online_status = data.getString("online_status");
                                    String photo = data.getString("photo");
                                    String about_me = data.getString("about_me");
                                    String gcm_id = data.getString("gcm_id");

                                    Constants.savePreference(getApplicationContext(), Constants.XML_USER_ID, user_id);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_FULL_NAME, fullname);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_BIRTHDAY, birthday);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_EMAIL, email);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_PHONE, phone);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_LATITUDE, latitude);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_LONGTITUDE, longtitude);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_ONLINE_STATUS, online_status);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_PHOTO, photo);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_ABOUT_ME, about_me);
                                    Constants.savePreference(getApplicationContext(), Constants.XML_GCM_ID, gcm_id);

                                    initData();
                                } catch (Exception e) {
                                    Log.d("login service", e.getMessage());
                                }
                            }
                        }
                    }).execute(userObject.getUser_id(), userObject.getFullname(), userObject.getBirthday(), userObject.getEmail(), userObject.getPhoto(), userObject.getAbout_me());
                }
                editing = !editing;
                enableAll();
                break;
            case R.id.action_profile_edit:
                editing = !editing;
                enableAll();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideKeyboard() {
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean checkValidate() {
        userObject.setFullname(et_fullname.getText().toString().trim());
        userObject.setAbout_me(et_aboutme.getText().toString().trim());
        userObject.setBirthday(et_birthday.getText().toString().trim());
        userObject.setEmail(et_email.getText().toString().trim());
//        userObject.setPhoto(Constants.getPreference(this, Constants.XML_PHOTO));
        return true;
    }

    public class ImageDialog extends Dialog implements View.OnClickListener {
        private final Button btn_capture;
        private final Button btn_album;
        Context mContext;

        public ImageDialog(Context context, int resource) {
            super(context);
            this.mContext = context;
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setCancelable(true);
            setContentView(resource);
            btn_capture = (Button) findViewById(R.id.btn_capture);
            btn_album = (Button) findViewById(R.id.btn_album);
            btn_capture.setOnClickListener(this);
            btn_album.setOnClickListener(this);

            WindowManager.LayoutParams wmlp = getWindow().getAttributes();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int width = displaymetrics.widthPixels;
            wmlp.width = (int) (width * 0.7);
            wmlp.gravity = Gravity.TOP;
            wmlp.y = 500;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_capture:

                    break;
                case R.id.btn_album:
                    Intent galleryIntent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_FILE);
                    this.cancel();
                    break;
                default:
                    break;
            }
        }
    }
}
