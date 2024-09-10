package com.ishan.model;

import org.springframework.stereotype.Component;


public class User {

    private String userId;
    private String userName;

    public User(){

    }
    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "USER ID: " + userId + "\nUSER NAME: " + userName + "\n";
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
