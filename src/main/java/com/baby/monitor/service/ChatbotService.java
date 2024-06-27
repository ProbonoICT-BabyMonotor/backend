package com.baby.monitor.service;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.persistance.Stm32Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final Stm32Repository stm32JPA;

    public void RequestToStm(int memberNumber, String command) {
        RestTemplate restTemplate = new RestTemplate();
        // 랜덤으로 세계 맥주에 대한 정보를 주는 url
        String url = stm32JPA.findByMemberNumber(memberNumber).getStm32Ip()+"/"+command;

        ResponseEntity<RestResponse> response = restTemplate.getForEntity(url, RestResponse.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("침대 연결 상태가 좋지 않아요.");
        }
    }
}
