package com.example.authserver.service;

import com.example.authserver.domain.Course;
import com.example.authserver.domain.Participant;
import com.example.authserver.domain.Role;
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

    private ParticipantRepository participantRepository;

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
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build());
        }
        return dto;
    }

    @Transactional
    public void getCoursesByStudent(Long userId){
        List<Course> courses = participantRepository.getAllByUser_IdAndRoleOrderByIdDesc(userId, Role.STUDENT);

    }

    @Transactional
    public void getCoursesByTeacher(Long userId){
        List<Course> courses = participantRepository.getAllByUser_IdAndRoleOrderByIdDesc(userId, Role.TEACHER);
    }


}
