package com.baby.monitor.service;

import com.baby.monitor.domain.ActingVO;
import com.baby.monitor.persistance.ActingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ActingService {
    private final ActingRepository actingJPA;

    private Map<String, String> actingMap = new HashMap<>();

    public ActingService(ActingRepository actingJPA){
        this.actingJPA = actingJPA;
        this.actingMap.put("rest", "동작 대기 중");
        this.actingMap.put("backdraft", "역류 방지 기능 동작 중");
        this.actingMap.put("burp", "트름 유도 기능 동작 중");
        this.actingMap.put("swing", "스윙 기능 동작 중");
        this.actingMap.put("spin", "뒤집기 기능 동작 중");
        this.actingMap.put("fix", "침대 고정 기능 동작 중");
    }

    /**
     * 회원 번호로 현재 동작 파악하기
     */
    public String searchNowActing(int memberNumber){
        ActingVO acting = actingJPA.findByMemberNumber(memberNumber);

        // TODO STM32 IP 조회해서 연결된 침대가 없음 → 침대 연결 프로세스 진행해도 좋을 듯

        // 기존에 동작이 없었다는 뜻
        if (acting == null){
            return actingMap.get("rest"); // 침대가 휴식 중 이라는 뜻
        } else{
            // 이미 끝난 작업이라면?
            if (acting.getActingEndTime().isBefore(LocalDateTime.now())){
                return actingMap.get("rest"); // 침대가 휴식 중 이라는 뜻
            } else {
                return actingMap.get(acting.getActingName());
            }
        }
    }

    /**
     * 동작 추가하기
     */
    public ActingVO addActing(ActingVO acting){
        return actingJPA.save(acting);
    }
}
