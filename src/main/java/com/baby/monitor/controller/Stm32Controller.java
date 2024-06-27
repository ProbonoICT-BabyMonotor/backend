package com.baby.monitor.controller;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.service.Stm32Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stm32") // API의 기본 경로 설정
public class Stm32Controller {

    private final Stm32Service stm32Service;

    RestResponse<Object> restResponse = new RestResponse<>();

    private static final Logger logger = LoggerFactory.getLogger(Stm32Controller.class);

    @GetMapping("/nowip")
    public ResponseEntity getNowIP(@RequestParam int memberNumber) throws Exception {
        logger.info("[STM32 IP 조회] 회원 번호 : " + memberNumber);

        // 성공적으로 조회 했을 때
        try{
            String MyIP = stm32Service.findMyIp(memberNumber);
            Map<String, String> myIPMap = new HashMap<>();
            myIPMap.put("IP", MyIP);
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("내 STM32 보드 IP 조회 완료")
                    .data(myIPMap)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // ID 또는 비밀번호가 일치하지 않음, IllegalArgumentException 발생
        catch (IllegalArgumentException e){
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }
}
