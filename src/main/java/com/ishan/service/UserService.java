package com.ishan.service;

import com.ishan.model.User;

import java.util.UUID;

public class UserService {

    public User findUserById(String userId ){
        String randomUserName = UUID.randomUUID().toString();
        return new User(userId, randomUserName);
    }
}
