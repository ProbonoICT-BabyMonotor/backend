package com.baby.monitor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "member") // 테이블 이름을 명시해줄 수 있습니다.
public class MemberVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNumber;

    private String memberName;

    private String memberId;

    private String memberPhone;

    private String memberPassword;

    private LocalDate memberBirth;

    private String memberGender;

    public MemberVO() {

    }

    public MemberVO(String memberName, String memberId, String memberPhone, String memberPassword, LocalDate memberBirth, String memberGender){
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPhone = memberPhone;
        this.memberPassword = memberPassword;
        this.memberBirth = memberBirth;
        this.memberGender = memberGender;
    }

    public void setMemberBirth(String memberBirth)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.memberBirth = LocalDate.parse(memberBirth, formatter);
    }

}
