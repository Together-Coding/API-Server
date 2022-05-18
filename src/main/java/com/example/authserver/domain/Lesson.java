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
@Table(name = "lessons")
public class Lesson extends AuditingCreateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_file_id")
    private LessonFile lessonFile;

    @Column(nullable = false)
    private Long lang_image_id;

    @Builder
    private Lesson(String name, String description, Course course) {
        this.name = name;
        this.description = description;
        this.course = course;
        this.active = 1;
        this.lang_image_id = 1L;
    }

    public void updateLessonFile(LessonFile lessonFile) {
        this.lessonFile = lessonFile;
    }

    public void updateActive(int status){this.active = status;}

    public void updateName(String name){
        this.name = name;
    }

    public void updateDescription(String description){
        this.description = description;
    }

}
