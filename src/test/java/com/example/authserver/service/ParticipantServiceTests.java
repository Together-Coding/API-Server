package com.example.authserver.service;

import com.example.authserver.domain.Participant;
import com.example.authserver.dto.ParticipantDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")
public class ParticipantServiceTests {

    @Autowired
    private ParticipantService participantService;

    @DisplayName("course 참여자 목록 가져오기")
    @Test
    void Should_GetParticipantList() {
        List<ParticipantDTO> dtos = participantService.getParticipantList(1L);
        for (int i = 1; i <= 10; i++) {
            assertEquals("student" + i + "@naver.com", dtos.get(i).getEmail());
        }
    }

    @Test
    void Should_GetCourseIdList(){
        List<Participant> participants = participantService.getCoursesThatIamStudentByUserId(1L);
        for (Participant p : participants){
            System.out.println(p.getCourse().getId());
        }
    }
}
