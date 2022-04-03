package com.example.authserver.repository;

import com.example.authserver.domain.Course;
import com.example.authserver.domain.Participant;
import com.example.authserver.domain.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Participant> getAllByCourse_Id(Long courseId);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Participant> getAllByCourse_IdAndRole(Long courseId, Role role);

    @EntityGraph(attributePaths = {"course"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Participant> getParticipantsByUser_IdAndRoleOrderByIdDesc(Long userId, Role role);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    Participant getParticipantById(Long participantId);
}
