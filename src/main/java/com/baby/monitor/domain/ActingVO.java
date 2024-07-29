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
}
