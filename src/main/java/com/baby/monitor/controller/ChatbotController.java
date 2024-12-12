package com.baby.monitor.controller;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.domain.ActingVO;
import com.baby.monitor.service.ChatbotService;
import com.baby.monitor.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;
    private final SensorService sensorService;
    RestResponse<Object> restResponse = new RestResponse<>();

    @GetMapping("/bed/backdraft/on")
    public ResponseEntity backDraftOn(@RequestParam int memberNumber){
        log.info("[챗봇] 역류 방지 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "backdraft/on");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 역류 방지 기능을 수행할게요. 최대 20초 정도 소요될 수 있어요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());
            
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/backdraft/off")
    public ResponseEntity backDraftOff(@RequestParam int memberNumber){
        log.info("[챗봇] 역류 방지 기능 종료");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "backdraft/off");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 역류 방지 기능을 종료할게요. 최대 20초 정도 소요될 수 있어요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/burp/on")
    public ResponseEntity burpOn(@RequestParam int memberNumber){
        log.info("[챗봇] 트름 유도 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "burp/on");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 아기 트름을 유도할게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/burp/off")
    public ResponseEntity burpOff(@RequestParam int memberNumber){
        log.info("[챗봇] 트름 유도 기능 종료");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "burp/off");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 트림 유도 기능을 종료할게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/swing/on")
    public ResponseEntity swing(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 스윙 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "swing/on");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 스윙할게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/swing/off")
    public ResponseEntity swingOn(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 스윙 기능 종료");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "swing/off");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대 스윙을 멈출게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/flip/right/on")
    public ResponseEntity flipRight(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 작은 엑추에이터 뒤집기 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "flip/right/on");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 뒤집기할게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/flip/left/on")
    public ResponseEntity flipLeft(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 왼쪽 엑추에이터 뒤집기 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "flip/left/on");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 뒤집기할게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/flip/off")
    public ResponseEntity flipOff(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 뒤집기 기능 종료");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "flip/off");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대 뒤집기을 멈출게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/flip/down")
    public ResponseEntity flipDown(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 작은 엑추에이터 뒤집기 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "flip/down");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 내리고 있어요")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/bed/fix")
    public ResponseEntity fix(@RequestParam int memberNumber){
        log.info("[챗봇] 침대 고정 기능 수행");
        try{
            ActingVO acting = chatbotService.RequestToStm(memberNumber, "fix");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("네. 침대를 고정할게요.")
                    .data(acting)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/ai/babysleep")
    public ResponseEntity babySleep(@RequestParam int memberNumber){
        log.info("[챗봇] 아기 취침 여부 확인 기능 수행");
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
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @GetMapping("/sensor/babystatus/temperature")
    public ResponseEntity babyStatusTemperature(@RequestParam int memberNumber){
        try{
            Object temperature = sensorService.checkBabyTemperature(memberNumber);
            if (temperature == null) {
                restResponse = RestResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("아기가 침대에서 자고 있지 않아요")
                        .data(null)
                        .build();
                return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
            }

            Float Temperature = (float) temperature;
            log.info(String.valueOf(Temperature));
            if(Temperature >= 37.5){
                restResponse = RestResponse.builder()
                        .code(HttpStatus.PARTIAL_CONTENT.value())
                        .httpStatus(HttpStatus.PARTIAL_CONTENT)
                        .message("아기 체온에 이상이 생겼어요")
                        .data(temperature)
                        .build();
                return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
            } else if (Temperature < 37.5) {
                restResponse = RestResponse.builder()
                        .code(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .message("아기 체온이 정상이에요")
                        .data(temperature)
                        .build();
                return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
            } else {
                restResponse = RestResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("아기가 침대에서 자고 있지 않아요")
                        .data(null)
                        .build();
                return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
            }
        } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    // 챗봇 응답 버전용
    @GetMapping("/sensor/babystatus/now")
    public ResponseEntity babyStatusNow(@RequestParam int memberNumber){
        log.info("[챗봇] 아기 현 상태 확인");
        try {
            String message = sensorService.checkBabyStatus(memberNumber);
            if (message == null){
                restResponse = RestResponse.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("아기가 침대에서 자고 있지 않아요")
                        .data(null)
                        .build();
                return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
            } else {
                restResponse = RestResponse.builder()
                        .code(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .message(message)
                        .data(null)
                        .build();
                return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
            }
            } catch(Exception e) {
            String result = ExceptionMessage(e.toString());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(result)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    public String ExceptionMessage(String e){
        // 입력 문자열을 공백 기준으로 나누기
        String[] words = e.toString().split(" ");

        // 세 번째 단어부터 출력
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < words.length; i++) {
            result.append(words[i]).append(" ");
        }

        // 결과 출력
        log.info(result.toString().trim());

        if (result.toString().trim().substring(0,3).equals("I/O")){
            return "침대와의 연결이 불안정합니다. 같은 네트워크에 있는지 확인해주세요.";
        }

        return result.toString().trim();
    }
}
