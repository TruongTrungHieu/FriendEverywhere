package com.fithou.friendeverywhere.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by TRUNGHIEU on 12/3/2016.
 */
public class FriendObject implements Serializable {

    @SerializedName("friend_pk")
    private String friend_pk;
    @SerializedName("friend_status")
    private int friend_status;
    @SerializedName("me_id")
    private String me_id;
    private UserObject friend_object;

    public FriendObject() {

    }

    public String getFriend_pk() {
        return friend_pk;
    }

    public void setFriend_pk(String friend_pk) {
        this.friend_pk = friend_pk;
    }

    public int getFriend_status() {
        return friend_status;
    }

    public void setFriend_status(int friend_status) {
        this.friend_status = friend_status;
    }

    public String getMe_id() {
        return me_id;
    }

    public void setMe_id(String me_id) {
        this.me_id = me_id;
    }

    public UserObject getFriend_object() {
        return friend_object;
    }

    public void setFriend_object(UserObject friend_object) {
        this.friend_object = friend_object;
    }

    public static FriendObject parseJsonToObject(JSONObject jsonObject) {
        Gson gson = new GsonBuilder().create();
        FriendObject friendObject = gson.fromJson(jsonObject.toString(), FriendObject.class);
        try {
            friendObject.setFriend_object(UserObject.parseJsonToObject(jsonObject.getJSONObject("friend")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendObject;
    }
}
