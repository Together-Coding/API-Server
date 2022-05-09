package com.example.authserver.domain;


import com.example.authserver.domain.base.AuditingCreateEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="supported_languages")
public class SupportedLanguage extends AuditingCreateEntity {

    @Id
    String name;

    Long order;

}
