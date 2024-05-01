package com.baby.monitor.persistance;

import com.baby.monitor.domain.MemberVO;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    MemberVO save(MemberVO member);

    MemberVO findByMemberId(String memberId);
}
