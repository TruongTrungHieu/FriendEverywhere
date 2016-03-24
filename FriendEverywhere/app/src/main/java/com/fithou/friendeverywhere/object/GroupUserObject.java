package com.fithou.friendeverywhere.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class GroupUserObject implements Serializable {

    @SerializedName("group_user_id")
    private String group_user_id;
    private GroupObject group;
    private UserObject user;

    public GroupUserObject() {

    }

    public String getGroup_user_id() {
        return group_user_id;
    }

    public void setGroup_user_id(String group_user_id) {
        this.group_user_id = group_user_id;
    }

    public GroupObject getGroup() {
        return group;
    }

    public void setGroup(GroupObject group) {
        this.group = group;
    }

    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject user) {
        this.user = user;
    }

    public static GroupUserObject parseJsonToObject(JSONObject jsonObject) {
        Gson gson = new GsonBuilder().create();
        GroupUserObject groupUserObject = gson.fromJson(jsonObject.toString(), GroupUserObject.class);
        try {
            groupUserObject.setGroup(GroupObject.parseJsonToObject(jsonObject.getJSONObject("group")));
            groupUserObject.setUser(UserObject.parseJsonToObject(jsonObject.getJSONObject("user")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return groupUserObject;
    }
}
