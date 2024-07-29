package com.baby.monitor.controller;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.domain.Stm32VO;
import com.baby.monitor.service.Stm32Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stm32") // API의 기본 경로 설정
public class Stm32Controller {

    private final Stm32Service stm32Service;

    RestResponse<Object> restResponse = new RestResponse<>();

    @GetMapping("/nowip")
    public ResponseEntity getNowIP(@RequestParam int memberNumber) throws Exception {
        log.info("[STM32 IP 조회] 회원 번호 : " + memberNumber);

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

    @PostMapping("/nowip")
    public ResponseEntity setNowIP(@RequestParam Stm32VO stm32) throws Exception {
        log.info("[STM32 IP 수정] 회원 번호 : " + stm32.getMemberNumber());

        // 성공적으로 조회 했을 때
        try{
            Stm32VO myStm32 = stm32Service.setMyIP(stm32);
            Map<String, String> myIPMap = new HashMap<>();
            myIPMap.put("IP", stm32.getStm32Ip());
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("내 STM32 보드 IP 수정 완료")
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
