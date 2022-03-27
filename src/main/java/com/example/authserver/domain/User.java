package com.example.authserver.domain;


import com.example.authserver.domain.base.AuditingCreateEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "roleSet")
@Table(name = "users")
public class User extends AuditingCreateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    @Length(min = 1, max = 60, message = "사이즈를 확인하세요.")
    @NotBlank(message = "이메일은 필수 값 입니다.")
    @Email
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 80)
    @NotBlank(message = "비밀번호는 필수 값 입니다.")
    private String password;

    @Column(nullable = false, length = 20)
    @Length(min = 1, max = 20, message = "사이즈를 확인하세요.")
    @NotBlank(message = "이름은 필수 값 입니다.")
    private String name;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserRole> roleSet = new HashSet<>();

    public void updateName(String name){
        this.name = name;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void addUserRole(UserRole userRole){
        roleSet.add(userRole);
    }

    @Builder
    private User(String email, String name, String password, boolean fromSocial){
        this.email = email;
        this.name = name;
        this.password = password;
        this.fromSocial = fromSocial;
    }
}
