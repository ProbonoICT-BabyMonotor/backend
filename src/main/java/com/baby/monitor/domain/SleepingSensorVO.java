package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sleeping_sensor") // 테이블 이름을 명시해줄 수 있습니다.
public class SleepingSensorVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sleepingSensorNumber;
    private int sleepingNumber;
    private float temperatureAver;
    private float temperatureMin;
    private float temperatureMax;
    private int heartRateAver;
    private int heartRateMin;
    private int heartRateMax;
    private boolean breath; // True : 이상 없음, False : 이상 있음
    private float weight;
}
