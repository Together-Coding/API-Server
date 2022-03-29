package com.example.authserver.repository;

import com.example.authserver.domain.CourseParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseParticipantRepository extends JpaRepository<CourseParticipant, Long> {
}
