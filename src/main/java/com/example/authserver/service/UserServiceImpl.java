package com.example.authserver.service;

import com.example.authserver.domain.User;
import com.example.authserver.domain.UserRole;
import com.example.authserver.exception.custom.AlreadyExistsException;
import com.example.authserver.exception.custom.InvalidArgsException;
import com.example.authserver.exception.custom.NotFoundException;
import com.example.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
            throw new AlreadyExistsException("Email already exists input: " + email);
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

    @Override
    @Transactional
    public void updateUser(Long userId, String name){
        User user = this.getUser(userId);
        user.updateName(name);
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, String oldPw, String newPw, String checkPw) {
        User user = this.getUser(userId);

        if (!passwordEncoder.matches(oldPw, user.getPassword())) {
            log.warn("이전 비밀번호와 다릅니다.");
            throw new InvalidArgsException("이전 비밀번호와 다릅니다.");
        }

        if (!newPw.equals(checkPw)) {
            log.warn("확인 비밀번호가 새 비밀번호와 다릅니다.");
            throw new InvalidArgsException("확인 비밀번호가 새 비밀번호와 다릅니다.");
        }

        user.updatePassword(passwordEncoder.encode(newPw));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public User getUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("can not find user. input userId: " + userId));
    }

    @Override
    @Transactional
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("can not find user. input email: " + email));
    }

}
