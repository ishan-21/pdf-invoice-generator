package com.ishan.service;

import com.ishan.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("singleton")
public class UserService {

    public User findUserById(String userId){
        String randomUserName = UUID.randomUUID().toString();
        return new User(userId, randomUserName);
    }
}
