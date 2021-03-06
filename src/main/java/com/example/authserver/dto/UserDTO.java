package com.example.authserver.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDTO {

    @Getter
    @Builder
    public static class MyInfo {

        private Long userId;

        private String name;

        private String email;
    }

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

        @NotNull
        private String name;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Nickname {

        private Long courseId;

        private String nickname;
    }

    @Getter
    public static class Password {

        private String currentPassword;

        private String newPassword;

        private String checkPassword;
    }
}
