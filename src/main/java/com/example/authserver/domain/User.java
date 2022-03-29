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
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 80)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    private boolean fromSocial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

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
