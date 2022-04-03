package com.example.authserver.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParticipantDTO {

    Long id;

    String email;

    String name;
}
