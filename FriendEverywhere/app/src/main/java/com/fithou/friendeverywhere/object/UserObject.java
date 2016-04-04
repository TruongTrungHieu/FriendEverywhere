package com.fithou.friendeverywhere.object;

import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.StringSupport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserObject implements Serializable {

    @SerializedName("user_id")
    private String user_id;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("birthday")
    private long birthday;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("latitude")
    private float latitude;
    @SerializedName("longtitude")
    private float longtitude;
    @SerializedName("pass")
    private String pass;
    @SerializedName("online_status")
    private int online_status;
    @SerializedName("photo")
    private String photo;
    @SerializedName("about_me")
    private String about_me;
    @SerializedName("gcm_id")
    private String gcm_id;

    public UserObject() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getGcm_id() {
        return gcm_id;
    }

    public void setGcm_id(String gcm_id) {
        this.gcm_id = gcm_id;
    }

    public static UserObject parseJsonToObject(JSONObject jsonObject) {
        Gson gson = new GsonBuilder().create();
        UserObject userObject = gson.fromJson(jsonObject.toString(), UserObject.class);
        return userObject;
    }

    public String getFirtCharacterName() {
        if (StringSupport.isNullOrEmpty(fullname)) {
            return "#";
        } else {
            String a[] = fullname.split(" ");
            String name = a[a.length - 1];
            return name.substring(0, 1);
        }
    }

    public String getSeriousName() {
        if (StringSupport.isNullOrEmpty(fullname)) {
            return "#";
        } else {
            String a[] = fullname.split(" ");
            String name = null;
            for (int i = a.length - 1; i >= 0; --i) {
                name += a[i] + " ";
            }
            return name;
        }
    }

}
