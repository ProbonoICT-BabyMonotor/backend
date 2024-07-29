package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sleeping") // 테이블 이름을 명시해줄 수 있습니다.
public class SleepingVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sleepingNumber;
    private int memberNumber;
    private LocalDateTime sleepingTime;
    private LocalDateTime sleepingEndTime;
    private String sleepingStatus;
    private Boolean sensorError;
}
