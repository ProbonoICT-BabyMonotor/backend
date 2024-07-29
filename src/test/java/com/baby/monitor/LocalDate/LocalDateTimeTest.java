package com.baby.monitor.LocalDate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class LocalDateTimeTest {


    /**
     * LocalDateTime을 Formatter로 변경함
     * @Return : 07/29(월) 14:30
     */
    @Test
    public void LocalDateTimeTest1(){
        // 현재 시간 가져오기
        LocalDateTime localDateTime = LocalDateTime.now();

        // 포맷터 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd(E) HH:mm", Locale.KOREAN);

        // 문자열로 포맷팅
        String formattedDate = localDateTime.format(formatter);

        // 결과 출력
        log.info("Formatted Date: " + formattedDate);
    }

}
