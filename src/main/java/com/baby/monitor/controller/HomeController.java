package com.baby.monitor.controller;

import com.baby.monitor.DTO.RestResponse;
import com.baby.monitor.DTO.SignupDTO;
import com.baby.monitor.domain.BabyVO;
import com.baby.monitor.domain.MemberVO;
import com.baby.monitor.service.ActingService;
import com.baby.monitor.service.BabyService;
import com.baby.monitor.service.InoculationService;
import com.baby.monitor.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/") // API의 기본 경로 설정
public class HomeController {

    private final MemberService memberService;
    private final BabyService babyService;
    private final InoculationService inoculationService;
    private final ActingService actingService;
    RestResponse<Object> restResponse = new RestResponse<>();

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberVO member) throws Exception {
        log.info("[로그인 요청] ID : " + member.getMemberId());

        // 성공적으로 로그인 했을때.
        try{
            MemberVO full_member = memberService.loginMember(member);
            full_member.setMemberPassword("secret");
            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("정상적으로 로그인 되었습니다.")
                    .data(full_member)
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }

        // ID 또는 비밀번호가 일치하지 않음, IllegalArgumentException 발생
        catch (IllegalArgumentException e){
            restResponse = RestResponse.builder()
                    .code(HttpStatus.FORBIDDEN.value())
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
        }
    }

    @RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody SignupDTO signupDTO) throws Exception {
        try{
            log.info("[회원가입 요청] ",signupDTO.getMemberName());

            MemberVO member = signupDTO.getMemberVO();
            BabyVO baby = signupDTO.getBabyVO();

            // 회원가입 진행 후 비밀번호 암호화
            baby.setMemberNumber(memberService.signUpMember(member).getMemberNumber());

            babyService.signUpBaby(baby);

            // [DB] inoculation_member Table 초기화
            inoculationService.setUpInoculationList(member.getMemberNumber());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .httpStatus(HttpStatus.CREATED)
                    .message("회원가입이 정상적으로 진행되었습니다.")
                    .data(memberService.signUpMember(member))
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

    /**
     * 침대 현 상태 조회하기
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/status/now" }, method = RequestMethod.GET)
    public ResponseEntity statusNow(@RequestParam int memberNumber) throws Exception {
        try{
            MemberVO searchMember = memberService.findMemberName(memberNumber);
            log.info("[현재 침대 상태 확인 요청] ({}) {}", searchMember.getMemberId(), searchMember.getMemberName());

            restResponse = RestResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message(actingService.searchNowActing(memberNumber))
                    .data(null)
                    .build();
        }

        // 알 수 없는 오류 발생 시
        catch (Exception e){
            restResponse = RestResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message(e.getMessage())
                    .build();
        }
        return new ResponseEntity<>(restResponse, restResponse.getHttpStatus());
    }
}
