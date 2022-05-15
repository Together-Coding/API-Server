package com.example.authserver.domain;

import com.example.authserver.domain.base.AuditingCreateUpdateEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "courses")
public class Course extends AuditingCreateUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @JsonIgnore
    private String password;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private User user;

    private int accessible;

    private int active;

    @Builder
    private Course(String name, User user, String description ,String password){
        this.name = name;
        this.user = user;
        this.description = description;
        this.password = password;
        this.accessible = 1;
        this.active = 1;
    }

    public void updateAccessible(int status){this.accessible = status;}

    public void updateActive(int status){this.active = status;}

    public void updatePw(String password){
        this.password = password;
    }

    public void updateUser(User user) {this.user = user;}
}
