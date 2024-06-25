package com.baby.monitor.controller;

import com.baby.monitor.domain.RestResponse;
import com.baby.monitor.service.InoculationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inoculation") // API의 기본 경로 설정
public class InoculationController {

    private final InoculationService inoculationService;
    RestResponse<Object> restResponse = new RestResponse<>();

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/list")
    public ResponseEntity FindInoculationList() throws Exception {
        logger.info("[접종 해야 할 리스트 출력]");
        HashMap<String, List> ListInoculation = inoculationService.findInoculationList();

        // 성공적으로 로그인 했을때.
        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("예방 접종 해야 할 리스트 입니다.")
                    .data(ListInoculation)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // ID 또는 비밀번호가 일치하지 않음, IllegalArgumentException 발생
        catch (Exception e){
            logger.info(e.toString());
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("예상치 못한 오류가 발생했습니다.")
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }
}
