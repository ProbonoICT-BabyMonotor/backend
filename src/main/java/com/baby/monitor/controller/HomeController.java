package com.baby.monitor.controller;

import com.baby.monitor.domain.MemberVO;
import com.baby.monitor.domain.RestResponse;
import com.baby.monitor.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class HomeController {

    private final MemberService memberService;
    RestResponse<Object> restResponse = new RestResponse<>();

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }

    @RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody MemberVO member) throws Exception {
        try{
            logger.info("[회원가입 요청] ",member.getMemberName());
            // 회원가입 진행 후 비밀번호 암호화
            MemberVO signUpMember = memberService.signUpMember(member);
            signUpMember.setMemberPassword("secret");

            restResponse = RestResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .httpStatus(HttpStatus.CREATED)
                    .message("회원가입이 정상적으로 진행되었습니다.")
                    .data(signUpMember)
                    .build();
        }
        // 회원가입 실패 시, (중복된 아이디가 존재할 때)
        catch (IllegalStateException e){
            restResponse = RestResponse.builder()
                    .code(HttpStatus.FORBIDDEN.value())
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message(e.getMessage())
                    .build();
        }
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }
}
