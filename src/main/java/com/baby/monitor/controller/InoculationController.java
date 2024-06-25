package com.baby.monitor.controller;

import com.baby.monitor.DTO.InoculationDTO;
import com.baby.monitor.domain.InoculationMemberVO;
import com.baby.monitor.domain.RestResponse;
import com.baby.monitor.service.InoculationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inoculation") // API의 기본 경로 설정
public class InoculationController {

    private final InoculationService inoculationService;
    RestResponse<Object> restResponse = new RestResponse<>();

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/list")
    public ResponseEntity FindInoculationList(@RequestParam int memberNumber) throws Exception {
        logger.info("[아기 접종 이력 조회]");
        List<InoculationMemberVO> ListInoculation = inoculationService.findInoculationMember(memberNumber);

        List<InoculationDTO> ListDTO = inoculationService.changeTODTO(ListInoculation);

        // 성공적으로 로그인 했을때.
        try{
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("아기 접종 이력 조회")
                    .data(ListDTO)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // 이외 예상치 못한 오류
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
