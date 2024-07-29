package com.baby.monitor.DTO;

import com.baby.monitor.domain.ActingVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
public class ActingDTO {
    private int actingNumber;
    private String actingTime;
    private String actingName;

    public ActingDTO(ActingVO a) {
        this.actingNumber = a.getActingNumber();
        this.actingTime = changeTimeToString(a.getActingTime());
        this.actingName = ActingVO.changeToStringInSleeping(a.getActingName());
    }

    private String changeTimeToString(LocalDateTime s){
        // 문자열로 포맷팅
        return s.format(DateTimeFormatter.ofPattern("MM/dd(E) HH시 mm분", Locale.KOREAN));
    }
}
