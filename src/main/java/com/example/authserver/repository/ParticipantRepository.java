package com.example.authserver.repository;

import com.example.authserver.domain.Participant;
import com.example.authserver.domain.ParticipantRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Participant> getAllByCourse_Id(Long courseId);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Participant> getAllByCourse_IdAndRole(Long courseId, ParticipantRole role);

    @EntityGraph(attributePaths = {"course"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Participant> getParticipantsByUser_IdAndRoleOrderByIdDesc(Long userId, ParticipantRole role);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    Participant getParticipantById(Long participantId);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    Participant getParticipantByCourse_IdAndUser_Id(Long courseId, Long userId);

    Participant findByCourse_IdAndRole(Long courseId, ParticipantRole participantRole);
}
