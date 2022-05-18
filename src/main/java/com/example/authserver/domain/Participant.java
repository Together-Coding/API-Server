package com.example.authserver.domain;

import com.example.authserver.domain.base.AuditingCreateEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "participants",
        uniqueConstraints = {@UniqueConstraint(
                name = "part_uk",
                columnNames = {"user_Id", "course_Id"}
        )})
public class Participant extends AuditingCreateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private ParticipantRole role = ParticipantRole.STUDENT;

    public void updateUser(User user){
        this.user = user;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    @Builder
    private Participant(Course course, User user, ParticipantRole role){
        this.nickname = "";
        this.course = course;
        this.user = user;
        this.role = role;
    }
}



