package com.springboot.advanced_jpa.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)  //엔티티를 데이터베이스에 적용하기 전후로 콜백을 요청
@Getter
@Setter
@MappedSuperclass   //JPA 의 엔티티 클래스가 상속받을 경우 자식 클래스에게 매핑 정보 전달
public class BaseEntity {

    @CreatedDate    //데이터 생성 날짜를 자동으로 주입
    @Column(updatable = false)  
    private LocalDateTime createdAt;

    @LastModifiedDate   //데이터 수정 날짜를 자동으로 주입
    private LocalDateTime updatedAt;
}
