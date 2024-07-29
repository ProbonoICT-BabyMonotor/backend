package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    public ActingVO(int memberNumber, String actingName){
        this.memberNumber = memberNumber;
        this.actingName = actingName;
        this.actingTime = LocalDateTime.now();
        this.actingEndTime = calculateEndTime(actingName, actingTime);
    }

    public ActingVO() {

    }

    /**
     * Map을 이용한 종료 시간 계산.
     * TODO 추후 시간이 정해지면 수정해야 함.
     * @return actingEndTime
     */
    public LocalDateTime calculateEndTime(String actingName, LocalDateTime actingTime){
        // 이름 : Seconds
        Map<String, Integer> EndTimeMap = new HashMap<>();
        EndTimeMap.put("backdraft", 1200); // 역류
        EndTimeMap.put("burp", 1200); // 트름
        EndTimeMap.put("swing", 1200); // 스윙
        EndTimeMap.put("spin", 30); // 뒤집기
        EndTimeMap.put("fix", 20); // 침대 고정

        return actingTime.plusSeconds(EndTimeMap.get(actingName));
    }

    // 현재 동작 여부 조회 시
    public static String changeToStringInNowActing(String englishName){
        Map<String, String> actingMap = new HashMap<>();

        actingMap.put("rest", "동작 대기 중");
        actingMap.put("backdraft", "역류 방지 기능 동작 중");
        actingMap.put("burp", "트름 유도 기능 동작 중");
        actingMap.put("swing", "스윙 기능 동작 중");
        actingMap.put("spin", "뒤집기 기능 동작 중");
        actingMap.put("fix", "침대 고정 기능 동작 중");

        return actingMap.get(englishName);
    }

    // 취침 상세 여부 조회 시
    public static String changeToStringInSleeping(String englishName){
        Map<String, String> actingMap = new HashMap<>();

        actingMap.put("backdraft", "역류 방지 기능 수행");
        actingMap.put("burp", "트름 유도 기능 수행");
        actingMap.put("swing", "스윙 기능 수행");
        actingMap.put("spin", "뒤집기 기능 수행");
        actingMap.put("fix", "침대 고정 기능 수헹");

        return actingMap.get(englishName);
    }
}
