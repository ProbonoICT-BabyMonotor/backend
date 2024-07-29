package com.baby.monitor.controller;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.DTO.SleepingDTO;
import com.baby.monitor.service.SleepingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sleeping") // API의 기본 경로 설정
public class SleepingController {
    private final SleepingService sleepingService;
    RestResponse<Object> restResponse = new RestResponse<>();

    @GetMapping("/list")
    public ResponseEntity findSleepingList(@RequestParam int memberNumber) throws Exception{
        log.info("[취침 이력 조회] {}",memberNumber);

        try{
            ArrayList<SleepingDTO> sleepings = sleepingService.searchSleepingList(memberNumber);
            
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("취침 이력을 조회합니다.")
                    .data(sleepings)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // 아기 취침 이력이 없을 때
        catch (NullPointerException e){
            log.info("[아기 취침 이력 없음] {}", e.toString());
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }
}
