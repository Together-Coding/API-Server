package com.example.authserver.service;

import com.example.authserver.domain.User;
import com.example.authserver.dto.UserDTO;

public interface UserService {

    UserDTO.MyInfo getUserInfo(Long userId);

    void signup(String email, String password, String name);

    User getUser(Long userId);

    User getUserByEmail(String email);

    void updateUser(Long userId, String name);

    void updatePassword(Long userId, String oldPw, String newPw, String checkPw);
}
