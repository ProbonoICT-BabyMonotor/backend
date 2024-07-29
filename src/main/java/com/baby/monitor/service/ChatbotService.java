package com.baby.monitor.service;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.domain.ActingVO;
import com.baby.monitor.persistance.Stm32Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final Stm32Repository stm32JPA;
    private final ActingService actingService;

    public ActingVO RequestToStm(int memberNumber, String command) {
        // RestTemplate 생성 및 타임아웃 설정
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);  // 연결 타임아웃 설정 (5초)
        factory.setReadTimeout(5000);     // 읽기 타임아웃 설정 (5초)
        restTemplate.setRequestFactory(factory);

        // 현재 STM32 연결 가능한 URL
        String url = stm32JPA.findByMemberNumber(memberNumber).getStm32Ip() + "/" + command;

        try {
            ResponseEntity<RestResponse> response = restTemplate.getForEntity(url, RestResponse.class);

            // 상태 코드 확인
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new IllegalStateException("침대 연결 상태가 좋지 않아요.");
            }

            // 실제로 통신이 완료되어 동작 중
            ActingVO acting = new ActingVO(memberNumber, command);
            actingService.addActing(acting);

            return acting;


        } catch (Exception e) {
            // 타임아웃 또는 기타 예외 발생 시 처리
            throw new IllegalStateException("침대 연결 상태가 좋지 않아요.");
        }
    }
}
