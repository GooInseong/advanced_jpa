package com.springboot.advanced_jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {

    /*JPA 에서 Audit 이란 '감시하다' 라는 뜻으로, 각 데이터마다 '누가','언제' 데이터를 생성했고
    * 변경했는지 감시한다는 의미로 사용된다. 현재 엔티티클래스에 공통적으로 들어가는 생성일자와 변경일자 필드
    * 같은 것들이다. 이러한 핃드들은 매번 엔티티를 생성하거나 변경할 때마다 값을 주입해야 하는 번거로움이 있다.
    * 이같은 번거로움을 해소하기 위해 Spring Data JPA 는 JPA Auditing 을 통해 이를 해결한다.
    *
    * 본 파일은 JPA Auditing 을 config 하는 클래스이다.*/
}
