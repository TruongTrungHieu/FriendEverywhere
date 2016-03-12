package com.fithou.friendeverywhere.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class MessageObject implements Serializable {

    private String message_id;
    private String content;
    private Date created_date;
    private int error;
    private int seen;
    private int attachment;
    private String attachment_url;
    private int attachment_type;
    private UserObject userObject;
    private GroupObject groupObject;

    public MessageObject() {

    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getAttachment() {
        return attachment;
    }

    public void setAttachment(int attachment) {
        this.attachment = attachment;
    }

    public String getAttachment_url() {
        return attachment_url;
    }

    public void setAttachment_url(String attachment_url) {
        this.attachment_url = attachment_url;
    }

    public int getAttachment_type() {
        return attachment_type;
    }

    public void setAttachment_type(int attachment_type) {
        this.attachment_type = attachment_type;
    }

    public UserObject getUserObject() {
        return userObject;
    }

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public GroupObject getGroupObject() {
        return groupObject;
    }

    public void setGroupObject(GroupObject groupObject) {
        this.groupObject = groupObject;
    }

    public static MessageObject parseJsonToObject(JSONObject jsonObject) {
        Gson gson = new GsonBuilder().create();
        MessageObject messageObject = gson.fromJson(jsonObject.toString(), MessageObject.class);
        try {
            messageObject.setUserObject(UserObject.parseJsonToObject(jsonObject.getJSONObject("user")));
            messageObject.setGroupObject(GroupObject.parseJsonToObject(jsonObject.getJSONObject("group")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messageObject;
    }

}
