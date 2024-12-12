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
    private float sensorWeight;
    private LocalDateTime sensorDate;
    private float sensorAreaTemp; // 주변 온도
    private float sensorAreaHumi; // 주변 습도


    public SensorsVO(int memberNumber, float sensorTemperature, float sensorAreaTemp, float sensorAreaHumi) {
        this.memberNumber = memberNumber;
        this.sensorTemperature = sensorTemperature;
        this.sensorAreaTemp = sensorAreaTemp;
        this.sensorAreaHumi = sensorAreaHumi;

        // TODO 여기서는 주작이므로;//  추후 수정할 것!!
        this.sensorHeartRate = 80;
        this.sensorBreath = true;
        this.sensorWeight = 0;
    }

    public SensorsVO() {

    }
}
