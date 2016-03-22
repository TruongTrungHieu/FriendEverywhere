package com.fithou.friendeverywhere.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

public class GroupObject implements Serializable{

    @SerializedName("group_id")
    private String group_id;
    @SerializedName("display_name")
    private String display_name;
    @SerializedName("user_id")
    private String user_id;

    public GroupObject() {

    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public static GroupObject parseJsonToObject(JSONObject jsonObject) {
        Gson gson = new GsonBuilder().create();
        GroupObject groupObject = gson.fromJson(jsonObject.toString(), GroupObject.class);
        return groupObject;
    }

}



