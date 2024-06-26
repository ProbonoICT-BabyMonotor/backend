package com.baby.monitor.service;

import com.baby.monitor.domain.MemberVO;
import com.baby.monitor.persistance.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /***
     * 회원 가입하는 메소드 [비밀번호 인코딩]
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

    /***
     * 로그인 하는 메소드, 암호화 된 비밀번호로 로그인
     * @param member (ID, PW)
     * @return 로그인 되었으면, 완성된 객체 전달함. 아니면 오류 발생
     */
    public MemberVO loginMember(MemberVO member) throws Exception {
        String memberId = member.getMemberId();
        MemberVO DB_member = memberRepository.findByMemberId(memberId);

        // 아이디 미 일치
        if (DB_member == null){
            throw new IllegalArgumentException("[로그인] 아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 미 일치
        if (passwordEncoder.matches(member.getMemberPassword(), DB_member.getMemberPassword())) {
            return DB_member;
        } else {
            throw new IllegalArgumentException("[로그인] 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
}
