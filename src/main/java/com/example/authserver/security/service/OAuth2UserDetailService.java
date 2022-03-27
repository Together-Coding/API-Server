package com.example.authserver.security.service;

import com.example.authserver.domain.User;
import com.example.authserver.domain.UserRole;
import com.example.authserver.repository.UserRepository;
import com.example.authserver.security.dto.AuthUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OAuth2UserDetailService extends DefaultOAuth2UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("===================================");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v);
        });

        String email = null;

        //Todo: 확인 요망
        if (clientName.equals("Github")) {
            email = oAuth2User.getAttribute("email");
        }
        log.info("EMAIL: " + email);

        User user = saveSocialMember(email);

        return new AuthUserDTO(
                user.getEmail(),
                user.getPassword(),
                user.isFromSocial(),
                user.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                user.getId(),
                oAuth2User.getAttributes()
        );
    }

    private User saveSocialMember(String email) {
        Optional<User> result = repository.findByEmailAndSocial(email, true);

        if (result.isPresent()) {
            return result.get();
        }


        User user = User.builder()
                .email(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();

        user.addUserRole(UserRole.USER);
        repository.save(user);
        return user;
    }
}

