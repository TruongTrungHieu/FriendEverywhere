package com.fithou.friendeverywhere.object;

import java.io.Serializable;

public class MessageObject implements Serializable {

    private String message_id;
    private String content;

    private String send_user_id;
    private String send_avatar;
    private String send_name;

    public MessageObject() {

    }

    public MessageObject( String content, String send_user_id, String send_avatar, String send_name) {
        this.message_id = (System.currentTimeMillis() / 1000L) + "";
        this.content = content;
        this.send_user_id = send_user_id;
        this.send_avatar = send_avatar;
        this.send_name = send_name;
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

    public String getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(String send_user_id) {
        this.send_user_id = send_user_id;
    }

    public String getSend_avatar() {
        return send_avatar;
    }

    public void setSend_avatar(String send_avatar) {
        this.send_avatar = send_avatar;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }
}
