package com.example.authserver.service;

import com.example.authserver.domain.User;

public interface UserService {

    void signup(String email, String password, String name);

    User getUser(Long userId);

    User getUserByEmail(String email);
}
