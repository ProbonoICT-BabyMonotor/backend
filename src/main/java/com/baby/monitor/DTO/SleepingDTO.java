package com.baby.monitor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Setter
@Getter
public class SleepingDTO {
    private int sleepingNumber;
    private String sleepingStatus;
    private String sleepingDate;
    private String sleepingTime;
    private String sleepingEndTime;

    public SleepingDTO(int sleepingNumber, LocalDateTime sleepingTime, LocalDateTime sleepingEndTime){
        this.sleepingNumber = sleepingNumber;
        this.sleepingTime = changeTimeToString(sleepingTime);
        this.sleepingDate = this.sleepingTime.substring(0, 8) + " 취침";
        if (sleepingEndTime == null){
            this.sleepingStatus = "취침중";
            this.sleepingEndTime = "";
        } else {
            this.sleepingStatus = "기상완료";
            this.sleepingEndTime = changeTimeToString(sleepingEndTime);
        }
    }

    private String changeTimeToString(LocalDateTime s){
        // 포맷터 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd(E) HH:mm", Locale.KOREAN);

        // 문자열로 포맷팅
        return s.format(formatter);

    }
}
