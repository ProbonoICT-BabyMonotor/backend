package com.baby.monitor.controller;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;
    RestResponse<Object> restResponse = new RestResponse<>();
    private static final Logger logger = LoggerFactory.getLogger(ChatbotController.class);

    @GetMapping("/bed/backdraft")
    public ResponseEntity backDraft(@RequestParam int memberNumber){
        logger.info("[챗봇] 역류 방지 기능 수행");
        chatbotService.RequestToStm(memberNumber, "backdraft");

        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 역류 방지 기능을 켤게요.")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/burp")
    public ResponseEntity burp(@RequestParam int memberNumber){
        logger.info("[챗봇] 트름 유도 기능 수행");
        chatbotService.RequestToStm(memberNumber, "burp");

        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 아기 트름을 유도할게요.")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/swing")
    public ResponseEntity swing(@RequestParam int memberNumber){
        logger.info("[챗봇] 침대 스윙 기능 수행");
        chatbotService.RequestToStm(memberNumber, "swing");

        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 스윙할게요.")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/fix")
    public ResponseEntity fix(@RequestParam int memberNumber){
        logger.info("[챗봇] 침대 고정 기능 수행");
        chatbotService.RequestToStm(memberNumber, "fix");

        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 고정할게요.")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/ai/babysleep")
    public ResponseEntity babySleep(@RequestParam int memberNumber){
        logger.info("[챗봇] 아기 취침 여부 확인 기능 수행");
        // TODO 젯슨 나노 연동 필요
        
        // 역류 방지 기능을 수행 한 후,
        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/sensor/babystatus")
    public ResponseEntity babyStatus(@RequestParam int memberNumber){
        logger.info("[챗봇] 아기 상태 확인 기능 수행");
        // TODO 센서 연동 필요
        
        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/sensor/surroundings")
    public ResponseEntity surroundings(@RequestParam int memberNumber){
        logger.info("[챗봇] 주변 환경 체크 기능 수행");
        // TODO 센서 연동 필요

        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("")
                    .data(null)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

}
