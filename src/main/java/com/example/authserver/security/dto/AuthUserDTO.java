package com.example.authserver.security.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Getter
public class AuthUserDTO extends User implements OAuth2User {

    private String email;

    private String password;

    private Long id;

    private boolean fromSocial;

    private Map<String, Object> attr;

    private String name;

    public AuthUserDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities,
            Long id,
            Map<String, Object> attr) {
        this(username, password, fromSocial, authorities, id);
        this.attr = attr;
    }


    public AuthUserDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities,
            Long id) {

        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
        this.id = id;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}