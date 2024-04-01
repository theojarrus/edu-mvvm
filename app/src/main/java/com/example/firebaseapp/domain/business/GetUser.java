package com.example.firebaseapp.domain.business;

import com.example.firebaseapp.data.UserRepository;
import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.domain.util.ErrorListener;
import com.example.firebaseapp.domain.util.SuccessListener;

public class GetUser {

    public static void getUserById(String uid, SuccessListener<User> successListener, ErrorListener errorListener) {
        UserRepository.getUserById(uid, successListener, errorListener);
    }
}
