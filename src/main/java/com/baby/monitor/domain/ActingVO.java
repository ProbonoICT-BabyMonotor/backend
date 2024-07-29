package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "acting") // 테이블 이름을 명시해줄 수 있습니다.
public class ActingVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actingNumber;
    private int sleepingNumber;
    private int memberNumber;
    private String actingName;
    private LocalDateTime actingTime;
    private LocalDateTime actingEndTime;
}
