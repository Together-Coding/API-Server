package com.example.authserver.controller;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    @Getter
    public static class Signup{

        @NotBlank(message = "이메일은 필수 값 입니다.")
        @Email(message = "이메일 형식이 아닙니다.")
        private String email;

        @Pattern(regexp="[a-zA-Z1-9]{8,20}",
                message = "비밀번호는 영어와 숫자로 포함해서 8~20자리 이내로 입력해주세요.")
        private String password;

        @NotBlank(message = "이름은 필수 값 입니다.")
        private String name;
    }
}
