package com.baby.monitor.domain;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "sleeping_sensor") // 테이블 이름을 명시해줄 수 있습니다.
public class SleepingSensorVO {
    private int sleepingSensorNumber;
    private int sleepingNumber;
    private float temperatureAver;
    private float temperatureMin;
    private float temperatureMax;
    private int heartRateAver;
    private int heartRateMin;
    private int heartRateMax;
    private int breath;
    private int weight;
}
