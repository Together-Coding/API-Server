package com.example.authserver.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {

    @Getter
    public static class Signup {

        @NotBlank(message = "이메일은 필수 값 입니다.")
        @Email(message = "이메일 형식이 아닙니다.")
        private String email;

        private String password;

        @NotBlank(message = "이름은 필수 값 입니다.")
        private String name;
    }

    @Getter
    public static class Update {

        private String name;

    }

    @Getter
    public static class Password {

        private String currentPassword;

        private String newPassword;

        private String checkPassword;
    }
}
