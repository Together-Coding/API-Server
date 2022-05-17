package com.example.authserver.repository;

import com.example.authserver.domain.LessonFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonFileRepository extends JpaRepository<LessonFile, Long> {
}
