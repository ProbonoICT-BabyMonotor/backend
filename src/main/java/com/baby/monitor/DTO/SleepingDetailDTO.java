package com.baby.monitor.DTO;

import com.baby.monitor.domain.SleepingVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class SleepingDetailDTO {
    private int sleepingNumber;
    private String sleepingDate;
    private String sleepingStatus;
    private String sleepingTime;
    private String sleepingEndTime;
    private SensorDTO sleepingSensor;
    private ArrayList<ActingDTO> sleepingAction;

    public SleepingDetailDTO(SleepingVO s) {
        this.sleepingNumber = s.getSleepingNumber();
        this.sleepingDate = changeTimeToString2(s.getSleepingTime());
        this.sleepingTime = changeTimeToString(s.getSleepingTime());
        this.sleepingEndTime = changeTimeToString(s.getSleepingEndTime());
        if (Objects.equals(s.getSleepingStatus(), "취침중")){
            this.sleepingStatus = "😴 취침 중";
        } else {
            this.sleepingStatus = "😊 기상 완료";
        }
    }

    private String changeTimeToString(LocalDateTime s){
        // 문자열로 포맷팅
        return s.format(DateTimeFormatter.ofPattern("MM/dd(E) HH시 mm분", Locale.KOREAN));
    }

    private String changeTimeToString2(LocalDateTime s){
        // 문자열로 포맷팅
        return s.format(DateTimeFormatter.ofPattern("MM / dd (E) 취침", Locale.KOREAN));
    }
}
