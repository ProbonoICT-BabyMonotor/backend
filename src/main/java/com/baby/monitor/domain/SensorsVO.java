package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sensors") // 테이블 이름을 명시해줄 수 있습니다.
public class SensorsVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sensorNumber;
    private int memberNumber;
    private int sleepingNumber;
    private float sensorTemperature;
    private int sensorHeartRate;
    private boolean sensorBreath;
    private float sensorWight;
    private LocalDateTime sensorDate;
}
