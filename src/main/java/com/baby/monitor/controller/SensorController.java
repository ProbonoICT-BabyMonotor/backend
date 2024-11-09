package com.baby.monitor.controller;

import com.baby.monitor.DTO.ReceiveSensorDTO;
import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.DTO.SensorRequestDTO;
import com.baby.monitor.domain.SensorsVO;
import com.baby.monitor.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sensor") // API의 기본 경로 설정
public class SensorController {
    private final SensorService sensorService;
    RestResponse<Object> restResponse = new RestResponse<>();
    @PostMapping("/date")
    public ResponseEntity findSensorDate(@RequestBody SensorRequestDTO sensorRequestDTO){

        log.info("[센서 데이터 조회]");
        // 성공적으로 로그인 했을때.
        try{
            Map<String, SensorsVO> sensors = sensorService.findBySensorDateAndMemberNumber(sensorRequestDTO.getSensorDate(),sensorRequestDTO.getMemberNumber());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(sensorRequestDTO.getSensorDate() + "날의 센서 데이터를 조회할게요.")
                    .data(sensors)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // 일치하는 회원번호가 없을 때
        catch (IllegalArgumentException e){
            log.info(e.toString());
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @PostMapping("/save")
    public ResponseEntity receiveSensor(@RequestBody ReceiveSensorDTO receiveSensorDTO){
        log.info("[챗봇] 센서 데이터 저장하기");
        SensorsVO sensorsVO = sensorService.addSensorData(receiveSensorDTO);
        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("센서 데이터가 잘 저장되었습니다!")
                    .data(sensorsVO)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            log.info(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("오류가 발생하였습니다.")
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }
}
