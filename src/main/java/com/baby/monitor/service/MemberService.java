package com.baby.monitor.service;

import com.baby.monitor.domain.MemberVO;
import com.baby.monitor.persistance.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /***
     * 회원 가입하는 메소드 [비밀번호 인코딩 ]
     * @param member
     * @return member (회원가입한 친구들)
     */
    public MemberVO signUpMember(MemberVO member) throws Exception {
        String inputPW = member.getMemberPassword();
        member.setMemberPassword(passwordEncoder.encode(inputPW));

        // 아이디 중복이면 오류 발생, 아니면 회원가입 진행
        if(memberRepository.findByMemberId(member.getMemberId()) != null){
            throw new IllegalStateException("이미 가입된 아이디가 있습니다.");
        }
        else{
            return memberRepository.save(member);
        }
    }
}
