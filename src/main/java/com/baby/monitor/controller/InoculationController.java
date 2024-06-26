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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inoculation") // API의 기본 경로 설정
public class InoculationController {

    private final InoculationService inoculationService;
    RestResponse<Object> restResponse = new RestResponse<>();

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/list")
    public ResponseEntity FindInoculationList(@RequestParam int memberNumber){
        logger.info("[아기 접종 이력 조회]");
        // 성공적으로 로그인 했을때.
        try{
            List<InoculationMemberVO> ListInoculation = inoculationService.findInoculationMember(memberNumber);
            List<InoculationDTO> ListDTO = inoculationService.changeTODTO(ListInoculation);

            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("아기 접종 이력 조회")
                    .data(ListDTO)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // 일치하는 회원번호가 없을 때
        catch (IllegalArgumentException e){
            logger.info(e.toString());
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    /**
     * 접종 이력 변경 하기
     * @param inoculationMember
     * @return
     */
    @PutMapping("")
    public ResponseEntity updateInoculationStatus(@RequestBody InoculationMemberVO inoculationMember){
        try{
            boolean isSuccess = inoculationService.updateInoculationStatus(inoculationMember);
            List<InoculationDTO> ListDTO = new ArrayList<>();
            if (isSuccess){
                List<InoculationMemberVO> ListInoculation = inoculationService.findInoculationMember(inoculationMember.getMemberNumber());
                ListDTO = inoculationService.changeTODTO(ListInoculation);
            }
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("접종 이력 변경 완료!")
                    .data(ListDTO)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        catch (Exception e){
            logger.info(e.toString());
            restResponse = RestResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

    }
}
