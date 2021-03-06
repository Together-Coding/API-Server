package com.example.authserver.service;

import com.example.authserver.domain.Participant;
import com.example.authserver.domain.ParticipantRole;
import com.example.authserver.domain.User;
import com.example.authserver.dto.ParticipantDTO;
import com.example.authserver.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    /**
     * 코스Id로 코스에 참가하는 사람들 목록 반환
     */
    @Override
    @Transactional
    public List<ParticipantDTO> getParticipantList(Long courseId) {
        List<Participant> participants = participantRepository.getAllByCourse_Id(courseId);
        List<ParticipantDTO> dto = new ArrayList<>();
        for (Participant part : participants) {
            User user = part.getUser();
            dto.add(ParticipantDTO.builder()
                    .userId(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build());
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Participant> getCoursesThatIamStudentByUserId(Long userId){
        return participantRepository.getParticipantsByUser_IdAndRoleOrderByIdDesc(userId, ParticipantRole.STUDENT);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Participant> getCoursesThatIamTeacherByUserId(Long userId){
        return participantRepository.getParticipantsByUser_IdAndRoleOrderByIdDesc(userId, ParticipantRole.TEACHER);
    }

    @Override
    @Transactional
    public void changeNickname(Long userId, Long courseId, String nickName){
        Participant participant = participantRepository.getParticipantByCourse_IdAndUser_Id(courseId, userId);
        participant.updateNickname(nickName);
    }

}
