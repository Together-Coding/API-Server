package com.example.authserver.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Builder
@ToString
public class JwtDTO {

    private boolean isValid;

    private Long userId;

    private String email;

    private Date issuedAt;

    private Date expiredAt;

    public void updateUserId(Long userId){
        this.userId = userId;
    }
}
