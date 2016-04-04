package com.fithou.friendeverywhere.ultis.newmessage;

import com.fithou.friendeverywhere.object.UserObject;

public class UserTempObject {

    private boolean isHeader;
    private UserObject userObject;
    private boolean isSelected;

    public UserTempObject() {
        this.isHeader = false;
        this.isSelected = false;
    }

    public UserTempObject(UserObject userObject) {
        this.userObject = userObject;
        this.isHeader = false;
        this.isSelected = false;
    }

    public void setIsHeader() {
        this.isHeader = true;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setIsSelected() {
        this.isSelected = true;
    }

    public void removeSelected() {
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public UserObject getUserObject() {
        return userObject;
    }

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }
}
