package com.itcast.education.model.user;

public class User {
    private String userRealName;
    public User(String loginId) {
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
}
