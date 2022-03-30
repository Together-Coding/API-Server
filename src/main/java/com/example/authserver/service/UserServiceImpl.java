package com.example.authserver.service;

import com.example.authserver.domain.User;
import com.example.authserver.domain.UserRole;
import com.example.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signup(String email, String password, String name){
        if (userRepository.existsByEmail(email)) {
            log.warn("Email already exists input: {}", email);
            throw new IllegalArgumentException("Email already exists input: " + email);
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .fromSocial(false)
                .build();

        user.addUserRole(UserRole.USER);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId, String name){
        User user = this.getUser(userId);
        user.updateName(name);
    }

    @Transactional
    public User getUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("can not find user. input userId: " + userId));
    }


}
