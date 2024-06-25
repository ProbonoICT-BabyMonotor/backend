package com.baby.monitor.DTO;

import com.baby.monitor.domain.BabyVO;
import com.baby.monitor.domain.MemberVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SignupDTO {
    private String memberName;
    private String memberId;
    private String memberPhone;
    private String memberPassword;
    private LocalDate memberBirth;
    private String memberGender;

    private String babyName;
    private LocalDate babyBirth;
    private Float babyWeight;

    public MemberVO getMemberVO(){
        return new MemberVO(memberName, memberId, memberPhone, memberPassword, memberBirth, memberGender);
    }

    public BabyVO getBabyVO(){
        return new BabyVO(babyName, babyBirth, babyWeight);
    }
}
