package com.baby.monitor.service;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.domain.ActingVO;
import com.baby.monitor.domain.SleepingVO;
import com.baby.monitor.persistance.ActingRepository;
import com.baby.monitor.persistance.SleepingRepository;
import com.baby.monitor.persistance.Stm32Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatbotService {
    private final ActingRepository actingJPA;
    private final Stm32Repository stm32JPA;
    private final SleepingRepository sleepingJPA;

    public ActingVO RequestToStm(int memberNumber, String command) {
        // RestTemplate 생성 및 타임아웃 설정
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(6000);  // 연결 타임아웃 설정 (4초)
        factory.setReadTimeout(6000);     // 읽기 타임아웃 설정 (4초)
        restTemplate.setRequestFactory(factory);

        // 현재 STM32 연결 가능한 URL
        String url = "http://192.168.0.9:333/" + command;

        try {
            ActingVO nowActingVO = null;

            if(command.substring(command.length() - 3).equals("off") && nowActingVO != null){
                nowActingVO.setActingEndTime(LocalDateTime.now());
                // actingJPA.save(nowActingVO);
            }

            // STM32로 요청
            ResponseEntity<RestResponse> response = restTemplate.getForEntity(url, RestResponse.class);

            log.info(response.getStatusCode().toString());
            // 상태 코드 확인
            if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
                throw new IllegalStateException("침대 연결 상태가 좋지 않아요. 잠시후 다시 실행해보세요");
            }

            // 실제로 통신이 완료되어 동작 중
            ActingVO acting = new ActingVO(memberNumber, command.substring(0, command.length() - 3));
            // addActing(acting);

            return acting;
        } catch (Exception e) {
            log.info(Arrays.toString(e.getStackTrace()));
            // 타임아웃 또는 기타 예외 발생 시 처리
            throw new IllegalStateException(e.toString());
        }
    }


    // 기능 실행 도중에, 중단하기 위한 임시 요청
    public Boolean RequestTempToStm(int memberNumber) throws InterruptedException {
        // RestTemplate 생성 및 타임아웃 설정
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(100);  // 연결 타임아웃 설정 (5초)
        factory.setReadTimeout(100);     // 읽기 타임아웃 설정 (5초)
        restTemplate.setRequestFactory(factory);

        // 현재 STM32 연결 가능한 URL
        String url = "http://192.168.0.9:333/done";

        try {
            ResponseEntity<RestResponse> response = restTemplate.getForEntity(url, RestResponse.class);

            response.getStatusCode(); // 여기서 오류 발생 예정
        } catch (Exception e) {
            // 타임아웃 또는 기타 예외 발생 시 처리
            Thread.sleep(1000);
            return Boolean.TRUE;
        }
        return Boolean.TRUE;
    }

    /**
     * 회원 번호로 현재 동작 파악하기
     *
     * @Return String : rest / backdraft / ...
     */
    public String searchNowActing(int memberNumber) {
        ActingVO acting = actingJPA.findFirstByMemberNumberOrderByActingNumberDesc(memberNumber);
        // TODO STM32 IP 조회해서 연결된 침대가 없음 → 침대 연결 프로세스 진행해도 좋을 듯

        // 기존에 동작이 없었다는 뜻
        if (acting == null || acting.getActingEndTime().isBefore(LocalDateTime.now())) {
            return ActingVO.changeToStringInNowActing("rest"); // 침대가 휴식 중 이라는 뜻
        } else {
            return ActingVO.changeToStringInNowActing(acting.getActingName());
        }
    }

    /**
     * 회원 번호로 현재 동작 객체 파악하기
     *
     * @Return ActingVO
     */
    public ActingVO searchNowActingVO(int memberNumber) {
        ActingVO acting = actingJPA.findFirstByMemberNumberOrderByActingNumberDesc(memberNumber);
        // TODO STM32 IP 조회해서 연결된 침대가 없음 → 침대 연결 프로세스 진행해도 좋을 듯

        // 기존에 동작이 없었다는 뜻
        if (acting == null || acting.getActingEndTime().isBefore(LocalDateTime.now())) {
            return null; // 침대가 휴식 중 이라는 뜻
        } else {
            return acting;
        }
    }

    /**
     * 동작 추가하기
     * @Memo : 이때 취침 중인 아기 이력 조회하기
     */
    public ActingVO addActing(ActingVO acting) {
        SleepingVO sleep1 = sleepingJPA.findFirstBySleepingEndTimeIsNullAndMemberNumberOrderBySleepingNumberDesc(acting.getMemberNumber());

        // 기존에 취침 하고 있는 아기가 있다면
        if (sleep1 != null) {
            acting.setSleepingNumber(sleep1.getSleepingNumber());
        } 
        // 없다면 새로 만들기
        else {
            SleepingVO sleepingVO = new SleepingVO(acting.getMemberNumber());
            acting.setSleepingNumber(sleepingVO.getSleepingNumber());
        }

        return actingJPA.save(acting);
    }

    public ActingVO backBaby(int memberNumber){
        return RequestToStm(memberNumber, "backdraft/on");
    }
}
